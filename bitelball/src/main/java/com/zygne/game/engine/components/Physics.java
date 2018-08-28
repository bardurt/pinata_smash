package com.zygne.game.engine.components;

/**
 * Created by Bardur on 11/06/2016.
 */
public class Physics {

    public Entity entity;
    public float drag = 1;
    public int velocityX =0;
    public int velocityY =0;

    public Physics(Entity entity){
        this.entity = entity;
    }

    public void update(){

        this.entity.collider.setPosY(this.entity.collider.getPosX() + velocityX);
        this.entity.collider.setPosY(this.entity.collider.getPosY() + velocityY);

        velocityX*=drag;
        velocityY*=drag;
    }

    public void thrust(int power){
        velocityX += power;
    }

}
