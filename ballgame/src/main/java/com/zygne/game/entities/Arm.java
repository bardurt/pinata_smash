package com.zygne.game.entities;

import com.zygne.game.Assets;
import com.zygne.engine.implementation.SpriteBatcher;
import com.zygne.engine.core.objects.DynamicGameObject;
import com.zygne.game.screens.World;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 09/09/2018.
 */
public class Arm extends DynamicGameObject implements com.zygne.engine.core.base.RenderableObject {

    private float x;
    private float y;
    private float width;
    private float height;
    private boolean hitting;
    private int angle = 0;
    private double force;

    public Arm(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitting = false;
    }

    public void update(World world, float deltaTime) {

        if(hitting){
            updateHitting();
            if(angle >= 45){
                hitting = false;
                world.ball.hit(force);
            }
        } else {

            if(angle > 0) {
                angle -= 10;
                if(angle < 0){
                    angle = 0;
                    hitting = false;
                }
            }
        }

    }

    private void updateHitting(){

        angle += force;
    }

    public void hit(double force){
        if(!hitting) {

            if(force < 15) {
                this.force = force;
            } else {
                this.force = 15;
            }

            this.hitting = true;
        }
    }

    @Override
    public void render(GL10 gl, SpriteBatcher batcher) {

        batcher.beginBatch(Assets.texturePinata);

        batcher.drawSprite(x,
                y,
                width,
                height,
                angle,
                Assets.arm);

        batcher.endBatch();
    }
}
