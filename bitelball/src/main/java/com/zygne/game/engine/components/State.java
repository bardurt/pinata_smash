package com.zygne.game.engine.components;

/**
 * Created by Bardur on 12/06/2016.
 */
public class State {

    public String currentState="";
    public Entity entity;

    public State(Entity entity){
        this.entity = entity;
    }
    public void setState(String state){
        this.currentState=state;
    }
    public String getCurrentState(){
        return this.currentState;
    }
}
