package com.zygne.engine;

import com.zygne.engine.core.base.Game;

public abstract class Screen {
    protected final Game game;

    public Screen(Game game) {
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void render(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();

    public abstract void onMovementDetected(double acceleration);

    public abstract void onMovementDetected(float dx, float dy);
}
