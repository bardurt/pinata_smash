package com.zygne.game.engine.lighting;


import com.zygne.game.engine.components.BoxCollider;

/**
 * Created by Bardur on 07/06/2016.
 */
public class Point {
    int x, y;

    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInsideBox(BoxCollider bc){
        boolean inside = false;
        if(this.x >= bc.getLeft() && this.x <= bc.getRight()){
            if(this.y >= bc.getTop() && this.y <= bc.getBottom()){
                inside = true;
            }
        }
        return inside;
    }
}
