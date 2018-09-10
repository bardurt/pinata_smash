package com.zygne.game.screens;

import com.zygne.game.Assets;
import com.zygne.game.framework.implementation.AmbientLight;
import com.zygne.game.framework.implementation.Animation;
import com.zygne.game.framework.implementation.DirectionalLight;
import com.zygne.game.framework.implementation.GLGraphics;
import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.implementation.TextureRegion;

import javax.microedition.khronos.opengles.GL10;

public class WorldRenderer {

    private float stateTime = 0;
    private GLGraphics glGraphics;
    private AmbientLight ambientLight;
    private DirectionalLight directionalLight;
    private SpriteBatcher batcher;

    public WorldRenderer(GLGraphics glGraphics) {
        this.glGraphics = glGraphics;
        ambientLight = new AmbientLight();
        ambientLight.setColor(0.2f, 0.2f, 0.2f, 1.0f);
        directionalLight = new DirectionalLight();
        directionalLight.setDirection(-1, -0.5f, 0);
        batcher = new SpriteBatcher(glGraphics, 10);
    }

    public void render(World world, float deltaTime) {

        if (world.currentState == World.STATE_INTRO) {
            renderIntro(world);
        } else {
            renderGame(world, deltaTime);
        }
    }

    private void renderIntro(World world) {

        float time = world.introTime;

        TextureRegion textureRegion = Assets.intro3;

        if (time <= 2 && time > 1) {
            textureRegion = Assets.intro2;
        } else if (time <= 1) {
            textureRegion = Assets.intro1;
        }

        batcher.beginBatch(Assets.textureUtil);

        batcher.drawSprite(
                Assets.SCREEN_WIDTH / 2,
                Assets.SCREEN_HEIGHT / 2,
                64,
                128,
                textureRegion);

        batcher.endBatch();
    }

    private void renderGame(World world, float deltaTime) {
        stateTime += deltaTime;

        GL10 gl = glGraphics.getGL();
        batcher.beginBatch(Assets.textureBall);

        world.ball.render(gl, batcher);

        if (world.hairEmitter4 != null) {
            world.hairEmitter4.render(gl, batcher);
        }

        batcher.drawSprite(world.ball.position.x - 4,
                world.ball.position.y + 192,
                8,
                256,
                Assets.rope);

        batcher.endBatch();

        if (world.explosion != null) {
            if (world.explosion.isAlive()) {
                world.explosion.render(gl, batcher);
            }
        }

        if (world.emitter != null) {
            world.emitter.render(gl, batcher);
        }

        if (world.hairEmitter1 != null) {
            world.hairEmitter1.render(gl, batcher);
        }

        if (world.hairEmitter2 != null) {
            world.hairEmitter2.render(gl, batcher);
        }

        if (world.hairEmitter3 != null) {
            world.hairEmitter3.render(gl, batcher);
        }


        if (world.hairEmitter5 != null) {
            world.hairEmitter5.render(gl, batcher);
        }

        world.arm.render(gl, batcher);

        renderMenu();
    }

    private void renderMenu(){

        TextureRegion frame = Assets.agitar.getKeyFrame(stateTime,
                Animation.ANIMATION_LOOPING);

        batcher.beginBatch(Assets.textureUtil);

        batcher.drawSprite(Assets.SCREEN_WIDTH - (72-26),
                64,
                72,
                74,
                Assets.agitarContainer);

        batcher.drawSprite(Assets.SCREEN_WIDTH - (56),
                96,
                72,
                74,
                frame);

        batcher.drawSprite(46,
                64,
                72,
                74,
                Assets.timerContainer);

        batcher.drawSprite(34,
                Assets.SCREEN_HEIGHT - 64,
                48,
                48,
                Assets.backButton);

        batcher.endBatch();
    }
}