package com.zygne.game.screens;

import com.zygne.game.Assets;
import com.zygne.game.framework.implementation.AmbientLight;
import com.zygne.game.framework.implementation.Animation;
import com.zygne.game.framework.implementation.DirectionalLight;
import com.zygne.game.framework.implementation.GLGraphics;
import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.implementation.TextureRegion;

import javax.microedition.khronos.opengles.GL10;

public class WorldRenderer {

    private float stateTime = 0;
	private GLGraphics glGraphics;
	private AmbientLight ambientLight;
	private DirectionalLight directionalLight;
	private SpriteBatcher batcher;

	public WorldRenderer(GLGraphics glGraphics) {
		this.glGraphics = glGraphics;
		ambientLight = new AmbientLight();
		ambientLight.setColor(0.2f, 0.2f, 0.2f, 1.0f);
		directionalLight = new DirectionalLight();
		directionalLight.setDirection(-1, -0.5f, 0);
		batcher = new SpriteBatcher(glGraphics, 10);
	}

	public void render(World world, float deltaTime) {
	    stateTime += deltaTime;

		GL10 gl = glGraphics.getGL();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.items);

        world.ball.render(gl, batcher);

        batcher.drawSprite(world.ball.position.x-5,
                world.ball.position.y + 173,
                8,
                256,
                Assets.ropeRegion);


        batcher.endBatch();

        if(world.explosion != null){
            if(world.explosion.isAlive()){
                world.explosion.render(gl, batcher);
            }
        }

        if(world.emitter != null) {
            world.emitter.render(gl, batcher);
        }

        TextureRegion frame = Assets.agitar.getKeyFrame(stateTime,
                Animation.ANIMATION_LOOPING);

        batcher.beginBatch(Assets.items);
        batcher.drawSprite(320 - 36, 36, 64, 64, frame);
        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
	}
}