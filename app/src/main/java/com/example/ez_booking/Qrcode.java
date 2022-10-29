package com.example.ez_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Qrcode extends AppCompatActivity {
    EditText etData;
    Button btnGenerate;
    ImageView imgQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        btnGenerate = findViewById(R.id.btnGenerate);
        imgQr = findViewById(R.id.imgQr);
        etData = findViewById(R.id.et_data);

        Bundle extras = getIntent().getExtras();
        String sText = extras.getString("from")+"_"+extras.getString("to")+"_"+extras.getString("time")+"_"+extras.getInt("passengers")+"_"+extras.getDouble("amount");
        etData.setText(sText);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize multi format writer
                 MultiFormatWriter writer = new MultiFormatWriter();

                //Initialize Bit matrix
                try {
                    BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE,300,300);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    imgQr.setImageBitmap(bitmap);
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(etData.getApplicationWindowToken(),0);
                }catch (WriterException e){
                    e.printStackTrace();
                }
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
            case R.id.logout:
                setContentView(R.layout.activity_login);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}