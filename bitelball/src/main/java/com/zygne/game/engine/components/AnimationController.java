package com.zygne.game.engine.components;

import android.graphics.Canvas;
import android.util.Log;

import java.util.HashMap;


public class AnimationController {
    public final String NORMAL = "Normal";
    public final String HIT = "Hit";
    public final String JUMPING = "Jumping";
    public final String DEAD = "Dead";
    public final String DOUBLE_JUMPING ="Double_Jumping";
    public final String SLIDING = "Sliding";
    public final String FALLING = "Falling";
    public final String STANDING = "Standing";
    public final String RUNNING = "Running";

    private String state = NORMAL;

    HashMap<String, Sprite> animationList;

    public AnimationController(){
        this.animationList = new HashMap<>();
    }

    public void addAnimation(String name, Sprite sprite){
        animationList.put(name,sprite);
    }

    public Sprite getAnimation(String name){
        return animationList.get(name);
    }

    public void draw(Canvas c, int x, int y, String name){

        if(animationList.get(name) != null) {
            animationList.get(name).onDraw(c, x - animationList.get(name).dstWith / 2,
                                           y - animationList.get(name).dstHeight / 2);
        }
    }

    public void draw(Canvas c, int x, int y){

        if(animationList.get(state) != null) {
            animationList.get(state).onDraw(c, x - animationList.get(state).dstWith / 2,
                    y - animationList.get(state).dstHeight / 2);
        }
    }

    public void releaseBitmaps(){
        for (String key : animationList.keySet()) {
           if( !animationList.get(key).b.isRecycled())
               Log.d("Anim frame:", "is not recycled");
               animationList.get(key).recycle();
        }
    }

    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
}
