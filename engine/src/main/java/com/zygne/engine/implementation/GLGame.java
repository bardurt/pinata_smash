package com.zygne.engine.implementation;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;

import com.zygne.engine.core.base.Game;
import com.zygne.engine.Screen;
import com.zygne.engine.input.AndroidInput;
import com.zygne.engine.core.base.Input;
import com.zygne.engine.streams.AndroidAudio;
import com.zygne.engine.streams.AndroidFileIO;
import com.zygne.engine.core.base.Audio;
import com.zygne.engine.core.base.FileIO;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Bardur on 14/03/2017.
 */

public abstract class GLGame extends Activity implements
        Game,
        GLSurfaceView.Renderer {

    enum GLGameState {
        Initialized,
        Running,
        Paused,
        Finished,
        Idle
    }

    protected GLSurfaceView glView;
    protected GLGraphics glGraphics;
    protected Audio audio;
    protected Input input;
    protected FileIO fileIO;
    protected Screen screen;
    protected GLGameState state = GLGameState.Initialized;
    protected Object stateChanged = new Object();
    protected long startTime = System.nanoTime();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        setContentView(glView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        glGraphics = new GLGraphics(glView);
        fileIO = new AndroidFileIO(getAssets());
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, glView, 1, 1);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

    }

    public void onResume() {
        super.onResume();
        glView.onResume();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glGraphics.setGL(gl);

        synchronized(stateChanged) {
            if(state == GLGameState.Initialized)
                screen = getStartScreen();
            state = GLGameState.Running;
            screen.resume();
            startTime = System.nanoTime();
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLGameState state = null;

        synchronized(stateChanged) {
            state = this.state;
        }

        if(state == GLGameState.Running) {
            float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            screen.update(deltaTime);
            screen.render(deltaTime);
        }

        if(state == GLGameState.Paused) {
            screen.pause();
            synchronized(stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }

        if(state == GLGameState.Finished) {
            screen.pause();
            screen.dispose();
            synchronized(stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }
    }

    @Override
    public void onPause() {
        synchronized(stateChanged) {
            if(isFinishing())
                state = GLGameState.Finished;
            else
                state = GLGameState.Paused;
            while(true) {
                try {
                    stateChanged.wait();
                    break;
                } catch(InterruptedException e) {
                }
            }
        }

        glView.onPause();
        super.onPause();
    }

    public GLGraphics getGLGraphics() {
        return glGraphics;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

}
