package com.zygne.game.screens;

import android.util.Log;

import com.zygne.game.Assets;
import com.zygne.game.framework.Game;
import com.zygne.game.framework.controls.Input;
import com.zygne.game.framework.implementation.Camera2D;
import com.zygne.game.framework.implementation.FPSCounter;
import com.zygne.game.framework.implementation.GLScreen;
import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.math.Vector2;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 01/09/2018.
 */
public class GameScreen extends GLScreen {

    public static final int GAME_RUNNING = 0;
    public static final int GAME_PAUSED = 1;
    public static final int GAME_OVER = 2;

    private int state;
    private Camera2D guiCam;
    private Vector2 touchPoint;
    private SpriteBatcher batcher;
    private World world;
    private WorldRenderer renderer;
    private FPSCounter fpsCounter;

    public GameScreen(Game game) {
        super(game);

        state = GAME_RUNNING;
        guiCam = new Camera2D(glGraphics, Assets.SCREEN_WIDTH, Assets.SCREEN_HEIGHT);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 256);
        world = new World();
        renderer = new WorldRenderer(glGraphics);
        fpsCounter = new FPSCounter();
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

            world.onTouch((int)touchPoint.x, (int)touchPoint.y);
        }


        switch (state) {
            case GAME_PAUSED:
                updatePaused();
                break;
            case GAME_RUNNING:
                updateRunning(deltaTime);
                break;
            case GAME_OVER:
                updateGameOver();
                break;
        }

        fpsCounter.logFrame();
    }

    private void updateGameOver() {

    }

    private void updateRunning(float deltaTime) {

        world.update(deltaTime, 0);

        if (world.isGameOver()) {
            state = GAME_OVER;
        }
    }

    private void updatePaused() {

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

        renderer.render(world, deltaTime);

        batcher.beginBatch(Assets.textureUtil);
        batcher.drawSprite(Assets.SCREEN_WIDTH / 2,
                Assets.SCREEN_HEIGHT - 64,
                177,
                72,
                Assets.logo);
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

        world.onMovementDetected(acceleration);
        Log.d("GameScreen", "Movement detected " + acceleration);
    }
}
