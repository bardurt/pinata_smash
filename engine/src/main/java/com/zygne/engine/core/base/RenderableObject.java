package com.zygne.engine.core.base;

import com.zygne.engine.implementation.SpriteBatcher;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 03/09/2018.
 */
public interface RenderableObject {
    void render(GL10 gl, SpriteBatcher batcher);
}
