package com.zygne.game.particles;

import android.util.Log;

import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.objects.RendableObject;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 08/09/2018.
 */
public class HairEmitter implements RendableObject {

    private static final String TAG = HairEmitter.class.getSimpleName();

    public static final int STATE_ALIVE = 0;    // at least 1 particle is alive
    public static final int STATE_DEAD = 1;    // all particles are dead

    private final int minSize = 20;
    private int maxSize = 10;
    private HairParticle[] particles;            // particles in the explosion
    private float x, y;                        // the explosion's origin
    private float gravity;                    // the gravity of the explosion (+ upward, - down)
    private float wind;                        // speed of wind on horizontal
    private int size;                        // number of particles
    private int state;                        // whether it's still active or not
    private int direction;

    public HairEmitter(int particleNr, float x, float y, int direction) {
        Log.d(TAG, "HairEmitter created at " + x + "," + y);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.state = STATE_ALIVE;

        if(particleNr < minSize){
            particleNr = minSize;
        }

        this.particles = new HairParticle[particleNr];

        for(int i = 0; i < minSize; i++) {
            HairParticle p = new HairParticle(x, y, direction);
            this.particles[i] = p;
        }

        this.size = 10;
        maxSize = particleNr;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    // helper methods -------------------------
    public boolean isAlive() {
        return this.state == STATE_ALIVE;
    }

    public boolean isDead() {
        return this.state == STATE_DEAD;
    }

    public void update(float deltaTime) {

        createNewParticle();

        for (int i = 0; i < size; i++) {
            if (this.particles[i].isAlive()) {
                this.particles[i].update(deltaTime);
            } else {
                this.particles[i].reset(x,y);
            }
        }

    }

    @Override
    public void render(GL10 gl, SpriteBatcher batcher) {
        for (int i = 0; i < size; i++) {
            this.particles[i].render(gl, batcher);
        }
    }

    private void createNewParticle(){

        if(size < maxSize) {
            HairParticle p = new HairParticle(x, y, direction);
            this.particles[size] = p;
        }
    }
}