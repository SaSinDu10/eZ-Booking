package com.example.ez_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;

public class TransMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_mode);

        Button btnNext = findViewById(R.id.btnNext);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(TransMode.this, destination.class);
            intent.putExtra("vehicle", radioGroup.getCheckedRadioButtonId() == R.id.rd_bus ? TransportMode.BUS.value(): TransportMode.TRAIN.value());
            startActivity(intent);
        });
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dot3menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                setContentView(R.layout.activity_splash);
                break;
            case R.id.logout:
                setContentView(R.layout.activity_login);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}