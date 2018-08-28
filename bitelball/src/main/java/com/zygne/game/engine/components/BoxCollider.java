package com.zygne.game.engine.components;

/**
 * Created by Bardur on 03/02/2016.
 */
public class BoxCollider {

    public int posX;
    public int posY;
    public int width;
    public int height;


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTop(){
        return posY;
    }

    public int getBottom(){
        return posY +height;
    }

    public int getLeft(){
        return posX+width;
    }

    public int getRight(){
        return posX;
    }

    public boolean isColliding(BoxCollider b){

        boolean collision = false;
        if( this.getBottom() > b.getTop() )
            if( this.getTop() < b.getBottom() )
                if( this.getRight() > b.getLeft() )
                    if( this.getLeft() < b.getRight() )
                         collision = true;


        return collision;
    }

    public boolean leftSide(BoxCollider b){
        boolean isColliding = false;
            if (b.getRight() > this.getLeft() && b.getLeft() < this.getLeft())
                if (b.getTop() < this.getBottom() && b.getBottom() > this.getTop())
                    if(b.getRight() < this.getRight())
                        isColliding = true;

        return isColliding;
    }

    public boolean rightSide(BoxCollider b){
        boolean isColliding = false;

            if (b.getLeft() < this.getRight() && b.getRight() > this.getRight()) {
                if (b.getTop() < this.getBottom() && b.getBottom() > this.getTop()) {
                    if(b.getLeft() > this.getLeft())
                        isColliding = true;
                }
            }

        return isColliding;
    }

}
