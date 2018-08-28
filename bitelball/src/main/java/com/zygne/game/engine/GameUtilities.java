package com.zygne.game.engine;

import android.content.Context;
import android.os.Vibrator;

public class GameUtilities {

    static Context context;
    private Vibrator v;
    private boolean shouldVibrate = true;

    private static GameUtilities ourInstance = new GameUtilities();

    public static GameUtilities getInstance() {
        return ourInstance;
    }

    public void initialize(Context ctx) {
        context = ctx;
        try {
            v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }catch (Exception e){
            v = null;
        }
    }

    public void vibrate(long seconds){
            if(shouldVibrate && v != null) {
                v.vibrate(seconds);
            }
    }

    private GameUtilities() {
    }

    public void setVibration(boolean vib){
        this.shouldVibrate = vib;
    }
}
