package com.zygne.game.entities;

import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.implementation.TextureRegion;
import com.zygne.game.framework.objects.DynamicGameObject;
import com.zygne.game.framework.objects.RendableObject;
import com.zygne.game.framework.objects.UpdatableObject;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 15/09/2018.
 */
public class ScaleObject extends DynamicGameObject implements
        RendableObject,
        UpdatableObject{

    private float scaleDirection = 0;
    private final float scalarUp = 1.01f;
    private final float scalarDown = 0.99f;
    private float currentScale = 100;
    private final TextureRegion textureRegion;

    public ScaleObject(float x, float y, float width, float height, TextureRegion textureRegion) {
        super(x, y, width, height);
        this.textureRegion = textureRegion;
    }

    @Override
    public void render(GL10 gl, SpriteBatcher batcher) {

        batcher.drawSprite(position.x,
                position.y,
                bounds.width,
                bounds.height,
                textureRegion);
    }

    @Override
    public void update(float deltaTime) {

        if(scaleDirection == 0){

            bounds.width *= scalarUp;
            bounds.height *= scalarUp;
            currentScale *= scalarUp;

            if(currentScale > 110){
                scaleDirection = 1;
            }

        } else {

            bounds.width *= scalarDown;
            bounds.height *= scalarDown;
            currentScale *= scalarDown;

            if(currentScale < 90){
                scaleDirection = 0;
            }
        }
    }
}
