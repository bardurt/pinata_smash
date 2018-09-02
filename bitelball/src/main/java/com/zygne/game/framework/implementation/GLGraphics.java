package com.zygne.game.framework.implementation;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Bardur on 14/03/2017.
 */

public class GLGraphics {
    GLSurfaceView glView;
    GL10 gl;
    GLGraphics(GLSurfaceView glView) {
        this.glView = glView;
    }
    public GL10 getGL() {
        return gl;
    }
    void setGL(GL10 gl) {
        this.gl = gl;
    }
    public int getWidth() {
        return glView.getWidth();
    }
    public int getHeight() {
        return glView.getHeight();
    }
}