package com.example.housemap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ConsultPieceActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_piece_actvity);
        ImageView img = findViewById(R.id.img_piece);
        ImageView img2 = findViewById(R.id.img_piece2);
        ImageView img3 = findViewById(R.id.img_piece3);
        ImageView img4 = findViewById(R.id.img_piece3);
        FileInputStream fis,fis2,fis3,fis4;
        try {
            fis = openFileInput("image0.data");
            fis2 = openFileInput("image1.data");
            fis3 = openFileInput("image2.data");
            fis4 = openFileInput("image3.data");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        Bitmap bm1= BitmapFactory.decodeStream(fis2);
        Bitmap bm2 = BitmapFactory.decodeStream(fis3);
        Bitmap bm3 = BitmapFactory.decodeStream(fis4);
        // Reste à mettre bm à mettre sur l’ImageView
        img.setImageBitmap(bm);
        img2.setImageBitmap(bm1);
        img3.setImageBitmap(bm2);
        img4.setImageBitmap(bm3);

    }
}