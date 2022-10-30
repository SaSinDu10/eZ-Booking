package com.example.ez_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Qrcode extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        Bundle extras = getIntent().getExtras();

        TextView txtSource = findViewById(R.id.txtSource);
        txtSource.setText(extras.getString("from"));

        TextView txtDestination = findViewById(R.id.txtDestination);
        txtDestination.setText(extras.getString("to"));

        TextView txtAmount = findViewById(R.id.txtAmount);
        txtAmount.setText(String.valueOf(extras.getDouble("amount")));

        TextView txtPassengers = findViewById(R.id.txtPassengers);
        txtPassengers.setText(extras.getString("passengers"));

        String sText = extras.getString("from")+"_"+extras.getString("to")+"_"+extras.getString("time")+"_"+extras.getInt("passengers")+"_"+extras.getDouble("amount");

        //Initialize multi format writer
        MultiFormatWriter writer = new MultiFormatWriter();

        //Initialize Bit matrix
        try {
            BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            ImageView imgQr = findViewById(R.id.imgQr);
            imgQr.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }
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