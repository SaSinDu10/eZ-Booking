package com.example.ez_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TransMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_mode);

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(TransMode.this, destination.class);
            startActivity(intent);
        });
    }
}