package com.zygne.game.entities;

import android.util.Log;

import com.zygne.game.Assets;
import com.zygne.engine.implementation.SpriteBatcher;
import com.zygne.engine.implementation.TextureRegion;
import com.zygne.engine.core.objects.DynamicGameObject;
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
public class Ball extends DynamicGameObject implements com.zygne.engine.core.base.RenderableObject {

    private int level = 2;
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

         createEmitter(1);
    }

    public void setWorld(World world) {
        this.world = world;
    }


    public void update(float deltaTime) {

        updateMovement(deltaTime);

        if (explosion != null) {
            explosion.update(deltaTime);
        }

        if (emitter != null) {
            emitter.setX(position.x + 2);
            emitter.setY(position.y - 34);
            emitter.update(deltaTime);
        }
    }

    private void updateMovement(float dt) {
        position.add(velocity.x, 0);
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

    }

    @Override
    public void render(GL10 gl, SpriteBatcher batcher) {

        if (alive) {
            batcher.beginBatch(Assets.texturePinata);

            batcher.drawSprite(position.x,
                    position.y + 192,
                    4,
                    256,
                    Assets.rope);

            batcher.drawSprite(position.x,
                    position.y,
                    bounds.width,
                    bounds.height,
                    com.zygne.game.Assets.ball3);

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

        if (alive) {
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

    private void createEmitter(int level) {
        this.emitter = new Emitter(20 * level, position.x - 32, position.y - 78);
    }

    public boolean isDead() {
        return !alive;
    }
}
