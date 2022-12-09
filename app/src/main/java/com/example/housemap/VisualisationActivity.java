package com.example.housemap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class VisualisationActivity extends AppCompatActivity {
    private Piece piece;
    private Batiment maison;
    private int i;

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualisation);
        i = 0;
        img = findViewById(R.id.imageView);
        if(getIntent().getExtras() != null) {
            piece = (Piece) getIntent().getSerializableExtra("piece");
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
        afficheImage(0); //on affiche le mur nord de base
    }
    public void afficheImage (int i){
            String nameFile = piece.getMur(i).getNomPhoto();
            FileInputStream fis;
            try {
                fis = openFileInput(nameFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Bitmap bm = BitmapFactory.decodeStream(fis);
            bm = Bitmap.createScaledBitmap(bm, 1000, 1000, false);
            // Reste à mettre bm à mettre sur l’ImageView
            img.setImageBitmap(bm);

    }

    public void clickSuivant(View view) {
        if(i<3) {
            i++;
            afficheImage(i);
        }
        else{
            i=0;
        }
    }

    public void clickPrec(View view) {//a revoir cette fonction
        if(i>0) {
            i--;
            afficheImage(i);
        }
        else{
            i=0;
        }
    }
}