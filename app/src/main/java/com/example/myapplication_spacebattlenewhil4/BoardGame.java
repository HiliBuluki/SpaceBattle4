package com.example.myapplication_spacebattlenewhil4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;


public class BoardGame extends View {
    Handler handler;
    SoundPool soundPool;
    int killalien ,shootsound;
    GameModule gameModule;
    ThreadGame threadGame;
    Paint p =new Paint();//משמש לכתיבת הניקוד על המסך ולכתיבת כמות החיים של החייזר עך המסך
    Random rnd=new Random();
    int random= rnd.nextInt(1000);//מספר רנדומלי בין 0-999 לקביעת מקום הx של חייזר שנוצר
    int randomalien=rnd.nextInt(2);//מספר רנדומלי בין 1-0 לקביעת סוג החייזר
    int counter = 0;//ניקוד
    int newscore;//משתנה המכיל את השיא הגבוה ביותר של המשתמש
    boolean flag=true;//בדיקה פעם ראשונה שחייזר פוגע בחללית
    private boolean isRun = false;
    ArrayList <Sprite> arrayList;
    Bitmap bitmapstarship;
    int time=0;
    FB fb;
    Context context;
    private TextToSpeech textToSpeech;

    private String name;
    public BoardGame(Context context, String name)
    {
        super(context);
        this.context = context;
        this.name = name;
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC,0);
       killalien=soundPool.load(this.getContext(),R.raw.deathalien,1);
       shootsound=soundPool.load(this.getContext(),R.raw.shoot1,1);
        gameModule = new GameModule();
        arrayList = new ArrayList<>();//מערך בו העצם הראשון הוא החללית וכל השאר הם החייזרים
        Bitmap bitmapalien= BitmapFactory.decodeResource(getResources(),R.drawable.aliens);
        Bitmap bitmapsecondalien= BitmapFactory.decodeResource(getResources(),R.drawable.secondalien);
        bitmapstarship= BitmapFactory.decodeResource(getResources(),R.drawable.spaceship);
        bitmapstarship=Bitmap.createScaledBitmap(bitmapstarship,400,400,true);
        Player starship = new Player(300,1800,bitmapstarship);
        arrayList.add(starship);
        p.setTextSize(70);
        fb = new FB(context);


        threadGame = new ThreadGame();
        threadGame.start(); // runs as thread the run() method

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message)
            {
                counter++;

                Bullet b = new Bullet(starship.getX()+100,starship.getY(),13);
                starship.addBullet(b);
                if (counter%8==0)     soundPool.play(shootsound, 1, 1, 0, 0, 1);//צליל יריה

                if (counter % 25 == 0)
                {
                    if(randomalien==0)
                    {
                        Alien alien = new Alien(random, 150,bitmapalien);
                        arrayList.add(alien);
                    }
                    else {
                        Alien alien = new Alien(random, 100,bitmapsecondalien);
                        arrayList.add(alien);
                    }
                    randomalien=rnd.nextInt(2);
                    random= rnd.nextInt(1000)+150;
                }
                starship.fireBullet();


                for (int i = 1; i < arrayList.size(); i++)
                {
                    if (gameModule.isCollisionDetected(bitmapstarship,(int)arrayList.get(0).getX(),(int)arrayList.get(0).getY(),((Alien)arrayList.get(i)).getBitmap(),(int)arrayList.get(i).getX(),(int)arrayList.get(i).getY()))
                    {//בדיקת התנגשות בין חייזר לחללית
                        if (flag == true)//תנאי שמונע מהתפריט משחק חוזר להפתח אינסוף פעמים
                        {
                            flag = false;
                            isRun = false;//מפסיק את התנועה של החייזרים

                            if (gameModule.getOldscore() < gameModule.getScore())
                            {//בודק את השיא החדש שלו
                                newscore = gameModule.getScore();//פעולת קריאה של השיא החדש
                                fb.setRecord(name, newscore);//פעולה שמעדכנת את הפיירבייס בשיא החדש
                                Toast.makeText(context, "you have new score!!:"+newscore, Toast.LENGTH_SHORT).show();
                                ((GameActivity) context).soundnewscore(newscore);//פעולה שמקריאה את השיא החדש
                            }
                            createDialog();
                        }
                    }
                    arrayList.get(i).move();
                    if (gameModule.sucseedstrike( starship.getArrayListBullets(),(Alien) arrayList.get(i)))
                    {
                        soundPool.play(killalien,1,1,0,0,1);
                        arrayList.remove(i);
                    }
                }
                invalidate(); //  שוב מנקה את הלוח וקורא לפעולה
                return false;
            }});
    }

    public void resetGame()
    {
        ((Player)arrayList.get(0)).getArrayListBullets().clear();
        Sprite starship=arrayList.get(0);
        arrayList.clear();
        flag=true;//משתנה אם נפגעה החללית מחייזר
        arrayList.add(starship);
        gameModule.setScore();//מאפס את הניקוד
        invalidate();
    }

    public void getScore(int score)
    {
        gameModule.newscore(score);
    }


    private class ThreadGame extends Thread{
        @Override
        public void run()
        {
            super.run();
            while (true)
            {
                try
                {
                    sleep(40);
                    if(isRun)
                        handler.sendEmptyMessage(0);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace(); //  במקום להתעופףשולח הודעת שגיאה
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {isRun = true;}

        if(event.getAction() == MotionEvent.ACTION_MOVE)  {((Player)arrayList.get(0)).setNewLocation(event.getX());}

        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            isRun = false;

            if (flag == true)
            {
                flag = false;

                if (gameModule.getOldscore() < gameModule.getScore())
                {
                    newscore = gameModule.getScore();
                    fb.setRecord(name, newscore);
                    Toast.makeText(context, "you have new score!!:"+newscore, Toast.LENGTH_SHORT).show();
                    ((GameActivity) context).soundnewscore(newscore);
                }

                createDialog();
            }

        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Bitmap gamescreen=BitmapFactory.decodeResource(getResources(),R.drawable.background);
        canvas.drawBitmap(gamescreen,0,0,null);
        int x = gameModule.getScore();
        canvas.drawText("score:"+x,canvas.getWidth()-400,100,p);
        for (int i=0; i<arrayList.size();i++)
        {
            arrayList.get(i).draw(canvas);
        }
        p.setColor(Color.rgb(255,255,255));//צבע לבן


    }
    private void createDialog()
    {
        CustomDialog customDialog = new CustomDialog(context);
        customDialog.show();
    }
}
