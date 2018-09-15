package com.zygne.game.screens;

import com.zygne.game.Assets;
import com.zygne.game.entities.Arm;
import com.zygne.game.entities.Ball;
import com.zygne.game.framework.VibrationManager;
import com.zygne.game.particles.Emitter;
import com.zygne.game.particles.Explosion;
import com.zygne.game.particles.HairEmitter;

public class World {

    private float clock = 60;

    private float timer = 0;
    public static final int STATE_INTRO = 0;
    public static final int STATE_GAME = 1;
    public static final int STATE_FINISH = 2;

    public int currentState = STATE_INTRO;

    public float introTime = 3f;

    public Ball ball;
    public Arm arm;
    public Explosion explosion;
    public Emitter emitter;
    public HairEmitter hairEmitter1;
    public HairEmitter hairEmitter2;
    public HairEmitter hairEmitter3;
    public HairEmitter hairEmitter4;
    public HairEmitter hairEmitter5;

	public World(){
	    this.ball = new Ball(Assets.SCREEN_WIDTH / 2,
                (Assets.SCREEN_HEIGHT /2) + 64,
                Assets.SCREEN_HEIGHT + 12,
                256,
                256);

	    this.ball.setWorld(this);

        arm = new Arm(Assets.SCREEN_WIDTH - 80,
                180,
                200,
                420);

//	    hairEmitter1 = new HairEmitter(128,
//                Assets.SCREEN_WIDTH / 2,
//                Assets.SCREEN_HEIGHT / 2,
//                0);
//        hairEmitter1.setX(ball.position.x - 32);
//        hairEmitter1.setY(ball.position.y - 82);
//
//        hairEmitter2 = new HairEmitter(128,
//                Assets.SCREEN_WIDTH / 2,
//                Assets.SCREEN_HEIGHT / 2,
//                1);
//        hairEmitter2.setX(ball.position.x - 32);
//        hairEmitter2.setY(ball.position.y - 82);
//
//        hairEmitter3 = new HairEmitter(128,
//                Assets.SCREEN_WIDTH / 2,
//                Assets.SCREEN_HEIGHT / 2,
//                1);
//        hairEmitter3.setX(ball.position.x - 32);
//        hairEmitter3.setY(ball.position.y - 82);
//
//        hairEmitter4 = new HairEmitter(32,
//                Assets.SCREEN_WIDTH / 2,
//                Assets.SCREEN_HEIGHT / 2,
//                1);
//        hairEmitter4.setX(ball.position.x - 32);
//        hairEmitter4.setY(ball.position.y - 82);
//
//        hairEmitter5 = new HairEmitter(128,
//                Assets.SCREEN_WIDTH / 2,
//                Assets.SCREEN_HEIGHT / 2,
//                0);
//        hairEmitter5.setX(ball.position.x - 32);
//        hairEmitter5.setY(ball.position.y - 82);

    }

	public boolean isGameOver(){
	    return currentState == STATE_FINISH;
    }

    public void update(float deltaTime, float accelX) {

	    if(currentState == STATE_INTRO){
	        updateIntro(deltaTime);
        } else if(currentState == STATE_GAME) {
	        updateGame(deltaTime);
        }
    }

    private void updateIntro(float dt){

	    introTime -= dt;

	    if(introTime <= 0 ){
	        currentState = STATE_GAME;
        }
    }

    private void updateGame(float dt){

	    clock -= dt;

        ball.update(dt);
        arm.update(this, dt);

        if(explosion != null){
            explosion.update(dt);
        }

        if(emitter != null) {
            emitter.setX(ball.position.x + 10);
            emitter.setY(ball.position.y - 40);
            emitter.update(dt);
        }

        if(hairEmitter1 != null){
            hairEmitter1.setX(ball.position.x - 68);
            hairEmitter1.setY(ball.position.y - 82);
            hairEmitter1.update(dt);
        }

        if(hairEmitter2 != null){
            hairEmitter2.setX(ball.position.x + 80);
            hairEmitter2.setY(ball.position.y - 82);
            hairEmitter2.update(dt);
        }

        if(hairEmitter3 != null){
            hairEmitter3.setX(ball.position.x + 66);
            hairEmitter3.setY(ball.position.y + 103);
            hairEmitter3.update(dt);
        }

        if(hairEmitter4 != null){
            hairEmitter4.setX(ball.position.x + 16);
            hairEmitter4.setY(ball.position.y + 110);
            hairEmitter4.update(dt);
        }

        if(hairEmitter5 != null){
            hairEmitter5.setX(ball.position.x - 76);
            hairEmitter5.setY(ball.position.y + 82);
            hairEmitter5.update(dt);
        }

        if(clock <= 0){
            this.currentState = STATE_FINISH;
        }
    }

    public void hit(int level){
        VibrationManager.getInstance().vibrate(level * 20);
        //Assets.playSound(Assets.crash);

        explosion = new Explosion(level*5, ball.position.x, ball.position.y);
    }

    public void createEmitter(int level){
        this.emitter = new Emitter(60*level,ball.position.x - 32, ball.position.y - 82);
    }

    public void onTouch(int x, int y){
	    if(x >= ball.position.x + ball.bounds.width/2){
	        ball.thrustLeft();
        } else if(x <= ball.position.x - ball.bounds.width/2){
	        ball.thrustRight();
        }
    }

    public void onMovementDetected(double acceleration){

	    if(currentState == STATE_GAME) {
            ball.thrust(this, acceleration);
            arm.hit(acceleration);
        }
    }

    public int getTime(){
	    return (int) clock;
    }
}
