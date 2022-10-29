package com.example.ez_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;

public class destination extends AppCompatActivity {

    FirebaseFirestore db;
    Spinner spinnerSource, spinnerDestination, spinnerSchedule;
    Button btnNext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        db = FirebaseFirestore.getInstance();
        String vehicle = getIntent().getStringExtra("vehicle");

        spinnerSchedule = findViewById(R.id.spinnerSchedule);
        spinnerSource = findViewById(R.id.spinnerSource);
        spinnerDestination = findViewById(R.id.spinnerDestination);

        db.collection("vehicles").document(vehicle).collection("schedules").get()
            .addOnSuccessListener(documentSnapshots -> {
                ArrayAdapter<DocumentSnapshot> scheduleAdapter = new DocumentSnapshotAdapter(destination.this, R.layout.spinner_layout, documentSnapshots.getDocuments());
                spinnerSchedule.setAdapter(scheduleAdapter);

                spinnerSchedule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Schedule selectedSchedule = documentSnapshots.getDocuments().get(position).toObject(Schedule.class);
                        ArrayAdapter<DocumentReference> stopsAdapter = new DocumentReferenceAdapter(destination.this, R.layout.spinner_layout, selectedSchedule.getStops());
                        spinnerSource.setAdapter(stopsAdapter);
                        spinnerDestination.setAdapter(stopsAdapter);

                        spinnerSource.setEnabled(true);
                        spinnerDestination.setEnabled(true);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spinnerSource.setEnabled(false);
                        spinnerDestination.setEnabled(false);
                    }
                });
            });

        btnNext2 = findViewById(R.id.btnNext2);
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((DocumentReference) spinnerSource.getSelectedItem()).get()
                    .addOnSuccessListener(documentSnapshot1 -> {
                        Stop s1 = documentSnapshot1.toObject(Stop.class);

                        ((DocumentReference) spinnerDestination.getSelectedItem()).get()
                            .addOnSuccessListener(documentSnapshot2 -> {
                                Stop s2 = documentSnapshot2.toObject(Stop.class);

                                Intent intent = new Intent(destination.this, pay.class);

                                intent.putExtra("from", AdapterUtils.capitalize(((DocumentReference) spinnerSource.getSelectedItem()).getId()));
                                intent.putExtra("to", AdapterUtils.capitalize(((DocumentReference) spinnerDestination.getSelectedItem()).getId()));
                                intent.putExtra("time", new Date().toString());

                                int passengers = 2;
                                intent.putExtra("passengers", passengers);

                                double d1 = s1.getCoords().getLatitude() - s2.getCoords().getLatitude();
                                double d2 = s1.getCoords().getLongitude() - s2.getCoords().getLongitude();
                                double distance = Math.sqrt((d1*d1)+(d2*d2));
                                intent.putExtra("amount", distance * passengers);

                                startActivity(intent);

                            });
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