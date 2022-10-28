package com.example.ez_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
                String source = spinnerSource.getSelectedItem().toString();
                String destination = spinnerDestination.getSelectedItem().toString();
                //Toast.makeText(activity_destination.this, source + ", "+ destination,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dot3menu,menu);
        return super.onCreateOptionsMenu(menu);
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
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}