package com.zygne.game.engine.components;

/**
 * Created by Bardur on 12/09/2016.
 */
public class Bonus{

    public Entity entity;
    public int points;

    public Bonus(Entity entity){
        this.entity = entity;
        points=0;
    }

    public void increment(int point){
        points+=point;
    }
}
