package com.zygne.game.entities;

import android.util.Log;

import com.zygne.game.Assets;
import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.implementation.TextureRegion;
import com.zygne.game.framework.objects.DynamicGameObject;
import com.zygne.game.framework.objects.RendableObject;
import com.zygne.game.screens.World;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 01/09/2018.
 */
public class Ball extends DynamicGameObject implements RendableObject {

    private int lives = 100;
    private World world;

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
        anchorX = (int) x;
        this.anchorY = (int) anchorY;
        length = (int) (anchorY - y);
    }

    public void setWorld(World world) {
        this.world = world;
    }


    public void update(float deltaTime) {

        updatePendulum(deltaTime);
    }

    private void updatePendulum(float dt) {

        angleAccel = (float) (gravity / length * Math.sin(angle));
        angleVelocity += angleAccel * (dt * speed);
        angleVelocity *= damping;
        angle += angleVelocity * dt;

        int ballX = anchorX + (int) (Math.sin(angle) * length);
        int ballY = anchorY - (int) (Math.cos(angle) * length);

        position.x = ballX;
        position.y = ballY;

        if (stationary) {

            stationaryCounter++;

            if (stationaryCounter > stationaryTime) {
                stationary = false;
                stationaryCounter = 0;
            }
        }

        Log.d("Ball", "AngelVel: " + angleVelocity);
    }

    public void thrustLeft() {
        if (stationary) {
            angleVelocity = -1;
        }
    }

    public void thrustRight() {
        if (stationary) {
            angleVelocity = 1;
        }
    }

    public void thrust(World world, double acceleration) {

        if (!stationary) {

            int acc = (int) acceleration;
            Log.d("BALL", "Thrust: " + acceleration);

            if (acc > 3 && acc < 8) {
                angleVelocity -= 0.85f;
                world.hit(5);
                lives -= 3;
            } else if (acceleration > 8 && acc < 13) {
                angleVelocity -= 1.1f;
                world.hit(10);
                lives -= 8;
            } else if (acceleration > 14 && acc < 24) {
                angleVelocity -= 1.53f;
                world.hit(15);
                lives -= 16;
            } else if (acc > 24) {
                angleVelocity -= 1.8f;
                world.hit(20);
                lives -= 22;
            }

            stationary = true;

            if (lives < 24) {
                world.createEmitter();
            }
        }
    }

    public float getAnchorXOffset() {
        return anchorX + position.x;
    }

    @Override
    public void render(GL10 gl, SpriteBatcher batcher) {

        batcher.beginBatch(Assets.textureBall);

        TextureRegion textureRegion = null;

        if (lives < 15) {
            textureRegion = Assets.ball5;
        } else if (lives < 40) {
            textureRegion = Assets.ball4;
        } else if (lives < 60) {
            textureRegion = Assets.ball3;
        } else if (lives < 80) {
            textureRegion = Assets.ball2;
        } else {
            textureRegion = Assets.ball1;
        }

        batcher.drawSprite(position.x,
                position.y,
                bounds.width,
                bounds.height,
                textureRegion);

        batcher.endBatch();
    }

    public void hit(double acceleration) {

        float diff = position.x - anchorX;

        if (Math.abs(diff) < (bounds.width/2)) {

            if (!stationary) {

                int acc = (int) acceleration;
                Log.d("BALL", "Thrust: " + acceleration);

                if (acc > 3 && acc < 8) {
                    angleVelocity -= 0.85f;
                    world.hit(5);
                    lives -= 3;
                } else if (acceleration > 8 && acc < 13) {
                    angleVelocity -= 1.1f;
                    world.hit(10);
                    lives -= 8;
                } else if (acceleration > 14 && acc < 24) {
                    angleVelocity -= 1.53f;
                    world.hit(15);
                    lives -= 16;
                } else if (acc > 24) {
                    angleVelocity -= 1.8f;
                    world.hit(20);
                    lives -= 22;
                }

                stationary = true;

                if (lives < 24) {
                    world.createEmitter();
                }
            }
        }
    }
}
