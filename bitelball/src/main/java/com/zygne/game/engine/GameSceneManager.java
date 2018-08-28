package com.zygne.game.engine;

import android.graphics.Canvas;

import java.util.Stack;


public class GameSceneManager {

    private Stack<Scene> scenes;

    public GameSceneManager(){
        scenes = new Stack<>();
    }

    public void update(){
        try {
            scenes.peek().update();
        }catch (Exception e){}
    }

    public void update(long ms){
        scenes.peek().update(ms);
    }

    public void render(Canvas c){
        try {
            scenes.peek().render(c);
        }catch (Exception e){}

    }

    public void push(Scene _scene){
        scenes.push(_scene);
    }

    public void pop(){
        scenes.pop().destroyScene();
    }

    public void set(Scene _scene){

        scenes.pop().destroyScene();
        scenes.push(_scene);
    }

    public Scene peek(){
        return scenes.peek();
    }
}
