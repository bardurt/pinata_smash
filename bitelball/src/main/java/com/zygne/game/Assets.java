package com.zygne.game;


import com.zygne.game.framework.implementation.Font;
import com.zygne.game.framework.implementation.GLGame;
import com.zygne.game.framework.implementation.Texture;
import com.zygne.game.framework.implementation.TextureRegion;
import com.zygne.game.framework.streams.Music;
import com.zygne.game.framework.streams.Sound;


public class Assets {
	public static Texture background;
	public static TextureRegion backgroundRegion;
	public static Texture ball;
	public static TextureRegion ballRegion;
	public static Font font;
	public static Music music;
	public static Sound crash;

	public static void load(GLGame game) {
		background = new Texture(game, "background.jpg", true);
		backgroundRegion = new TextureRegion(background, 0, 0, 512, 512);
		ball = new Texture(game, "ball.png", true);
		ballRegion = new TextureRegion(ball, 0,0, 128,128);

        crash = game.getAudio().newSound("crash.ogg");
	}

	public static void reload() {
		background.reload();
		ball.reload();
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled) {
            sound.play(1);
        }
	}
}
