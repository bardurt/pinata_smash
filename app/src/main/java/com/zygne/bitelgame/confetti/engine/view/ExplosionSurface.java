package com.zygne.bitelgame.confetti.engine.view;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 17/09/2018.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zygne.bitelgame.confetti.engine.explosions.Explosion;

/**
 * Created by Bardur Thomsen on 9/17/18.
 */
public class ExplosionSurface extends SurfaceView implements
        SurfaceHolder.Callback, Runnable {

    private SurfaceHolder surfaceHolder;

    private Paint paint = null;

    private Thread thread = null;

    // Record whether the child thread is running or not.
    private boolean running = false;

    private int screenWidth = 0;

    private int screenHeight = 0;

    private static String LOG_TAG = "SURFACE_VIEW_THREAD";

    private Explosion explosion;

    private Bitmap background;

    public ExplosionSurface(Context context) {
        super(context);

        setFocusable(true);

        // Get SurfaceHolder object.
        surfaceHolder = null;
        surfaceHolder = this.getHolder();
        // Add current object as the callback listener.
        surfaceHolder.addCallback(this);

        // Set the SurfaceView object at the top of View object.
        setZOrderOnTop(false);

        setBackgroundColor(Color.TRANSPARENT);

       // background = BitmapFactory.decodeResource(getResources(), R.drawable.fiesta_bg);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        // Create the child thread when SurfaceView is created.
        thread = new Thread(this);
        // Start to run the child thread.
        thread.start();
        // Set thread running flag to true.
        running = true;

        // Get screen width and height.
        screenHeight = getHeight();
        screenWidth = getWidth();

        explosion = new Explosion(20, screenWidth / 2, screenHeight / 2);
       // background = Bitmap.createScaledBitmap(background, screenWidth, screenHeight,false);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // Set thread running flag to false when Surface is destroyed.
        // Then the thread will jump out the while loop and complete.
        running = false;
    }


    @Override
    public void run() {
        while (running) {

            Canvas canvas= null;
            try {
                // Get Canvas from Holder and lock it.
                canvas = this.surfaceHolder.lockCanvas();

                canvas.drawBitmap(background, 0, 0, null);
                explosion.update(0);
                explosion.render(canvas);

            }catch(Exception e)  {
                // Do nothing.
            } finally {
                if(canvas!= null)  {
                    // Unlock Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}