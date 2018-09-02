package com.zygne.game.framework.controls;

import android.view.View.OnTouchListener;

import java.util.List;

public interface TouchHandler extends OnTouchListener {
    boolean isTouchDown(int pointer);
    
    int getTouchX(int pointer);
    
    int getTouchY(int pointer);
    
    List<Input.TouchEvent> getTouchEvents();
}
