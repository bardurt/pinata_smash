package com.zygne.game.engine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;

public class AssetsManager {

    private Context context;
    private AssetManager assetManager;

    private static AssetsManager ourInstance = new AssetsManager();

    public static AssetsManager getInstance() {
        return ourInstance;
    }

    public void initialize(Context ctx) {
        context = ctx;
        assetManager = context.getAssets();
    }

    public void Destroy(){
    }

    private AssetsManager() {
    }

    public Bitmap loadTextureAsBitmap(String name){

        InputStream istr3 = null;

        try {
            istr3 = assetManager.open(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(istr3);
    }

    public Bitmap loadTextureAsBitmapScaled(String name, int reqWidth, int reqHeight){

        InputStream istr3 = null;

        // First decode with inJustDecodeBounds=true to check dimensions


        try {
            istr3 = assetManager.open(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(istr3,null,options);


        if(options.outHeight > reqHeight)
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(istr3, null,options);
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public Typeface getFont(String path){

        return Typeface.createFromAsset(assetManager, path);
    }
}
