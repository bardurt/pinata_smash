package com.zygne.game.screens;

import com.zygne.game.Assets;
import com.zygne.game.entities.ScaleObject;
import com.zygne.game.framework.Game;
import com.zygne.game.framework.controls.Input;
import com.zygne.game.framework.implementation.Camera2D;
import com.zygne.game.framework.implementation.FPSCounter;
import com.zygne.game.framework.implementation.GLScreen;
import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.math.Vector2;
import com.zygne.game.particles.ExplosiveEmitter;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 15/09/2018.
 */
public class RewardScreen extends GLScreen {

    private Camera2D guiCam;
    private Vector2 touchPoint;
    private SpriteBatcher batcher;
    private FPSCounter fpsCounter;

    private ScaleObject felicidades;
    private ScaleObject reward;
    private ExplosiveEmitter explosiveEmitter;

    public RewardScreen(Game game) {
        super(game);

        guiCam = new Camera2D(glGraphics, Assets.SCREEN_WIDTH, Assets.SCREEN_HEIGHT);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 256);
        fpsCounter = new FPSCounter();

        felicidades = new ScaleObject(Assets.SCREEN_WIDTH / 2,
                Assets.SCREEN_HEIGHT - (144),
                275,
                66,
                Assets.felicidades);

        reward = new ScaleObject(Assets.SCREEN_WIDTH / 2,
                Assets.SCREEN_HEIGHT /2 + 20,
                50,
                144,
                Assets.iPhone);

        explosiveEmitter = new ExplosiveEmitter(128,
                Assets.SCREEN_WIDTH / 2,
                Assets.SCREEN_HEIGHT - (144));
    }

    @Override
    public void update(float deltaTime) {

        List<Input.TouchEvent> events = game.getInput().getTouchEvents();
        int len = events.size();

        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = events.get(i);

            if (event.type != Input.TouchEvent.TOUCH_DOWN) {
                continue;
            }

            guiCam.touchToWorld(touchPoint.set(event.x, event.y));
        }


        felicidades.update(deltaTime);
        reward.update(deltaTime);
        explosiveEmitter.update(deltaTime);


        fpsCounter.logFrame();
    }


    @Override
    public void render(float deltaTime) {

        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.background);
        batcher.drawSprite(Assets.SCREEN_WIDTH / 2,
                Assets.SCREEN_HEIGHT / 2,
                Assets.SCREEN_WIDTH,
                Assets.SCREEN_HEIGHT,
                Assets.backgroundRegion);
        batcher.endBatch();

        explosiveEmitter.render(gl, batcher);

        batcher.beginBatch(Assets.textureMenu);

        Assets.renderLogoAndBack(batcher);

        batcher.drawSprite((144/2) + 28,
                (54 / 2) + 36,
                144,
                54,
                Assets.buttonShare);

        batcher.drawSprite(Assets.SCREEN_WIDTH - ((144/2) + 28),
                (54 / 2) + 36,
                144,
                54,
                Assets.buttonHome);

        felicidades.render(gl, batcher);

        reward.render(gl, batcher);

        batcher.drawSprite(Assets.SCREEN_WIDTH / 2,
                148,
                (Assets.SCREEN_WIDTH - 56) ,
                128,
                Assets.textContainer);

        batcher.drawSprite(Assets.SCREEN_WIDTH / 2,
                206,
                250 ,
                50,
                Assets.hasGanado);

        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void onMovementDetected(double acceleration) {
    }
}

