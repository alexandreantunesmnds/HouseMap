package com.example.housemap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Mur;
import com.example.housemap.model.Piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AfficheMurActivity extends AppCompatActivity {
    private Mur mur;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_mur);
        img = findViewById(R.id.img_mur);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        if(getIntent().getExtras() != null) {
            mur = (Mur) getIntent().getSerializableExtra("mur");
        }
            FileInputStream fis;
            try {
                fis = openFileInput(mur.getNomPhoto());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Bitmap bm = BitmapFactory.decodeStream(fis);
            // Reste à mettre bm à mettre sur l’ImageView
            img.setImageBitmap(bm);
    }

}