package com.example.ez_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


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

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get input value from DataBase
                String sText = etData.getText().toString().trim();

                //Initialize multi format writer
                 MultiFormatWriter writer = new MultiFormatWriter();

                //Initialize Bit matrix
                try {
                    BitMatrix matrix = writer.encode(sText,BarcodeFormat.QR_CODE,400,400);

                    BarcodeEncoder encoder = new BarcodeEncoder();

                    Bitmap bitmap = encoder.createBitmap(matrix);

                    imgQr.setImageBitmap(bitmap);

                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    manager.hideSoftInputFromWindow(etData.getApplicationWindowToken(),0);
                }catch (WriterException e){
                    e.printStackTrace();
                }

            }
        });

    }
}