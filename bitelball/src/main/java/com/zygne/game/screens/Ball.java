package com.zygne.game.screens;

import android.util.Log;

import com.zygne.game.Assets;
import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.implementation.TextureRegion;
import com.zygne.game.framework.objects.DynamicGameObject;
import com.zygne.game.framework.objects.RendableObject;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 01/09/2018.
 */
public class Ball extends DynamicGameObject implements RendableObject {

    private int lives = 100;

    private final double gravity = -9.81;
    private final int speed = 112;
    private final int anchorX;
    private final int anchorY;
    private float angleAccel = 0f;
    private float angleVelocity = 0f;
    private float damping = 0.995f;
    public float angle = 270f;
    private int length;
    private boolean stationary = false;
    private final int stationaryTime = 65;
    private int stationaryCounter;

    public Ball(float x, float y, float anchorY, float width, float height) {
        super(x, y, width, height);
        anchorX = (int)x;
        this.anchorY = (int)anchorY;
        length = (int) (anchorY - y);
    }


    public void update(World world, float deltaTime) {

        updatePendulum(deltaTime);
    }

    private void updatePendulum(float dt){

        angleAccel = (float) (gravity / length * Math.sin(angle));
        angleVelocity += angleAccel * (dt*speed);
        angleVelocity *= damping;
        angle += angleVelocity * dt;

        int ballX = anchorX + (int) (Math.sin(angle) * length);
        int ballY = anchorY - (int) (Math.cos(angle) * length);

        position.x = ballX;
        position.y = ballY;

        if(stationary){

            stationaryCounter++;

            if(stationaryCounter > stationaryTime){
                stationary = false;
                stationaryCounter = 0;
            }
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

        if(!stationary){

            int acc = (int) acceleration;
            Log.d("BALL", "Thrust: " + acceleration);

            if(acc > 3 && acc < 8){
                angleVelocity -= 0.85f;
                world.hit(5);
                lives -= 3;
            } else if(acceleration > 8 && acc < 13){
                angleVelocity -= 1.1f;
                world.hit(10);
                lives -= 8;
            } else if(acceleration > 14 && acc < 24){
                angleVelocity -= 1.53f;
                world.hit(15);
                lives -= 16;
            } else if (acc > 24){
                angleVelocity -= 1.8f;
                world.hit(20);
                lives -= 22;
            }

            stationary = true;

            if(lives < 24){
                world.createEmitter();
            }
        }
    }

    public float getAnchorXOffset(){
        return anchorX + position.x;
    }

    @Override
    public void render(GL10 gl, SpriteBatcher batcher) {

        batcher.beginBatch(Assets.items);

        TextureRegion textureRegion = null;

        if(lives < 24){
            textureRegion = Assets.ballRegion2;
        } else if (lives >= 24 && lives < 64){
            textureRegion = Assets.ballRegion1;
        } else {
            textureRegion = Assets.ballRegion;
        }

        batcher.drawSprite(position.x,
                position.y,
                bounds.width,
                bounds.height,
                textureRegion);

        batcher.endBatch();
    }
}
