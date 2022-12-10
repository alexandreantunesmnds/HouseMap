package com.example.housemap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Mur;
import com.example.housemap.model.Piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class VisualisationActivity extends AppCompatActivity {
    private Piece piece;
    private Batiment maison;
    private int i;

    private ImageView img;

    @SuppressLint("ClickableViewAccessibility")
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
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int x = (int) event.getX(); // récupérer la coordonnée x du clic
                int y = (int) event.getY(); // récupérer la coordonnée y du clic
                Mur mur = piece.getMur(0);
                    if((mur.getListSorties().size())!=0){
                        for(int i = 0;i<mur.getListSorties().size();i++){
                            Rect rect = mur.getSortie(0).getRect();
                            if(rect != null) {
                                if (rect.contains(x, y)) {
                                    // faire quelque chose si le clic se trouve à l'intérieur du Rect
                                    Toast.makeText(view.getContext(), "Vous avez cliquer dans le Rect !!", Toast.LENGTH_SHORT).show();
                                } else {
                                    // faire quelque chose si le clic se trouve à l'extérieur du Rect
                                    //Toast.makeText(this, "Vous avez cliquer hors Rect !!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }

                return false;
            }
        });
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