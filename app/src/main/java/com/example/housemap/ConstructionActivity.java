package com.example.housemap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;
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
import java.io.IOException;

public class ConstructionActivity extends AppCompatActivity {
    private Batiment maison;
    private final int RECYCL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
        for(int i = 0;i<maison.getNbPieces();i++){
            Toast.makeText(ConstructionActivity.this, "La pièce est :"+maison.getPiece(i).getNom(), Toast.LENGTH_SHORT).show();
        }
        if(maison.getNbPieces() !=0) {
            Toast.makeText(ConstructionActivity.this, "Le mur a une sortie vers :" + maison.getPiece(0).getMur(0).getSortie(0).getNomPiece(), Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void clickAjoutPiece(View view) {
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
        Intent intent = new Intent(ConstructionActivity.this, PieceEnCoursActivity.class) ;
        Bundle extras = new Bundle();
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        startActivity(intent); ;
    }

    public void clickConsultPiece(View view) {
        Intent intent = new Intent(this, ConsultPieceActvity.class) ;
        Bundle extras = new Bundle();
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        startActivityForResult(intent,2);
    }
}