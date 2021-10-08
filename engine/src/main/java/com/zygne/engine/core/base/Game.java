package com.zygne.engine.core.base;

import com.zygne.engine.Screen;

public interface Game {
    Input getInput();
    FileIO getFileIO();
    Audio getAudio();
    void setScreen(Screen screen);
    Screen getCurrentScreen();
    Screen getStartScreen();
    void onGameStateChanged(int state);
}