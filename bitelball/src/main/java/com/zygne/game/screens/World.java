package com.zygne.game.screens;

import com.zygne.game.Assets;
import com.zygne.game.framework.VibrationManager;

public class World {

    public Ball ball;
    public Explosion explosion;
    public Emitter emitter;

	public World(){
	    this.ball = new Ball(160, 300, 450, 176, 176);

    }

	public boolean isGameOver(){
	    return false;
    }

    public void update(float deltaTime, float accelX) {
	    ball.update(this, deltaTime);

	    if(explosion != null){
	        explosion.update(deltaTime);
        }

        if(emitter != null) {
            emitter.setX(ball.position.x - 32);
            emitter.setY(ball.position.y - 82);
            emitter.update(deltaTime);
        }
    }

    public void hit(int level){
        VibrationManager.getInstance().vibrate(level * 20);
        Assets.playSound(Assets.crash);


        explosion = new Explosion(level*5, ball.position.x, ball.position.y);
    }

    public void createEmitter(){
        this.emitter = new Emitter(24,ball.position.x - 32, ball.position.y - 82);
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
