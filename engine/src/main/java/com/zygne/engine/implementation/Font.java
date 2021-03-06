package com.zygne.engine.implementation;

import android.util.Log;

public class Font {
    private final Texture texture;
    private final int glyphWidth;
    private final int glyphHeight;
    private final int numberOfChars;
    private final TextureRegion[] glyphs;
    
    public Font(Texture texture, 
                int offsetX, int offsetY,
                int glyphsPerRow, int glyphWidth,
                int glyphHeight, int numberOfChars) {

        this.numberOfChars = numberOfChars;
        this.glyphs = new TextureRegion[numberOfChars];
        this.texture = texture;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;

        int x = offsetX;
        int y = offsetY;

        for(int i = 0; i < numberOfChars; i++) {
            glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
            x += glyphWidth;
            if(x == offsetX + glyphsPerRow * glyphWidth) {
                x = offsetX;
                y += glyphHeight;
            }
        }

        Log.d("Font", "size: " + glyphs.length);
    }
    
    public void drawText(SpriteBatcher batcher, String text, float x, float y) {

        Log.d("Font", "drawing text: " + text);

        int len = text.length();
        for(int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';

            if(c < 0 || c > glyphs.length - 1) {
                continue;
            }

            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
            x += glyphWidth;
        }
    }
}
