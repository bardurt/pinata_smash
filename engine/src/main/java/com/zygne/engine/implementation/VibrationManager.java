package com.zygne.engine.implementation;

import android.content.Context;
import android.os.Vibrator;

import com.zygne.engine.core.base.RumbleHandler;

/**
 * Created by Bardur on 16/03/2017.
 */

public class VibrationManager implements RumbleHandler {

    private Vibrator vibrator;

    private VibrationManager(android.content.Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void rumble(int ms) {
        vibrator.vibrate(ms);
    }
}
