package com.example.myapplication_spacebattlenewhil4;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FB {
    FirebaseDatabase database;
    Context context;

    public FB(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
        initFirebaseListener();
    }


    public void setRecord(String name, int record)
    {
        // Write a message to the database
        DatabaseReference myRef = database.getReference("records").push(); // push adds new node with unique value

        //DatabaseReference myRef = database.getReference("records/" + FirebaseAuth.getInstance().getUid());

        Record rec = new Record(name, record);
        myRef.setValue(rec);
    }


    private void initFirebaseListener()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // הפניה לצומת שרוצים לכתוב אליו
        DatabaseReference reference = firebaseDatabase.getReference("record");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int score = snapshot.getValue(int.class);
                ((GameActivity) context).newScore(score);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}