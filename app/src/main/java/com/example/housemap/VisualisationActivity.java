package com.example.housemap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Mur;
import com.example.housemap.model.Piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VisualisationActivity extends AppCompatActivity {
    private Piece piece;
    private Batiment maison;
    private int i;

    private ImageView img;
    private int orientation;
    private TextView textNom;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualisation);
        i = 0;
        img = findViewById(R.id.imageView);
        textNom = findViewById(R.id.textView);
        if(getIntent().getExtras() != null) {
            piece = (Piece) getIntent().getSerializableExtra("piece");
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
            if(getIntent().getSerializableExtra("orientation")!=null) {
                orientation = (int) getIntent().getSerializableExtra("orientation");
            }
            else{
                orientation = 0;
            }
        }
        textNom.setText(piece.getNom());
        afficheImage(orientation); //on affiche le mur nord de base
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int x = (int) event.getX(); // récupérer la coordonnée x du clic
                int y = (int) event.getY(); // récupérer la coordonnée y du clic
                    Mur mur = piece.getMur(i);
                    if ((mur.getListSorties().size()) != 0) {
                        for (int n = 0; n < mur.getListSorties().size(); n++) {
                            Rect rect = mur.getSortie(n).getRect();
                            if (rect != null) {
                                if (rect.contains(x, y)) {
                                    // faire quelque chose si le clic se trouve à l'intérieur du Rect
                                    if(maison.getPiece(mur.getSortie(n).getNomPiece()).getMur(i)!=null) {
                                        Intent intent = new Intent(VisualisationActivity.this, VisualisationActivity.class);
                                        Bundle extras2 = new Bundle();
                                        extras2.putSerializable("maison", maison);
                                        extras2.putSerializable("orientation", i);
                                        extras2.putSerializable("piece", maison.getPiece(mur.getSortie(n).getNomPiece()));
                                        intent.putExtras(extras2);
                                        finish();
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(view.getContext(), "Vous n'avez pas initialisé la pièce, veuillez prendre les photos", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }

                return false;
            }
        });
    }
    public void afficheImage (int i){
        if(piece.getMur(0).getNomPhoto()!=null) {
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

    }

    public void clickSuivant(View view) {
        if(i<3) {
            i++;
            afficheImage(i);
        }
        else{
            i=0;
            afficheImage(i);
        }
    }

    public void clickPrec(View view) {//a revoir cette fonction
        i--;
        if(i<0) {
            i=3;
            afficheImage(i);
        }
        else{
            afficheImage(i);
        }

    }


}