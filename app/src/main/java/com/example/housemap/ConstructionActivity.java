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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void clickAjoutPiece(View view) {
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
        Intent intent = new Intent(ConstructionActivity.this, PieceEnCoursActivity.class) ;
        intent.putExtra("maison", maison); //where user is an instance of User object
        startActivity(intent) ;
    }

    public void clickConsultPiece(View view) {
        Intent intent = new Intent(this, ConsultPieceActvity.class) ;
        startActivity(intent) ;
    }
}