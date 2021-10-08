package com.zygne.game.entities;

import com.zygne.game.Assets;
import com.zygne.engine.implementation.SpriteBatcher;
import com.zygne.engine.implementation.TextureRegion;
import com.zygne.engine.core.objects.DynamicGameObject;
import com.zygne.engine.core.base.UpdatableObject;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 15/09/2018.
 */
public class Speaker extends DynamicGameObject implements
        com.zygne.engine.core.base.RenderableObject,
        UpdatableObject {

    private boolean isCommenting = false;
    private int currentComment;
    private TextureRegion[] comments;
    private float commentTime = 0f;
    private Random random;

    public Speaker(float x, float y, float width, float height, TextureRegion[] comments) {
        super(x, y, width, height);

        this.comments = comments;
        this.random = new Random();
    }

    @Override
    public void render(GL10 gl, SpriteBatcher batcher) {

        if (isCommenting) {

            if(currentComment < comments.length) {
                TextureRegion textureRegion = comments[currentComment];

                if (textureRegion != null) {
                    batcher.beginBatch(Assets.textureMenu);

                    batcher.drawSprite(position.x,
                            position.y,
                            bounds.width,
                            bounds.height,
                            textureRegion);

                    batcher.endBatch();
                }
            }
        }
    }

    @Override
    public void update(float deltaTime) {

        if (isCommenting) {
            commentTime += deltaTime;

            if (commentTime > 3f) {
                isCommenting = false;
                commentTime = 0;
            }
        }

    }

    public void sayComment() {

        if (!isCommenting) {
            isCommenting = true;
            currentComment = random.nextInt(comments.length +2);

            if (currentComment > comments.length) {
                isCommenting = false;
            }
        }
    }
}
