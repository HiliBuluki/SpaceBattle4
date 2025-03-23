package com.example.myapplication_spacebattlenewhil4;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.ArrayList;
public class Player extends Sprite{
    Bitmap bitmap;
    ArrayList <Bullet> arrayListBullets;
    public Player(float x, float y, Bitmap bitmap)
    {
        super(x, y,15,15 );
        this.bitmap=bitmap;
        arrayListBullets=new ArrayList<>();
    }

    public ArrayList <Bullet> getArrayListBullets()
    {
        return arrayListBullets;
    }
    public void fireBullet()
    {
        for (int i = 0; i < arrayListBullets.size(); i++)
        {
            arrayListBullets.get(i).move();
        }
    }
    @Override
    public void move() {}
    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap,x,y,null);
        // מצייר את הכדורים
        for (int i = 0; i < arrayListBullets.size(); i++)
        {
           arrayListBullets.get(i).draw(canvas);
        }
    }
    public void setNewLocation(float x)
    {
        this.x = x - bitmap.getWidth()/2;
    }
    public void addBullet(Bullet bullet)
    {
        arrayListBullets.add(bullet);
    }
}