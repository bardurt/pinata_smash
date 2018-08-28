package com.zygne.game.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.zygne.game.engine.components.BoxCollider;


/**
 * Created by Bardur on 31/05/2016.
 */
public class GameCamera {
    public BoxCollider bc;

    public GameCamera(int x, int y, int width, int height){
        bc = new BoxCollider();
        bc.setWidth(width);
        bc.setHeight(height);
        bc.setPosX(x);
        bc.setPosY(y);
    }

    public void draw(Canvas c){
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        c.drawRect(bc.getLeft(),bc.getTop(),bc.getRight(),bc.getBottom(),p);
    }
}
