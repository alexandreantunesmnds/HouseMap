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
        // Reste à mettre bm à mettre sur l’ImageView

    }
}