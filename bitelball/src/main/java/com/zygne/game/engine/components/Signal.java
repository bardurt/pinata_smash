package com.zygne.game.engine.components;

/**
 * Created by Bardur on 12/06/2016.
 */
public class Signal {

    public Entity entity;
    public String message="";

    public Signal(Entity entity){
        this.entity = entity;
    }

    public void dispatch(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public void resetMessage(){
        this.message="";
    }
}
