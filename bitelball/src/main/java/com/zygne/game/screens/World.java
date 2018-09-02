package com.zygne.game.screens;

import com.zygne.game.Assets;
import com.zygne.game.framework.VibrationManager;

public class World {

    public Ball ball;

	public interface WorldListener {
		public void explosion();

		public void shot();
	}

	private WorldListener listener;

	public World(){
	    this.ball = new Ball(160, 300, 80, 80);
    }

	public void setWorldListener(WorldListener worldListener){
		this.listener = worldListener;
	}

	public boolean isGameOver(){
	    return false;
    }

    public void update(float deltaTime, float accelX) {

	    ball.update(this, deltaTime);

    }

    public void crash(){
        VibrationManager.getInstance().vibrate();
        Assets.playSound(Assets.crash);
    }

    public void hit(int vib){
        VibrationManager.getInstance().vibrate(vib);
    }

    public void onTouch(int x, int y){

	    if(x >= ball.position.x + ball.bounds.width/2){
	        ball.thrustLeft();
        } else if(x <= ball.position.x - ball.bounds.width/2){
	        ball.thrustRight();
        }

    }

    public void onMovementDetected(double acceleration){
        ball.thrust(this, acceleration);
    }
}
