package com.example.ez_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class pay extends AppCompatActivity {
    FirebaseFirestore db;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        db = FirebaseFirestore.getInstance();

        final EditText mAccount = findViewById(R.id.txtAccountNo);
        final EditText mExDate = findViewById(R.id.txtExpireDate);
        final EditText mCvv = findViewById(R.id.txtCvv);

        String vehicle = getIntent().getStringExtra("vehicle");
        String scheduleId = getIntent().getStringExtra("scheduleId");
        int passengers = getIntent().getIntExtra("passengers", 1);

        btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accountNumber = mAccount.getText().toString();
                String expireDate = mExDate.getText().toString();
                String cvv = mCvv.getText().toString();

                db.collection("vehicles").document(vehicle).collection("schedules").document(scheduleId).get()
                    .addOnSuccessListener(scheduleSnapshot -> {
                        Schedule schedule = scheduleSnapshot.toObject(Schedule.class);
                        schedule.decrementCapacity(passengers);

                        db.collection("vehicles").document(vehicle).collection("schedules").document(scheduleId).set(schedule);

                        Intent intent = new Intent(pay.this,Qrcode.class);
                        intent.putExtras(getIntent());
                        startActivity(intent);
                    });
            }
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
            case R.id.about:
                // about text

                break;
            case R.id.logout:
                setContentView(R.layout.activity_login);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}