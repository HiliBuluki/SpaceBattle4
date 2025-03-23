package com.example.myapplication_spacebattlenewhil4;

import android.graphics.Canvas;
public abstract class Sprite {
    protected float x, y; // the center of the circle
    protected float dx, dy;

    public Sprite(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public float getX() {
        return x;
    }

    public float getY()
    {
        return y;
    }

    protected boolean isCollision(float x, float y)
    {

        if ((this.x > x && this.x < x + 40 || this.x < x && this.x > x - 40) && ((this.y >
                y && this.y < y + 40) || this.y < y && this.y > y + 40))
            return true;
        return false;
    }

    public abstract void move();

    public abstract void draw(Canvas canvas);
}