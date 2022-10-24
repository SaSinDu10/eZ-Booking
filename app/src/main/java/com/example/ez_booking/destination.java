package com.example.ez_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
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
        ArrayAdapter<String>sourceAdapter = new ArrayAdapter<>(this.android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.array_source));
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSource.setAdapter(sourceAdapter);
    }

    private void populateSpinnerDestination() {
        ArrayAdapter<String> destinationAdapter = new ArrayAdapter<>(this.android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.array_destination));
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDestination.setAdapter(destinationAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(parent.getId() == R.id.spinnerSource){
            //String selectedSource = parent.getSelectedItem.toString();
            String selectedSource = parent.getItemPosition(position).toString();
            Toast.makeText(this,"Selected: "+selectedSource,Toast.LENGTH_SHORT).show();
        }else if(parent.getId() == R.id.spinnerDestination){
            String selectedDestination = parent.getSelectedItem.toString();
            Toast.makeText(this,"Selected: "+selectedDestination,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}