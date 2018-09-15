package com.zygne.game;


import com.zygne.game.framework.implementation.Animation;
import com.zygne.game.framework.implementation.Font;
import com.zygne.game.framework.implementation.GLGame;
import com.zygne.game.framework.implementation.SpriteBatcher;
import com.zygne.game.framework.implementation.Texture;
import com.zygne.game.framework.implementation.TextureRegion;
import com.zygne.game.framework.streams.Music;
import com.zygne.game.framework.streams.Sound;


public class Assets {

    public static final int PADDING_HORIZONTAL = 28;
    public static final int PADDING_VERTICAL = 36;
    public static final int SCREEN_WIDTH = 360;
    public static final int SCREEN_HEIGHT = 640;

    public static Texture background;
    public static Texture textureUtil;
    public static Texture textureBall;
    public static Texture texturePinata;

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

    public static Texture textureMenu;
    public static TextureRegion buttonHome;
    public static TextureRegion buttonShare;
    public static TextureRegion buttonBack;
    public static TextureRegion felicidades;
    public static TextureRegion hasGanado;
    public static TextureRegion textContainer;
    public static TextureRegion fiestaLogo;
    public static TextureRegion iPhone;

    public static Font font;
    public static Music music;
    public static Sound crash;

    public static Animation agitar;

    public static void load(GLGame game) {
        background = new Texture(game, "background.jpg", true);
        backgroundRegion = new TextureRegion(background, 0, 0, 540, 960);

        textureUtil = new Texture(game, "sheet_items.png", true);
        textureBall = new Texture(game, "sheet_ball.png", true);
        texturePinata = new Texture(game, "pinata.png", false);
        textureMenu = new Texture(game, "assets_menu.png", true);

        rope = new TextureRegion(textureBall, 987, 0, 44, 300);

        ball1 = new TextureRegion(texturePinata, 0, 0, 326, 256);
        ball2 = new TextureRegion(texturePinata, 326, 0, 326, 256);
        ball3 = new TextureRegion(texturePinata, 653, 0, 326, 256);
        ball4 = new TextureRegion(texturePinata, 980, 512, 326, 256);
        ball5 = new TextureRegion(texturePinata, 1, 645, 326, 256);

        arm = new TextureRegion(textureBall, 768, 606, 256, 361);

        logo = new TextureRegion(textureUtil, 0,879, 276, 145);
        backButton = new TextureRegion(textureUtil, 551,919,112,112);

        hairParticle = new TextureRegion(textureBall, 720, 997, 8, 8);
        explosionParticle = new TextureRegion(textureBall, 693, 993, 19, 19);
        confetti1 = new TextureRegion(textureBall, 736, 990, 11, 11);
        confetti2 = new TextureRegion(textureBall, 747, 990, 11, 11);
        confetti3 = new TextureRegion(textureBall, 760, 990, 8, 8);
        confetti4 = new TextureRegion(textureBall, 810, 990, 12, 12);
        confetti5 = new TextureRegion(textureBall, 857, 990, 18, 33);
        confetti6 = new TextureRegion(textureBall, 980, 990, 14, 21);
        confetti7 = new TextureRegion(textureBall, 844, 990, 18, 33);
        confetti8 = new TextureRegion(textureBall, 857, 990, 18, 33);
        confetti9 = new TextureRegion(textureBall, 857, 990, 18, 33);
        confetti10 = new TextureRegion(textureBall, 857, 990, 18, 33);

        intro1 = new TextureRegion(textureUtil, 0, 0, 204, 249);
        intro2 = new TextureRegion(textureUtil, 205, 0, 204, 249);
        intro3 = new TextureRegion(textureUtil, 410, 0, 204, 249);

        font = new Font(textureUtil, 758, 0, 16, 16, 20, 96);

        crash = game.getAudio().newSound("crash.ogg");




        buttonShare = new TextureRegion(textureMenu, 0,0, 656,296);
        buttonHome = new TextureRegion(textureMenu, 0,296, 656,296);
        buttonBack = new TextureRegion(textureMenu, 1196,0, 200,200);
        felicidades = new TextureRegion(textureMenu, 0,592, 1208,364);
        hasGanado = new TextureRegion(textureMenu, 0, 956, 1104, 308);
        textContainer = new TextureRegion(textureMenu, 0, 1264, 1336, 596);
        fiestaLogo = new TextureRegion(textureMenu, 656, 0, 540, 268);
        iPhone =  new TextureRegion(textureMenu, 1895, 0, 153, 436);
        timerContainer = new TextureRegion(textureMenu, 1913,1904,135,145);
        agitarContainer = new TextureRegion(textureMenu, 1778,1904,135,145);

        TextureRegion[] keyFrames = new TextureRegion[2];
        int frame = 0;

        for (int x = 1744; x < 2048; x += 152) {
            keyFrames[frame++] = new TextureRegion(textureMenu, x, 1748,
                    152, 156);
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

    public static void renderLogoAndBack(SpriteBatcher batcher){

        batcher.drawSprite(34,
                Assets.SCREEN_HEIGHT - 64,
                56,
                56,
                Assets.buttonBack);

        batcher.drawSprite(Assets.SCREEN_WIDTH / 2,
                Assets.SCREEN_HEIGHT - 64,
                177,
                72,
                Assets.fiestaLogo);
    }

    public static void renderLogo(SpriteBatcher batcher){

        batcher.drawSprite(Assets.SCREEN_WIDTH / 2,
                Assets.SCREEN_HEIGHT - 64,
                177,
                72,
                Assets.fiestaLogo);
    }

    public static void renderBack(SpriteBatcher batcher){

        batcher.drawSprite(34,
                Assets.SCREEN_HEIGHT - 64,
                56,
                56,
                Assets.buttonBack);

    }
}
