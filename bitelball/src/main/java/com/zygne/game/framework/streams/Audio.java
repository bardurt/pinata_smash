package com.zygne.game.framework.streams;

public interface Audio {
    Music newMusic(String filename);

    Sound newSound(String filename);
}
