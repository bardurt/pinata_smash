package com.zygne.game.entities;

import android.util.Log;

import com.zygne.game.Assets;
import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.implementation.TextureRegion;
import com.zygne.game.framework.objects.DynamicGameObject;
import com.zygne.game.framework.objects.RendableObject;
import com.zygne.game.particles.Emitter;
import com.zygne.game.particles.Explosion;
import com.zygne.game.screens.World;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 01/09/2018.
 */
public class Ball extends DynamicGameObject implements RendableObject {

    private int level = 0;
    private int lives = 150;
    private World world;
    private Explosion explosion;

    private final double gravity = -9.81;
    private final int speed = 112;
    private final int anchorX;
    private final int anchorY;
    private float angleAccel = 0f;
    private float angleVelocity = 0f;
    private float damping = 0.995f;
    public float angle = 270f;
    private int length;
    boolean alive = true;
    public Emitter emitter;

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

        if(explosion != null){
            explosion.update(deltaTime);
        }

        if(emitter != null) {
            emitter.setX(position.x + 2);
            emitter.setY(position.y - 34);
            emitter.update(deltaTime);
        }
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

        Log.d("Ball", "AngelVel: " + angleVelocity);

        if (lives < 10) {
            level = 6;
        } else if (lives < 25) {
            level = 5;
        } else if (lives < 45) {
            level = 4;
        } else if (lives < 60) {
            level = 3;
        } else if (lives < 85) {
            level = 2;
        } else if (lives < 100) {
            level = 1;
        }
    }


    public float getAnchorXOffset() {
        return anchorX + position.x;
    }

    @Override
    public void render(GL10 gl, SpriteBatcher batcher) {

        if(alive) {
            batcher.beginBatch(Assets.texturePinata);

            TextureRegion textureRegion = null;

            if(level == 0){
                textureRegion = Assets.ball1;
            } else if (level == 1){
                textureRegion = Assets.ball2;
            } else if (level == 2){
                textureRegion = Assets.ball3;
            } else if (level == 3){
                textureRegion = Assets.ball4;
            } else if (level == 4){
                textureRegion = Assets.ball5;
            } else if (level == 5){
                textureRegion = Assets.ball6;
            } else if (level == 6){
                textureRegion = Assets.ball7;
            }

            batcher.drawSprite(position.x,
                    position.y,
                    bounds.width,
                    bounds.height,
                    textureRegion);

            batcher.drawSprite(world.ball.position.x - 14,
                    world.ball.position.y + 192,
                    4,
                    256,
                    Assets.rope);

            batcher.endBatch();

            if (emitter != null) {
                emitter.render(gl, batcher);
            }

            Assets.font.drawText(batcher, "L: " + lives, Assets.SCREEN_WIDTH / 2, Assets.SCREEN_HEIGHT / 2);

        } else {
            if (explosion != null) {
                explosion.render(gl, batcher);
            }
        }
    }

    public void hit(double acceleration) {

        if(alive) {
            float diff = position.x - anchorX;

            if (Math.abs(diff) < (bounds.width / 3)) {

                int acc = (int) acceleration;
                Log.d("BALL", "Thrust: " + acceleration);

                if (acc > 3 && acc < 10) {
                    angleVelocity -= 0.2f;
                    world.hit(5);
                    lives -= 1;
                } else if (acceleration >= 10 && acc < 13) {
                    angleVelocity -= 0.35f;
                    world.hit(10);
                    lives -= 2;
                } else if (acceleration >= 13) {
                    angleVelocity -= 0.5f;
                    world.hit(15);
                    lives -= 2;
                }

                if (lives < 0) {
                    alive = false;
                    explosion = new Explosion(200, position.x, position.y);
                } else if (lives < 15) {
                    createEmitter(5);
                } else if (lives < 30) {
                    createEmitter(2);
                } else if (lives < 50) {
                    createEmitter(1);
                }
            }
        }
    }

    private void createEmitter(int level){
        this.emitter = new Emitter(20*level,position.x - 32, position.y - 78);
    }

    public boolean isDead(){
        return !alive;
    }
}
