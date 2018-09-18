package com.zygne.game.screens;

import com.zygne.game.Assets;
import com.zygne.game.entities.Arm;
import com.zygne.game.entities.Ball;
import com.zygne.game.entities.Speaker;
import com.zygne.game.framework.VibrationManager;
import com.zygne.game.particles.Explosion;

public class World {

    private float clock = 60;

    private float outroTimer = 0;
    public static final int STATE_INTRO = 0;
    public static final int STATE_GAME = 1;
    public static final int STATE_COMPLETED = 2;
    public static final int STATE_GAME_OVER = 3;

    public int currentState = STATE_INTRO;

    public float introTime = 3f;

    public Ball ball;
    public Arm arm;
    public Explosion explosion;
    public Speaker speaker;

    public World() {
        this.ball = new Ball(Assets.SCREEN_WIDTH / 2,
                (Assets.SCREEN_HEIGHT / 2) + 64,
                Assets.SCREEN_HEIGHT + 12,
                256,
                256);

        this.ball.setWorld(this);

        arm = new Arm(Assets.SCREEN_WIDTH - 80,
                180,
                200,
                420);

        speaker = new Speaker(80, 188 , 160, 56, Assets.comments);
    }

    public boolean isFinished() {

        switch (currentState) {
            case STATE_GAME_OVER:
                return true;
            case STATE_COMPLETED:
                return true;
            default:
                return false;
        }

    }

    public boolean isCompleted() {
        return currentState == STATE_COMPLETED;
    }

    public void update(float deltaTime, float accelX) {

        if (currentState == STATE_INTRO) {
            updateIntro(deltaTime);
        } else if (currentState == STATE_GAME) {
            updateGame(deltaTime);
        }
    }

    private void updateIntro(float dt) {

        introTime -= dt;

        if (introTime <= 0) {
            currentState = STATE_GAME;
        }
    }

    private void updateGame(float dt) {

        clock -= dt;

        ball.update(dt);
        arm.update(this, dt);

        if (explosion != null) {
            explosion.update(dt);
        }

        if (clock <= 0) {
            if (ball.isDead()) {
                this.currentState = STATE_COMPLETED;
            } else {
                this.currentState = STATE_GAME_OVER;
            }
        }

        if (ball.isDead()) {
            outroTimer+= dt;

            if(outroTimer > 1.5) {
                this.currentState = STATE_COMPLETED;

            }
        }

        speaker.update(dt);
    }

    public void hit(int level) {
        VibrationManager.getInstance().vibrate(level * 20);
        //Assets.playSound(Assets.crash);

        explosion = new Explosion(level * 5, ball.position.x, ball.position.y);

        speaker.sayComment();
    }


    public void onTouch(int x, int y) {
    }

    public void onMovementDetected(double acceleration) {

        if (currentState == STATE_GAME) {
            arm.hit(acceleration);
        }
    }

    public int getTime() {
        return (int) clock;
    }

}
