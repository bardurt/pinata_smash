package com.zygne.game.screens;

import com.zygne.game.framework.objects.DynamicGameObject;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 01/09/2018.
 */
public class Ball extends DynamicGameObject {

    private final double gravity = -23.89;
    private double angle = 0;
    private final int length = 100;
    private final int anchorX;
    private final int anchorY;
    private double angleAccel = 0;
    private double angleVelocity = 0;
    private double damping = 0.995d;

    private int velUpdated = 0;
    private double direction = -1;
    private boolean stationary = false;

    public Ball(float x, float y, float width, float height) {
        super(x, y, width, height);
        anchorX = (int)x;
        anchorY = 350;
    }

    public void update(World world, float deltaTime) {

        updatePendulum(deltaTime);
    }

    private void updateNonPendulum(World world, float deltaTime){

        double distance = velocity.x * direction;

        position.x += distance;

        if (position.x - bounds.width/2 <= 0){
            position.x = bounds.width/2;
            direction = 1;
            velocity.x = 4f;
            world.crash();
        } else if(position.x + bounds.width/2 >= 320){
            position.x = 320 - bounds.width/2;
            direction = -1;
            velocity.x = 4f;
            world.crash();
        }

        velUpdated++;

        if(velUpdated >= 6) {

            if(velocity.x > 0.5) {
                velocity.x *= 0.90;
                velUpdated = 0;
            }
        }

        if(Math.abs(distance) <= 0.5){

            if(Math.abs(position.x - anchorX) > 2){

                if(position.x > anchorX){
                    direction = -1;
                } else {
                    direction = 1;
                }

            } else {
                stationary = true;
                velocity.x = 0f;
            }

            stationary = true;
        } else {
            stationary = false;
        }

    }

    private void updatePendulum(float dt){

        angleAccel = gravity / length * Math.sin(angle);
        angleVelocity += angleAccel * dt;
        angleVelocity *= damping;
        angle += angleVelocity * dt;

        int ballX = anchorX + (int) (Math.sin(angle) * length);
        int ballY = anchorY - (int) (Math.cos(angle) * length);

        if(Math.abs(angleVelocity) > 0.01){
            position.x = ballX;
            position.y = ballY;
            stationary = false;
        } else {
            stationary = true;
        }
    }

    public void thrustLeft(){
        if(stationary) {
            angleVelocity = -1;
        }
    }

    public void thrustRight(){
        if(stationary) {
            angleVelocity = 1;
        }
    }

    public void thrust(World world, double acceleration){

        if(stationary){
            if(acceleration > 6){
                angleVelocity = -0.85;
                world.hit(100);
            } else if(acceleration > 10){
                angleVelocity = -0.9;
                world.hit(120);
            } else if(acceleration > 12){
                angleVelocity = -0.98;
                world.hit(180);
            } else if(acceleration > 16){
                angleVelocity = -1.1;
                world.hit(200);
            }
        }
    }
}
