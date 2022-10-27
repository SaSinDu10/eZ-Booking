package com.example.ez_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;

public class destination extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerSource, spinnerDestination;
    Button btnNext2;
    //String[] source;
    private Build.VERSION_CODES android;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        spinnerSource = findViewById(R.id.spinnerSource);
        spinnerDestination = findViewById(R.id.spinnerDestination);
        btnNext2 = findViewById(R.id.btnNext2);

        populateSpinnerSource();
        populateSpinnerDestination();

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String source = spinnerSource.getSelectedItem().toString();
                String destination = spinnerDestination.getSelectedItem().toString();
                //Toast.makeText(activity_destination.this, source + ", "+ destination,Toast.LENGTH_SHORT).show();

            }
        });

        spinnerSource.setOnItemSelectedListener(this);
        spinnerDestination.setOnItemSelectedListener(this);
    }

    private void populateSpinnerSource() {
        //source = new DateFormatSymbols().getSource();
        ArrayAdapter<CharSequence>sourceAdapter = ArrayAdapter.createFromResource(this, R.array.array_source, R.layout.spinner_layout);
        spinnerSource.setAdapter(sourceAdapter);
    }

    private void populateSpinnerDestination() {
        ArrayAdapter<CharSequence>destinationAdapter = ArrayAdapter.createFromResource(this, R.array.array_destination, R.layout.spinner_layout);
        spinnerDestination.setAdapter(destinationAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(adapterView.getId() == R.id.spinnerSource){
            //String selectedSource = parent.getSelectedItem.toString();
            String selectedSourceItem = (String) adapterView.getSelectedItem();
            Toast.makeText(this,"Selected: "+selectedSourceItem, Toast.LENGTH_SHORT).show();
        }else if(adapterView.getId() == R.id.spinnerDestination){
            String selectedDestinationItem = (String) adapterView.getSelectedItem();
            Toast.makeText(this,"Selected: "+selectedDestinationItem, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
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