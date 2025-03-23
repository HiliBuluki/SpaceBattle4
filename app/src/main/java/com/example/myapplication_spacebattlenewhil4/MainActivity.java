package com.example.myapplication_spacebattlenewhil4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    private Button btnInstruction, btnStartGame; // הפניות
    SoundPool soundPool;
    int clicksound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        clicksound = soundPool.load(this, R.raw.clickbutton, 1);
        btnInstruction = findViewById(R.id.btnInstruction);
        btnStartGame = findViewById(R.id.btnStartGame);
        btnInstruction.setOnClickListener(this);
        btnStartGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnInstruction) {
            Intent i = new Intent(this, ActivityInstruction.class);
            soundPool.play(clicksound, 1, 1, 0, 0, 1);
            startActivity(i);
        }


        if (v == btnStartGame) {
            Intent i = new Intent(this, GameActivity.class);
            EditText et = findViewById(R.id.etName);
            String name = et.getText().toString();
            i.putExtra("userName", name);
            startActivity(i);
            soundPool.play(clicksound, 1, 1, 0, 0, 1);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {//הפונקציה מראה את התפריט
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.login)
        {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
            Dialog d = new Dialog(this);
            d.setContentView(R.layout.custom_dialog_login);//מה שנראה
            EditText etName = d.findViewById(R.id.etLoginName);
            EditText etPass = d.findViewById(R.id.etLoginPass);
            Button btnSave = d.findViewById(R.id.btnLoginSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Save " + etName.getText().toString(), Toast.LENGTH_SHORT).show();
                    d.dismiss();
                }
            });

            d.show();
        }

        if(id == R.id.register)
        {
            Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.logout)
        {
            Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}