package com.zygne.engine.core.objects;

import com.zygne.engine.core.math.Vector2;

/**
 * Created by Bardur on 14/03/2017.
 */

public class DynamicGameObject extends GameObject {
    public final Vector2 velocity;
    public final Vector2 accel;
    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }
}