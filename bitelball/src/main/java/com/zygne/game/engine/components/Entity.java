package com.zygne.game.engine.components;

import android.graphics.Canvas;

import com.zygne.game.engine.GameCamera;

/**
 * Created by Bardur on 11/06/2016.
 */
public class Entity{

    public BoxCollider collider;
    public Physics physics;
    public Health health;
    public boolean shouldDraw =false;
    public boolean isActive = true;
    public boolean rigidBody = false;
    public AnimationController animationController;
    public Signal signal;
    public State state;
    public Score score;
    public Bonus bonus;
    public int color;
    boolean onScreen = false;
    boolean hasEnteredScreen = false;

    public Entity(){
        signal = new Signal(this);
        state = new State(this);
        state.setState(States._ACTIVE);
    }

    public void render(Canvas c) {

    }


    public void update(GameCamera cam) {

        if(physics!=null) {
            physics.update();
        }

        if(this.collider.isColliding(cam.bc)) {
            hasEnteredScreen = true;
            onScreen = true;
            shouldDraw = true;
        }else{
            if(hasEnteredScreen){
                onScreen = false;
            }
            shouldDraw=false;
        }

    }

    public void checkForCollision(Entity e){ }

    public void setPosition(int x, int y){ }

    public void setSize(int w, int h){ }

    public void reset(){};
}
