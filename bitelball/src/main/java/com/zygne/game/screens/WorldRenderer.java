package com.zygne.game.screens;

import com.zygne.game.Assets;
import com.zygne.game.framework.implementation.AmbientLight;
import com.zygne.game.framework.implementation.DirectionalLight;
import com.zygne.game.framework.implementation.GLGraphics;
import com.zygne.game.framework.implementation.SpriteBatcher;

import javax.microedition.khronos.opengles.GL10;

public class WorldRenderer {

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
		GL10 gl = glGraphics.getGL();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.ball);
        batcher.drawSprite(world.ball.position.x,
                world.ball.position.y,
                world.ball.bounds.width,
                world.ball.bounds.height,
                Assets.ballRegion);

        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
	}
}