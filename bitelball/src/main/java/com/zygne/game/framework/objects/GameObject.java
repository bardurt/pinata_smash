package com.zygne.game.framework.objects;

import com.zygne.game.framework.math.Rectangle;
import com.zygne.game.framework.math.Vector2;

/**
 * Created by Bardur on 14/03/2017.
 */

public class GameObject {
    public final Vector2 position;
    public final Rectangle bounds;

    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x,y);
        this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
    }
}