package com.example.housemap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class PieceEnCoursActivity extends AppCompatActivity {
    private final int PHOTO = 1;
    private int nbPrises;
    private Mur[] tabMur = new Mur[4];
    private Piece piece;
    private Batiment maison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_en_cours);
        maison = new Batiment();
    }

    public void clickAjoutMur(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PHOTO);
                /*switch(nbPrises) {
                    case 0:
                        Toast.makeText(ConstructionActivity.this, "Prenez la photo Nord", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(ConstructionActivity.this, "Prenez la photo Sud", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(ConstructionActivity.this, "Prenez la photo Est", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(ConstructionActivity.this, "Prenez la photo Ouest", Toast.LENGTH_SHORT).show();
                        break;
                }

            }*/
        }
        if(nbPrises==3){
            Toast.makeText(PieceEnCoursActivity.this, "Votre pièce a été créée", Toast.LENGTH_SHORT).show();
        }
        piece = new Piece("cuisine", 1, tabMur);
        maison.ajouterPiece(piece);
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
}