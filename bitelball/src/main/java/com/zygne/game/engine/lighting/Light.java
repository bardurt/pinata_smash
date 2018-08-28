package com.zygne.game.engine.lighting;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;

/**
 * Created by Bardur on 07/06/2016.
 */
public class Light {
    private int radius;
    private int lightPower;
    private Paint lightImage;
    public Bitmap image;
    public Point point;

    public Light(int radius, int lightPower){
        this.radius=radius;
        this.lightPower=lightPower;
        lightImage = new Paint();
        point = new Point(0,0);
        Point center = new Point(radius,radius);

        Paint p1 = new Paint();
        p1.setARGB(0, 0, 0, 0);
        Paint p2 = new Paint();
        p2.setARGB(lightPower, 0, 0, 0);
        try {
            image = Bitmap.createBitmap(radius*2,radius*2, Bitmap.Config.ARGB_8888);
            //RadialGradient(float centerX, float centerY, float radius, int centerColor, int edgeColor, Shader.TileMode tileMode)
            RadialGradient rgp = new RadialGradient((float)center.getX(), (float)center.getY(),radius ,p2.getColor(),p1.getColor(), Shader.TileMode.CLAMP);
            lightImage.setShader(rgp);
            Canvas canvas = new Canvas();
            canvas.setBitmap(image);
            canvas.drawOval(0,0,radius*2,radius*2,lightImage);
        }catch (OutOfMemoryError e){}

    }

    public void render(Canvas c){
        if(image!= null)
            c.drawBitmap(image,point.getX(),point.getY(),null);
    }

    public void destroy(){
        if(image!=null) {
            image.recycle();
            image = null;
        }
    }
}
