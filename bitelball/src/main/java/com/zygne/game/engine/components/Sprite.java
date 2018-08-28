package com.zygne.game.engine.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Bardur on 05/10/2015.
 */
public class Sprite {int x,y;
    public int width,height;
    public int srcX, srcY;
    public int currentFrameX = 0;
    public int currentFrameY = 0;
    public int columns, rows;
    public Bitmap b;
    public int dstWith, dstHeight;
    public boolean loop = false;
    public long frameTicker = 01;
    public long framePeriod;
    public Rect srcRect;
    public Rect dstRect;

    public Sprite(Bitmap bm, int col, int row, int dsW, int dsH){
        b = bm;
        x = y = 0;
        height = b.getHeight();
        height = height/row;
        width = b.getWidth();
        dstWith = dsW;
        dstHeight = dsH;
        columns = col;
        rows =row;
        width = width/columns;
        srcX = srcY = 0;
        srcRect = new Rect();
        dstRect = new Rect();
    }

    public void setDrawSize(int dstWith, int dstHeight){
        this.dstWith=dstWith;
        this.dstHeight=dstWith;
    }

    public void setFramesPerSecond(int fu){

        this.framePeriod = 1000/fu;
    }

    private void update(){

        if(loop)
             currentFrameX = ++currentFrameX%columns;
        else{
            currentFrameX++;
            if(currentFrameX>= columns)
                currentFrameX=columns-1;
        }

    }

    public void update(long ms){
        if (ms > frameTicker + framePeriod) {
            frameTicker = ms;
            update();
        }
    }

    public void setLooping(boolean l){
        this.loop = l;
    }

    public void onDraw(Canvas c, int pX, int pY){
        srcX = currentFrameX*width;
        srcY = currentFrameY*height;
        srcRect.set(srcX,srcY,srcX+width,srcY+height);
        dstRect.set(pX,pY,pX+dstWith,pY+dstHeight);
        try {
            c.drawBitmap(b, srcRect, dstRect, null);
        }catch (Exception e){}
    }

    public void onDraw(Canvas c, int pX, int pY, Paint paint){
        srcX = currentFrameX*width;
        srcY = currentFrameY*height;
        srcRect.set(srcX,srcY,srcX+width,srcY+height);
        dstRect.set(pX,pY,pX+dstWith,pY+dstHeight);
        try {
            c.drawBitmap(b, srcRect, dstRect, paint);
        }catch (Exception e){}

    }

    public void resetAnimation(){
        currentFrameX = 0;
        frameTicker=0;
    }

    public int getDstWith(){
        return this.dstWith;
    }

    public int getDstHeight(){
        return this.dstHeight;
    }

    public void recycle(){
        b.recycle();
    }
}
