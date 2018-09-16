package com.zygne.bitelgame;

import android.os.Bundle;

import com.zygne.game.Assets;
import com.zygne.game.MovementSensor;
import com.zygne.game.Settings;
import com.zygne.game.framework.Screen;
import com.zygne.game.framework.VibrationManager;
import com.zygne.game.framework.implementation.GLGame;
import com.zygne.game.screens.GameScreen;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends GLGame implements
        MovementSensor.Listener {

    boolean firstTimeCreate = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VibrationManager.getInstance().init(this);
        MovementSensor.getInstance().init(this);
        MovementSensor.getInstance().addListener(this);

    }

    @Override
    public Screen getStartScreen() {
        return new GameScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        if (firstTimeCreate) {
            Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate = false;
        } else {
            Assets.reload();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MovementSensor.getInstance().start();
        if(!firstTimeCreate){
            Assets.reload();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MovementSensor.getInstance().stop();
    }

    @Override
    public void onMotionDetected(double acceleraton) {
        getCurrentScreen().onMovementDetected(acceleraton);
    }

    @Override
    public void onGameStateChanged(int state) {

    }

}