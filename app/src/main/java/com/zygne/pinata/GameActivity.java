package com.zygne.pinata;

import android.content.Intent;
import android.os.Bundle;

import com.zygne.engine.Screen;
import com.zygne.game.Assets;
import com.zygne.game.MovementSensor;
import com.zygne.game.Settings;
import com.zygne.engine.implementation.GLGame;
import com.zygne.game.screens.GameScreen;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameActivity extends GLGame implements
        MovementSensor.Listener {

    boolean firstTimeCreate = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void onMotionDetected(float dx, float dy) {
        if(getCurrentScreen() != null) {
            getCurrentScreen().onMovementDetected(dx, dy);
        }
    }


    @Override
    public void onGameStateChanged(int state) {
        startActivity(new Intent(GameActivity.this, MenuActivity.class));
    }

}