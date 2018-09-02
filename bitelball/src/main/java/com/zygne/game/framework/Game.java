package com.zygne.game.framework;

import com.zygne.game.framework.controls.Input;
import com.zygne.game.framework.streams.Audio;
import com.zygne.game.framework.streams.FileIO;

public interface Game {
    Input getInput();

    FileIO getFileIO();

    Audio getAudio();

    void setScreen(Screen screen);

    Screen getCurrentScreen();

    Screen getStartScreen();

}