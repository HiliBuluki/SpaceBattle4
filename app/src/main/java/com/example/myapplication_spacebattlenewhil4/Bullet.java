package com.example.myapplication_spacebattlenewhil4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bullet extends Sprite {
    private Paint p1;
    private int r1;

    public Bullet(float x, float y, int r1) {
        super(x, y, 0, 15);
        this.p1 = new Paint();
        this.p1.setColor(Color.WHITE); // Setting the bullet color to white
        this.r1 = r1;
    }

    @Override
    public void move() {
        y = y - 40;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, r1, p1);
    }
}