package com.zygne.game.engine.components;

/**
 * Created by Bardur on 16/08/2016.
 */
public class Score {

    public Entity entity;
    public int points;

    public Score(Entity entity){
        this.entity = entity;
        points=0;
    }

    public void increment(int point){
        points+=point;
    }
}
