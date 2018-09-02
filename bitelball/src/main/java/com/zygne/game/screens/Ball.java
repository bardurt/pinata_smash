package com.zygne.game.screens;

import com.zygne.game.framework.objects.DynamicGameObject;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 01/09/2018.
 */
public class Ball extends DynamicGameObject {

    private int velUpdated = 0;
    private double VELOCITY = 0;
    double direction = -1;

    boolean stationary = false;

    public Ball(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void update(World world, float deltaTime) {
        double distance = VELOCITY * direction;

        position.x += distance;

        if (position.x - bounds.width/2 <= 0){
            position.x = bounds.width/2;
            direction = 1;
            VELOCITY = 4d;
            world.crash();
        } else if(position.x + bounds.width/2 >= 320){
            position.x = 320 - bounds.width/2;
            direction = -1;
            VELOCITY = 4d;
            world.crash();
        }

        velUpdated++;

        if(velUpdated >= 6) {
            VELOCITY *= 0.90;
            velUpdated = 0;
        }

        if(Math.abs(distance) < 0.5){
            stationary = true;
        } else {
            stationary = false;
        }

    }

    public void thrustLeft(){
        if(stationary) {
            direction = -1;
            VELOCITY = 6d;
        }
    }

    public void thrustRight(){
        if(stationary) {
            direction = 1;
            VELOCITY = 6d;
        }
    }

    public void thrust(World world, double acceleration){

        if(stationary){

            if(acceleration > 6){
                direction = -1;
                VELOCITY = 3d;
                world.hit(20);
            } else if(acceleration > 10){
                direction = -1;
                VELOCITY = 6d;
                world.hit(60);
            } else if(acceleration > 12){
                direction = -1;
                VELOCITY = 8d;
                world.hit(100);
            } else if(acceleration > 16){
                direction = -1;
                VELOCITY = 12d;
                world.hit(160);
            }

        }

    }
}
