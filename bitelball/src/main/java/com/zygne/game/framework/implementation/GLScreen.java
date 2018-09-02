package com.zygne.game.framework.implementation;

import com.zygne.game.framework.Game;
import com.zygne.game.framework.Screen;

/**
 * Created by Bardur on 14/03/2017.
 */
public abstract class GLScreen extends Screen {
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;

    public GLScreen(Game game) {
        super(game);
        glGame = (GLGame)game;
        glGraphics = ((GLGame)game).getGLGraphics();
    }

}
