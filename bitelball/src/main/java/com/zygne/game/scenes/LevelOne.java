package com.zygne.game.scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.zygne.game.engine.GameCamera;
import com.zygne.game.engine.GameSceneManager;
import com.zygne.game.engine.Scene;


public class LevelOne extends Scene {

    private GameCamera camera;

    int updateColor = 0;

    @Override
    public boolean isActive() {

        return false;
    }

    public LevelOne(Context c, GameSceneManager gsm) {
        super(c,gsm);
    }

    @Override
    public void init(int w, int h) {
        camera = new GameCamera(0,0,w,h);
    }

    @Override
    public void update() {
        updateColor +=10;
        if(updateColor >= 60){
            updateColor = 0;
        }
    }

    @Override
    public void render(Canvas c) {

        if(updateColor >= 30){
            c.drawColor(Color.RED);
        } else {
            c.drawColor(Color.BLUE);
        }
    }

    @Override
    public void destroyScene() {

    }

    @Override
    public void onTouch(MotionEvent event){

    }

    @Override
    public void update(long ms) {

    }
}


