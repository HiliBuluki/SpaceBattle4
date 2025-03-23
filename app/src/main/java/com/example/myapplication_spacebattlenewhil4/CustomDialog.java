package com.example.myapplication_spacebattlenewhil4;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
public class CustomDialog extends Dialog implements View.OnClickListener {
    Button btnYes, btnNo;
    private Context context;
    GameModule gameModule;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.activity_custom_dialog);
        setCancelable(false);
        this.btnYes = findViewById(R.id.btnYes);
        this.btnYes.setOnClickListener(this);
        btnNo = findViewById(R.id.btnNo);
        btnNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnYes)
        {
            ((GameActivity)context).resetgame();
            dismiss(); // הפעולה מוחקת את החלוהית של הדיאלוג
        }

        if(v == btnNo)
        {
            // return to the GameActivity
            ((GameActivity)context).finish(); // finish close the Activity
        }
    }
}

