package com.zygne.game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.HashSet;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 14/04/2018.
 */
public class MovementSensor implements SensorEventListener {

    public static final double MIN_ACC = 5;

    private SensorManager sensorMan;
    private Sensor accelerometer;

    private HashSet<Listener> mListeners = new HashSet<>();

    float [] history = new float[2];
    String [] direction = {"NONE","NONE"};

    private static final MovementSensor ourInstance = new MovementSensor();

    public static MovementSensor getInstance() {
        return ourInstance;
    }

    private MovementSensor() {
    }

    public void init(Context context){
        sensorMan = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void start() {
        sensorMan.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        sensorMan.unregisterListener(this);
    }

    public void addListener(Listener listener) {
        mListeners.add(listener);
    }

    /* (non-Javadoc)
     * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            float xChange = history[0] - event.values[0];
            float yChange = history[1] - event.values[1];

            history[0] = event.values[0];
            history[1] = event.values[1];

            for (Listener listener : mListeners) {
                listener.onMotionDetected(xChange, yChange);
            }

            if (xChange > 2){
                direction[0] = "LEFT";
            }
            else if (xChange < -2){
                direction[0] = "RIGHT";
            }

            if (yChange > 2){
                direction[1] = "DOWN";
            }
            else if (yChange < -2){
                direction[1] = "UP";
            }
        }

    }

    /* (non-Javadoc)
     * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor, int)
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    public interface Listener {
        void onMotionDetected(float dx, float dy);
    }
}
