package com.example.myapplication_spacebattlenewhil4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;
public class Alien extends Sprite
{
    int life;
    Random bb=new Random();
    private float canvasWidth;
    public Bitmap bitmap;
    private float canvasHeight;
    public Alien(float x, float y, Bitmap bitmap)
    {
        super(x, y,15,15);
        this.bitmap=bitmap;
        life=(bb.nextInt(6)+1) ;
        this.bitmap=bitmap;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }
    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life-1;
    }
    @Override
    public void move()
    {
        if(x<bitmap.getWidth() || x>canvasWidth-bitmap.getWidth())
            dx = -dx;

        x = x + dx;
        y = y + dy;

    }
    @Override
    public void draw(Canvas canvas)
    {
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        canvas.drawBitmap(bitmap,x,y,null);
    }
}