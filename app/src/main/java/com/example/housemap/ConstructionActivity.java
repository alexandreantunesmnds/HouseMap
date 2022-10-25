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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConstructionActivity extends AppCompatActivity {
    private final int PHOTO = 1;
    private int nbPrises;
    private Mur [] tabMur = new Mur[4];
    private Piece piece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void clickAjoutPiece(View view) {
        while(nbPrises<4) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivityForResult(intent, PHOTO);
            nbPrises++;
        }
        piece = new Piece("cuisine",1,tabMur);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Toast.makeText(ConstructionActivity.this, "Hauteur de l'image prise : "+ imageBitmap.getHeight(), Toast.LENGTH_SHORT).show();
            FileOutputStream fos;
            FileInputStream fis;
            try {
                ImageView img = new ImageView(this);
                Log.i("Nb prises : ", String.valueOf(nbPrises));
                fos = openFileOutput("image"+nbPrises+".data", MODE_PRIVATE);//image1,image2...
                fis = openFileInput("image"+nbPrises+".data");
                Bitmap bm = BitmapFactory.decodeStream(fis);
                img.setImageBitmap(bm);
                Mur mur = new Mur(nbPrises,img);
                tabMur[nbPrises]=mur;
                nbPrises++;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            try {
                fos.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void clickConsultPiece(View view) {
        Intent intent = new Intent(this, ConsultPieceActvity.class) ;
        startActivity(intent) ;
    }

    public Piece getPiece() {
        return piece;
    }
}