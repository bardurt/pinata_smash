package com.zygne.game.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class Scene{

    public int ScreenWidth;
    public int ScreenHeight;
    public int sceneNumber;
    public Context context;
    public Bitmap buffer;
    public Canvas canvasBuffer;
    public boolean destroyed = false;
    protected GameSceneManager gameSceneManager;
    protected GameCamera camera;


    public Scene(Context c, GameSceneManager gms){
        this.context = c;
        this.gameSceneManager = gms;
    }

    public void init(int w, int h){
        ScreenWidth = w;
        ScreenHeight =h;
    }

    public void init(int SceneWidth, int SceneHeight, int DeviceWidth, int DeviceHeight){
        ScreenWidth = SceneWidth;
        ScreenHeight =SceneHeight;
        int camX = DeviceWidth-ScreenWidth;
        int camY = DeviceHeight-ScreenHeight;
        camera = new GameCamera(camX,camY,ScreenWidth,ScreenHeight);
        if(buffer == null){
            buffer = Bitmap.createBitmap(DeviceWidth, DeviceHeight, Bitmap.Config.ARGB_8888);
        }
    }

    public abstract void onTouch(MotionEvent event) throws NullPointerException;

    public abstract boolean isActive();

    public abstract void update() throws NullPointerException;

    public abstract void update(long ms);

    public abstract void render(Canvas c) throws NullPointerException;

    public abstract void destroyScene();
}
