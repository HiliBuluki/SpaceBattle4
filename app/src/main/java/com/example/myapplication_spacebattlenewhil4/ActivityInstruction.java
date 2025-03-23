package com.example.myapplication_spacebattlenewhil4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityInstruction extends AppCompatActivity implements View.OnClickListener {
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        btnReturn = findViewById(R.id.finish);
        btnReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}