package com.zygne.game.framework;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by Bardur on 16/03/2017.
 */

public class VibrationManager {

    private Vibrator vibrator;
    private static final VibrationManager ourInstance = new VibrationManager();

    public static VibrationManager getInstance() {
        return ourInstance;
    }

    private VibrationManager() {
    }

    public void init(Context context){

        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void vibrate(){
        vibrator.vibrate(200);
    }

    public void vibrate(int ms){
        vibrator.vibrate(ms);
    }
}
