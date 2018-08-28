package com.zygne.game.engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;

import java.util.Random;

/**
 * Created by Bardur on 08/02/2016.
 */
public class AudioManager {
    static private SoundPool soundPool;
    private Context context;
    private boolean shouldPlayEffects = true;
    private Random random;

    private static AudioManager ourInstance = new AudioManager();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    public static AudioManager getInstance() {
        return ourInstance;
    }

    public void setPlayEffects(boolean b){
        this.shouldPlayEffects = b;
    }

    public void initialize(Context ctx) {
        context = ctx;
        loadSounds();
        random = new Random();
        sp = context.getSharedPreferences("SOUNDS", Context.MODE_PRIVATE);
        editor = sp.edit();

        shouldPlayEffects =sp.getBoolean("SoundEffect",true);
    }

    public void Destroy(){
        if(soundPool != null) {
            soundPool.release();
        }

    }

    private AudioManager() {
    }

    public void loadSounds(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder().setMaxStreams(20).setAudioAttributes(attributes).build();
        } else
            soundPool = new SoundPool(20, android.media.AudioManager.STREAM_MUSIC, 0);


    }

    public void playHitSound(){
        if(shouldPlayEffects){
            switch (random.nextInt(4)) {
            }
        }
    }
}
