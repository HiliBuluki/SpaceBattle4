package com.example.myapplication_spacebattlenewhil4;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.SoundPool;
import java.util.ArrayList;
public class GameModule
{
    public int getOldscore()
    {
        return oldscore;
    }
    int score=0;
    private int oldscore;

    public boolean sucseedstrike(ArrayList <Bullet> arrayListBullets, Alien alien)//באופן כללי הפעולה בודקת פגיעה בחייזר
    {
        for (int n=0;n<arrayListBullets.size();n++)
        {
            if (alien.isCollision(arrayListBullets.get(n).getX(),arrayListBullets.get(n).getY()))
            {
                alien.setLife(alien.getLife());
                if (alien.getLife() == 0)
                {//בדיקת הפסלות של חייזר
                    score++;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isCollisionDetected(Bitmap bitmap1, int x1, int y1, Bitmap bitmap2, int x2, int y2)
    {

        Rect bounds1 = new Rect(x1, y1, x1+bitmap1.getWidth(), y1+bitmap1.getHeight());//רוחב וגובה של תמונה
        Rect bounds2 = new Rect(x2, y2, x2+bitmap2.getWidth(), y2+bitmap2.getHeight());

        if (Rect.intersects(bounds1, bounds2))
        {//אם הם חותכים אחד את השני
            Rect collisionBounds = getCollisionBounds(bounds1, bounds2);
            for (int i = collisionBounds.left; i < collisionBounds.right; i++) {
                for (int j = collisionBounds.top; j < collisionBounds.bottom; j++) {
                    int bitmap1Pixel = bitmap1.getPixel(i-x1, j-y1);
                    int bitmap2Pixel = bitmap2.getPixel(i-x2, j-y2);
                    if (isFilled(bitmap1Pixel) ==  isFilled(bitmap2Pixel)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }
    public void newscore(int score)
{
        this.oldscore=score;
    }

    private Rect getCollisionBounds(Rect rect1, Rect rect2)// בודקת לי התנגשויות
    {
        int left = (int) Math.max(rect1.left, rect2.left);
        int top = (int) Math.max(rect1.top, rect2.top);
        int right = (int) Math.min(rect1.right, rect2.right);
        int bottom = (int) Math.min(rect1.bottom, rect2.bottom);
        return new Rect(left, top, right, bottom);
    }
    private boolean isFilled(int pixel)
    {
        return pixel != Color.TRANSPARENT;
    }
    public int getScore()
    {
        return score;
    }
    public void setScore() {
        this.score = 0;
    }
}


