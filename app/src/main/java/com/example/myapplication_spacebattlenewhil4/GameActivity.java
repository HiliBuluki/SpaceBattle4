package com.example.myapplication_spacebattlenewhil4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    BoardGame boardGame;
    TextToSpeech textToSpeech;
    FB fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //
        super.onCreate(savedInstanceState);  // קורא למתודה  כדי onCreate לוודא שהאתחול הבסיסי מתבצע.
        setContentView(R.layout.activity_game);  // מגדיר את התצוגה של המסך לפי קובץ ה-.xml.

        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");


        boardGame = new BoardGame(this, name);  //  BoardGame שמייצג את לוח המשחק.
        setContentView(boardGame);  // משנה את התצוגה של ה-Activity כך שתציג את האובייקט boardGame במקום ה-XML.

        fb = new FB(this);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {  // יוצר מופע של TextToSpeech להמרת טקסט לדיבור, ומוסיף מאזין שיבדוק מתי הרכיב מוכן.
            @Override
            public void onInit(int status) {  //
                if (status == TextToSpeech.SUCCESS) {  // בודק אם האתחול של זה TextToSpeech הצליח.
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);  // מגדיר את שפת הדיבור לאנגלית.
                }
            }
        });
    }
    public void resetgame() {
        boardGame.resetGame();
    }

    public void newScore(int score) {//מקבל את השיא החדש
        boardGame.getScore(score);

    }

    public void soundnewscore(int score)
    {
        textToSpeech.speak("your new score is" + score, TextToSpeech.QUEUE_FLUSH, null);
    }
}


