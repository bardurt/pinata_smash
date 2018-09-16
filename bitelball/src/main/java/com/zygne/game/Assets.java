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
    public static Texture texturePinata;

    public static TextureRegion backgroundRegion;
    public static TextureRegion ball1;
    public static TextureRegion ball2;
    public static TextureRegion ball3;
    public static TextureRegion ball4;
    public static TextureRegion ball5;
    public static TextureRegion ball6;
    public static TextureRegion ball7;

    public static TextureRegion arm;

    public static TextureRegion rope;
    public static TextureRegion timerContainer;
    public static TextureRegion agitarContainer;

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
    public static TextureRegion confetti11;
    public static TextureRegion confetti12;
    public static TextureRegion confetti13;
    public static TextureRegion confetti14;
    public static TextureRegion confetti15;
    public static TextureRegion confetti16;
    public static TextureRegion confetti17;
    public static TextureRegion confetti18;

    public static TextureRegion comment1;
    public static TextureRegion comment2;
    public static TextureRegion comment3;

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

    public static TextureRegion[] confettiList = new TextureRegion[18];
    public static TextureRegion[] comments = new TextureRegion[3];

    public static void load(GLGame game) {
        background = new Texture(game, "background.jpg", true);
        backgroundRegion = new TextureRegion(background, 0, 0, 540, 960);

        texturePinata = new Texture(game, "pinata.png", true);
        textureMenu = new Texture(game, "assets_menu.png", true);

        rope = new TextureRegion(texturePinata, 1361, 0, 32, 300);

        ball1 = new TextureRegion(texturePinata, 0, 0, 340, 255);
        ball2 = new TextureRegion(texturePinata, 340, 0, 340, 255);
        ball3 = new TextureRegion(texturePinata, 680, 0, 340, 255);
        ball4 = new TextureRegion(texturePinata, 1020, 0, 340, 255);
        ball5 = new TextureRegion(texturePinata, 0, 255, 340, 255);
        ball6 = new TextureRegion(texturePinata, 340, 255, 340, 255);
        ball7 = new TextureRegion(texturePinata, 680, 255, 340, 255);

        arm = new TextureRegion(texturePinata, 1393, 0, 258, 356);

        intro1 = new TextureRegion(textureMenu, 1196, 200, 204, 249);
        intro2 = new TextureRegion(textureMenu, 1400, 200, 204, 249);
        intro3 = new TextureRegion(textureMenu, 1604, 200, 204, 249);

        crash = game.getAudio().newSound("crash.ogg");

        buttonShare = new TextureRegion(textureMenu, 0, 0, 656, 296);
        buttonHome = new TextureRegion(textureMenu, 0, 296, 656, 296);
        buttonBack = new TextureRegion(textureMenu, 1196, 0, 200, 200);
        felicidades = new TextureRegion(textureMenu, 0, 592, 1208, 364);
        hasGanado = new TextureRegion(textureMenu, 0, 956, 1104, 308);
        textContainer = new TextureRegion(textureMenu, 0, 1264, 1336, 596);
        fiestaLogo = new TextureRegion(textureMenu, 656, 0, 540, 268);
        iPhone = new TextureRegion(textureMenu, 1895, 0, 153, 436);
        timerContainer = new TextureRegion(textureMenu, 1913, 1904, 135, 145);
        agitarContainer = new TextureRegion(textureMenu, 1778, 1904, 135, 145);
        font = new Font(textureMenu, 656, 268, 16, 16, 30, 96);

        TextureRegion[] keyFrames = new TextureRegion[2];
        int frame = 0;

        for (int x = 1744; x < 2048; x += 152) {
            keyFrames[frame++] = new TextureRegion(textureMenu, x, 1748,
                    152, 156);
        }

        agitar = new Animation(0.2f, keyFrames);

        confetti1 = new TextureRegion(textureMenu, 1457, 6, 9, 9);
        confetti2 = new TextureRegion(textureMenu, 1467, 5, 9, 9);
        confetti3 = new TextureRegion(textureMenu, 1480, 5, 5, 5);
        confetti4 = new TextureRegion(textureMenu, 1488, 6, 6, 6);
        confetti5 = new TextureRegion(textureMenu, 1498, 6, 5, 5);
        confetti6 = new TextureRegion(textureMenu, 1505, 6, 10, 9);
        confetti7 = new TextureRegion(textureMenu, 1520, 6, 6, 6);
        confetti8 = new TextureRegion(textureMenu, 1530, 6, 10, 10);
        confetti9 = new TextureRegion(textureMenu, 1544, 6, 5, 6);
        confetti10 = new TextureRegion(textureMenu, 1554, 5, 6, 6);
        confetti11 = new TextureRegion(textureMenu, 1564, 6, 10, 11);
        confetti12 = new TextureRegion(textureMenu, 1578, 6, 14, 32);
        confetti13 = new TextureRegion(textureMenu, 1595, 6, 13, 13);
        confetti14 = new TextureRegion(textureMenu, 1612, 6, 6, 6);
        confetti15 = new TextureRegion(textureMenu, 1622, 6, 5, 5);
        confetti16 = new TextureRegion(textureMenu, 1628, 4, 12, 11);
        confetti17 = new TextureRegion(textureMenu, 1624, 7, 6, 5);
        confetti18 = new TextureRegion(textureMenu, 1651, 5, 12, 11);

        confettiList[0] = confetti1;
        confettiList[1] = confetti2;
        confettiList[2] = confetti3;
        confettiList[3] = confetti4;
        confettiList[4] = confetti5;
        confettiList[5] = confetti6;
        confettiList[6] = confetti7;
        confettiList[7] = confetti8;
        confettiList[8] = confetti9;
        confettiList[9] = confetti10;
        confettiList[10] = confetti11;
        confettiList[11] = confetti12;
        confettiList[12] = confetti13;
        confettiList[13] = confetti14;
        confettiList[14] = confetti15;
        confettiList[15] = confetti16;
        confettiList[16] = confetti17;
        confettiList[17] = confetti18;

        comment1 = new TextureRegion(textureMenu, 0, 1969, 177, 79);
        comment2 = new TextureRegion(textureMenu, 177, 1969, 177, 79);
        comment3 = new TextureRegion(textureMenu, 354, 1969, 177, 79);

        comments[0] = comment1;
        comments[1] = comment2;
        comments[2] = comment3;

    }

    public static void reload() {
        background.reload();
        texturePinata.reload();
        textureMenu.reload();
    }

    public static void playSound(Sound sound) {
        if (Settings.soundEnabled) {
            sound.play(1);
        }
    }

    public static void renderLogoAndBack(SpriteBatcher batcher) {

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

    public static void renderLogo(SpriteBatcher batcher) {

        batcher.drawSprite(Assets.SCREEN_WIDTH / 2,
                Assets.SCREEN_HEIGHT - 64,
                177,
                72,
                Assets.fiestaLogo);
    }

    public static void renderBack(SpriteBatcher batcher) {

        batcher.drawSprite(34,
                Assets.SCREEN_HEIGHT - 64,
                56,
                56,
                Assets.buttonBack);

    }
}
