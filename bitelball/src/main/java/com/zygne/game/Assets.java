package com.zygne.game;


import com.zygne.game.framework.implementation.Animation;
import com.zygne.game.framework.implementation.Font;
import com.zygne.game.framework.implementation.GLGame;
import com.zygne.game.framework.implementation.Texture;
import com.zygne.game.framework.implementation.TextureRegion;
import com.zygne.game.framework.streams.Music;
import com.zygne.game.framework.streams.Sound;


public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;
    public static TextureRegion ballRegion;
    public static TextureRegion ballRegion1;
    public static TextureRegion ballRegion2;
    public static TextureRegion particleRegion;
    public static TextureRegion particleRegion1;
    public static TextureRegion particleRegion2;
    public static Texture items;
    public static TextureRegion ropeRegion;
    public static Font font;
    public static Music music;
    public static Sound crash;

    public static Animation agitar;

    public static void load(GLGame game) {
        background = new Texture(game, "background.jpg", true);
        backgroundRegion = new TextureRegion(background, 0, 0, 512, 512);

        items = new Texture(game, "items.png", true);
        ropeRegion = new TextureRegion(items, 0, 0, 44, 300);
        ballRegion = new TextureRegion(items, 0, 700, 323, 324);
        ballRegion1 = new TextureRegion(items, 324, 700, 322, 324);
        ballRegion2 = new TextureRegion(items, 646, 700, 322, 324);
        particleRegion = new TextureRegion(items, 55, 0, 16, 16);
        particleRegion1 = new TextureRegion(items, 77, 5, 9, 9);
        particleRegion2 = new TextureRegion(items, 90, 5, 9, 9);

        crash = game.getAudio().newSound("crash.ogg");

        TextureRegion[] keyFrames = new TextureRegion[2];
        int frame = 0;

        for (int x = 0; x < 450; x += 225) {
            keyFrames[frame++] = new TextureRegion(items, x, 326,
                    225, 282);
        }

        agitar = new Animation(0.2f, keyFrames);
    }

    public static void reload() {
        background.reload();
    }

    public static void playSound(Sound sound) {
        if (Settings.soundEnabled) {
            sound.play(1);
        }
    }
}
