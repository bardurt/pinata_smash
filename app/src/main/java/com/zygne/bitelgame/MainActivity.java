package com.zygne.bitelgame;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zygne.game.engine.GameSceneManager;
import com.zygne.game.scenes.LevelOne;

import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity implements
    View.OnTouchListener{

    public GameView gameView;
    static int ScreenHeight;
    static int ScreenWidth;
    private GameSceneManager sceneManager;

    // we'll be reading the stats every second
    private final static int 	STAT_INTERVAL = 1000; //ms
    // the average will be calculated by storing
    // the last n FPSs
    private final static int	FPS_HISTORY_NR = 10;
    // last time the status was stored
    private long lastStatusStore = 0;
    // the status time counter
    private long statusIntervalTimer	= 0l;
    // number of frames skipped since the game started
    private long totalFramesSkipped			= 0l;
    // number of frames skipped in a store cycle (1 sec)
    private long framesSkippedPerStatCycle 	= 0l;

    // number of rendered frames in an interval
    private int frameCountPerStatCycle = 0;

    private long totalFrameCount = 0l;
    // the last FPS values
    private double 	fpsStore[];
    // the number of times the stat has been read
    private long 	statsCount = 0;
    // the average FPS since the game started
    private double 	averageFps = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DisplayMetrics display = this.getResources().getDisplayMetrics();
        ScreenHeight = display.heightPixels;
        ScreenWidth = display.widthPixels;

        sceneManager = new GameSceneManager();
        sceneManager.push(new LevelOne(this, sceneManager));
        sceneManager.peek().init(ScreenWidth, ScreenHeight);

        gameView = new GameView(this);
        gameView.setOnTouchListener(this);

        setContentView(gameView);


        fpsStore = new double[]{};
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sceneManager.pop();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            sceneManager.peek().onTouch(motionEvent);
        } catch (NullPointerException | EmptyStackException e) {
        }
        return true;
    }

    public class GameView extends SurfaceView implements Runnable {

        private final static int MAX_FPS = 35;
        // maximum number of frames to be skipped
        private final static int MAX_FRAME_SKIPS = 5;
        // the frame period
        private final static int FRAME_PERIOD = 1000 / MAX_FPS;
        Thread t = null;
        SurfaceHolder holder;
        public boolean isOkey = false;

        public GameView(Context context) {
            super(context);
            holder = getHolder();
        }

        @Override
        public void run() {
            long beginTime;        // the time when the cycle begun
            long timeDiff;        // the time it took for the cycle to execute
            int sleepTime;        // ms to sleep (<0 if we're behind)
            int framesSkipped;    // number of frames being skipped
            Canvas canvas = null;


            while (isOkey) {
                if (!holder.getSurface().isValid()) {
                    continue;
                }

                try {

                    synchronized (holder) {
                        beginTime = System.currentTimeMillis();
                        framesSkipped = 0;  // resetting the frames skipped

                        canvas = holder.lockCanvas();
                        sceneManager.render(canvas);
                        holder.unlockCanvasAndPost(canvas);
                        sceneManager.update();
                        timeDiff = System.currentTimeMillis() - beginTime;
                        // calculate sleep time
                        sleepTime = (int) (FRAME_PERIOD - timeDiff);

                        if (sleepTime > 0) {
                            // if sleepTime > 0 we're OK
                            try {
                                // send the thread to sleep for a short period
                                // very useful for battery saving
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                            }
                        }

                        while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                            // we need to catch up
                            // update without rendering
                            sceneManager.update();
                            // add frame period to check if in next frame
                            sleepTime += FRAME_PERIOD;
                            framesSkipped++;
                        }

                        if (framesSkipped > 0) {
                            Log.d("Frames Skipped", "Skipped:" + framesSkipped);
                        }

                    }
                } finally {

                }
            }
        }

        public void pause() {
            isOkey = false;
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t = null;
        }

        public void resume() {
            isOkey = true;
            t = new Thread(this);
            t.start();
        }

        private void storeStats() {
            frameCountPerStatCycle++;
            totalFrameCount++;

            // check the actual time
            statusIntervalTimer += (System.currentTimeMillis() - statusIntervalTimer);

            if (statusIntervalTimer >= lastStatusStore + STAT_INTERVAL) {
                // calculate the actual frames pers status check interval
                double actualFps = (double)(frameCountPerStatCycle / (STAT_INTERVAL / 1000));

                //stores the latest fps in the array
                fpsStore[(int) statsCount % FPS_HISTORY_NR] = actualFps;

                // increase the number of times statistics was calculated
                statsCount++;

                double totalFps = 0.0;
                // sum up the stored fps values
                for (int i = 0; i < FPS_HISTORY_NR; i++) {
                    totalFps += fpsStore[i];
                }

                // obtain the average
                if (statsCount < FPS_HISTORY_NR) {
                    // in case of the first 10 triggers
                    averageFps = totalFps / statsCount;
                } else {
                    averageFps = totalFps / FPS_HISTORY_NR;
                }
                // saving the number of total frames skipped
                totalFramesSkipped += framesSkippedPerStatCycle;
                // resetting the counters after a status record (1 sec)
                framesSkippedPerStatCycle = 0;
                statusIntervalTimer = 0;
                frameCountPerStatCycle = 0;

                statusIntervalTimer = System.currentTimeMillis();
                lastStatusStore = statusIntervalTimer;
                Log.d("GAME FPS", "Average FPS:" + averageFps);

            }
        }

        private void initTimingElements() {
            // initialise timing elements
            fpsStore = new double[FPS_HISTORY_NR];
            for (int i = 0; i < FPS_HISTORY_NR; i++) {
                fpsStore[i] = 0.0;
            }
        }
    }
}
