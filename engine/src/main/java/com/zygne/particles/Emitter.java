package com.zygne.particles;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 05/09/2018.
 */
public class Emitter {

    private static final String TAG = Explosion.class.getSimpleName();

    public static final int STATE_ALIVE = 0;    // at least 1 particle is alive
    public static final int STATE_DEAD = 1;    // all particles are dead

    private final int minSize = 10;
    private int maxSize = 10;
    protected FallingParticle[] particles;            // particles in the explosion
    private float x, y;                        // the explosion's origin
    private float gravity;                    // the gravity of the explosion (+ upward, - down)
    private float wind;                        // speed of wind on horizontal
    private int size;                        // number of particles
    private int state;                        // whether it's still active or not

    int lastUpdate = 0;

    public Emitter(int particleNr, float x, float y) {
        android.util.Log.d(TAG, "Explosion created at " + x + "," + y);
        this.x = x;
        this.y = y;
        this.state = STATE_ALIVE;

        if(particleNr < minSize){
            particleNr = minSize;
        }

        this.particles = new FallingParticle[particleNr];

        for(int i = 0; i < minSize; i++) {
            FallingParticle p = new FallingParticle(x, y);
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

    private void createNewParticle(){

        if(size < maxSize) {

            for(int i = 0; i < 5; i++) {
                if(size < maxSize) {
                    FallingParticle p = new FallingParticle(x, y);
                    this.particles[size] = p;
                    size++;
                }
            }
        }
    }
}