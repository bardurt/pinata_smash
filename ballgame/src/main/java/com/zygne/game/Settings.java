package com.zygne.game;

import com.zygne.engine.core.base.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Settings {
    public static boolean soundEnabled = true;
    public static boolean touchEnabled = true;
    public static boolean vibrationOn = true;
    public static boolean musicEnabled = true;
    public final static String file = ".starwarrior";

    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile(file)));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            vibrationOn = Boolean.parseBoolean(in.readLine());
            musicEnabled = Boolean.parseBoolean(in.readLine());
        } catch (Exception ignored) {
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(file)));
            out.write(Boolean.toString(soundEnabled));
            out.write("\n");
            out.write(Boolean.toString(vibrationOn));
            out.write("\n");
            out.write(Boolean.toString(musicEnabled));
        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }
}
