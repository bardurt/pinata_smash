package com.zygne.game.engine.components;

/**
 * Created by Bardur on 11/06/2016.
 */
public class Health {

    public Entity entity;
    public int lives;

    public Health(Entity entity){
        this.entity = entity;
    }

    public void hit(int damage){
        lives-=damage;
        entity.signal.dispatch(Messages._HURT);

        if(lives < 0)
            entity.signal.dispatch(Messages._DESTROYED);
    }
}
