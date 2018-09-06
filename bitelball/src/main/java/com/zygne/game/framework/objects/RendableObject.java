package com.zygne.game.framework.objects;

import com.zygne.game.framework.implementation.SpriteBatcher;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 03/09/2018.
 */
public interface RendableObject {

    void render(GL10 gl, SpriteBatcher batcher);
}
