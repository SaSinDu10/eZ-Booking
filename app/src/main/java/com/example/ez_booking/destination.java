package com.example.ez_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class destination extends AppCompatActivity {

    private TextView tvSourceSpinner, tvDestinationSpinner;
    private Spinner sourceSpinner, destinationSpinner;
    private ArrayAdapter<CharSequence> sourceAdapter, destinationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        //Source spinner initialisation
        sourceSpinner = findViewById(R.id.spinnerSource);

        //ArrayAdapter for Source
        sourceAdapter = ArrayAdapter.createFromResource(this, R.array.array_source,R.layout.spinner_layout);

        //Specify the layout to use when the list of choices appear
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the spinner to populate the source spinner
        sourceSpinner.setAdapter(sourceAdapter);

    }
}