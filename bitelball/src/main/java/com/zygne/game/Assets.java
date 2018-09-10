package com.zygne.game;


import com.zygne.game.framework.implementation.Animation;
import com.zygne.game.framework.implementation.Font;
import com.zygne.game.framework.implementation.GLGame;
import com.zygne.game.framework.implementation.Texture;
import com.zygne.game.framework.implementation.TextureRegion;
import com.zygne.game.framework.streams.Music;
import com.zygne.game.framework.streams.Sound;


public class Assets {

    public static final int SCREEN_WIDTH = 360;
    public static final int SCREEN_HEIGHT = 640;

    public static Texture background;
    public static Texture textureUtil;
    public static Texture textureBall;

    public static TextureRegion backgroundRegion;
    public static TextureRegion ball1;
    public static TextureRegion ball2;
    public static TextureRegion ball3;
    public static TextureRegion ball4;
    public static TextureRegion ball5;

    public static TextureRegion arm;

    public static TextureRegion logo;
    public static TextureRegion backButton;
    public static TextureRegion rope;
    public static TextureRegion timerContainer;
    public static TextureRegion agitarContainer;

    public static TextureRegion hairParticle;

    public static TextureRegion explosionParticle;

    public static TextureRegion confetti1;
    public static TextureRegion confetti2;
    public static TextureRegion confetti3;
    public static TextureRegion confetti4;
    public static TextureRegion confetti5;
    public static TextureRegion confetti6;
    public static TextureRegion confetti7;
    public static TextureRegion confetti8;
    public static TextureRegion confetti9;
    public static TextureRegion confetti10;

    public static TextureRegion intro3;
    public static TextureRegion intro2;
    public static TextureRegion intro1;

    public static Font font;
    public static Music music;
    public static Sound crash;

    public static Animation agitar;

    public static void load(GLGame game) {
        background = new Texture(game, "background.jpg", true);
        backgroundRegion = new TextureRegion(background, 0, 0, 540, 960);

        textureUtil = new Texture(game, "sheet_items.png", true);
        textureBall = new Texture(game, "sheet_ball.png", true);

        rope = new TextureRegion(textureBall, 987, 0, 44, 300);

        ball1 = new TextureRegion(textureBall, 1, 0, 409, 321);
        ball2 = new TextureRegion(textureBall, 419, 0, 409, 321);
        ball3 = new TextureRegion(textureBall, 1, 321, 409, 321);
        ball4 = new TextureRegion(textureBall, 419, 321, 409, 321);
        ball5 = new TextureRegion(textureBall, 1, 645, 409, 321);

        arm = new TextureRegion(textureBall, 768, 606, 256, 361);

        logo = new TextureRegion(textureUtil, 0,879, 276, 145);
        backButton = new TextureRegion(textureUtil, 551,919,112,112);
        timerContainer = new TextureRegion(textureUtil, 879,601,145,150);
        agitarContainer = new TextureRegion(textureUtil, 734,601,145,150);

        hairParticle = new TextureRegion(textureBall, 720, 997, 8, 8);
        explosionParticle = new TextureRegion(textureBall, 693, 993, 19, 19);
        confetti1 = new TextureRegion(textureBall, 736, 990, 11, 11);
        confetti2 = new TextureRegion(textureUtil, 747, 990, 11, 11);
        confetti3 = new TextureRegion(textureUtil, 760, 990, 8, 8);
        confetti4 = new TextureRegion(textureUtil, 810, 990, 12, 12);
        confetti5 = new TextureRegion(textureUtil, 857, 990, 18, 33);
        confetti6 = new TextureRegion(textureUtil, 980, 990, 14, 21);
        confetti7 = new TextureRegion(textureUtil, 844, 990, 18, 33);
        confetti8 = new TextureRegion(textureUtil, 857, 990, 18, 33);
        confetti9 = new TextureRegion(textureUtil, 857, 990, 18, 33);
        confetti10 = new TextureRegion(textureUtil, 857, 990, 18, 33);

        intro1 = new TextureRegion(textureUtil, 0, 0, 204, 249);
        intro2 = new TextureRegion(textureUtil, 205, 0, 204, 249);
        intro3 = new TextureRegion(textureUtil, 410, 0, 204, 249);

        crash = game.getAudio().newSound("crash.ogg");

        TextureRegion[] keyFrames = new TextureRegion[2];
        int frame = 0;

        for (int x = 0; x < 302; x += 151) {
            keyFrames[frame++] = new TextureRegion(textureUtil, x, 547,
                    151, 181);
        }

        agitar = new Animation(0.2f, keyFrames);
    }

    public static void reload() {
        background.reload();
        textureUtil.reload();
        textureBall.reload();
    }



    public static void playSound(Sound sound) {
        if (Settings.soundEnabled) {
            sound.play(1);
        }
    }
}
