package com.example.housemap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.provider.MediaStore;
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

public class ModifHouseActivity extends AppCompatActivity {
    private final int PHOTO = 1;
    private Batiment maison;
    private Piece piece;
    private Mur mur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_house);
        if (getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
            piece = (Piece) getIntent().getSerializableExtra("piece");
            mur = (Mur) getIntent().getSerializableExtra("mur");
        }
    }

    public void clickAjoutMur(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PHOTO);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            FileOutputStream fos;
            FileInputStream fis;
            try {
                ImageView img = new ImageView(this);
                String nameFile = piece.getNom() + mur.getNoMur() + ".data";
                fos = openFileOutput(nameFile, MODE_PRIVATE);
                fis = openFileInput(nameFile);
                Bitmap bm = BitmapFactory.decodeStream(fis);
                img.setImageBitmap(bm);
                mur.setPhoto(nameFile);
                this.piece.modifierMur(mur);
                this.maison.mettreAJourPiece(piece);
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

    public void clickValider(View view) {
        Intent intent = new Intent(ModifHouseActivity.this, ChangerActivity.class);
        Bundle extras2 = new Bundle();
        extras2.putSerializable("maison",maison);
        extras2.putSerializable("piece",piece);
        intent.putExtras(extras2);
        setResult(RESULT_OK, intent);
        finish();
    }
}