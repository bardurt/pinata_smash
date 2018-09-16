package com.zygne.game.particles;

import com.zygne.game.Assets;
import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.implementation.TextureRegion;
import com.zygne.game.framework.objects.RendableObject;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 05/09/2018.
 */
public class FallingParticle implements RendableObject {

    public static final int STATE_ALIVE = 0;    // particle is alive
    public static final int STATE_DEAD = 1;        // particle is dead

    public static final int DEFAULT_LIFETIME = 80;    // play with this
    public static int MAX_DIMENSION = 12;    // the maximum width or height
    public static final int MAX_SPEED = 2;    // maximum speed (per update)

    private int state;            // particle is alive or dead
    private float width;        // width of the particle
    private float height;        // height of the particle
    private float x, y;            // horizontal and vertical position
    private double xv, yv;        // vertical and horizontal velocity
    private int age;            // current age of the particle
    private int lifetime;        // particle dies when it reaches this value
    private int type = 0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
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

    public double getXv() {
        return xv;
    }

    public void setXv(double xv) {
        this.xv = xv;
    }

    public double getYv() {
        return yv;
    }

    public void setYv(double yv) {
        this.yv = yv;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    // helper methods -------------------------
    public boolean isAlive() {
        return this.state == STATE_ALIVE;
    }

    public boolean isDead() {
        return this.state == STATE_DEAD;
    }

    public FallingParticle(float x, float y) {
        this.x = x;
        this.y = y;
        this.state = Particle.STATE_ALIVE;
        this.width = rndInt(1, MAX_DIMENSION);
        this.height = this.width;
        this.lifetime = rndInt(20, DEFAULT_LIFETIME);
        this.age = 0;
        this.xv = (rndDbl(0, 2) - 1);
        this.yv = - (rndDbl(0, MAX_SPEED));
        type = rndInt(0,17);

    }

    // Return an integer that ranges from min inclusive to max inclusive.
    private static int rndInt(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }

    private static double rndDbl(double min, double max) {
        return min + (max - min) * Math.random();
    }

    public void update(float dt) {
        if (this.state != STATE_DEAD) {
            this.x += this.xv;
            this.y += this.yv;

            this.age++;                        // increase the age of the particle

//            this.width *= 0.99;
//            this.height *= 0.99;

            if (this.age >= this.lifetime) {    // reached the end if its life
                this.state = STATE_DEAD;
            }
        }
    }

    @Override
    public void render(GL10 gl, SpriteBatcher batcher) {
        if (this.state != STATE_DEAD) {

            TextureRegion texture = Assets.confettiList[type];

            batcher.beginBatch(Assets.textureMenu);
            batcher.drawSprite(x,
                    y,
                    width,
                    height,
                    texture);
            batcher.endBatch();
        }
    }

    public void reset(float x, float y){

        this.x = x;
        this.y = y;
        this.state = Particle.STATE_ALIVE;
        this.width = rndInt(1, MAX_DIMENSION);
        this.height = this.width;
        this.lifetime = rndInt(10, DEFAULT_LIFETIME);
        this.age = 0;
        this.xv = (rndDbl(0, 2) - 1);
        this.yv = -(rndDbl(0, MAX_SPEED));
        type = rndInt(0,17);
    }
}

