package com.example.housemap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

public class PieceEnCoursActivity extends AppCompatActivity {
    private  Context context;
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
    }
    public void sauvegarder() throws IOException {

        JSONObject json = new JSONObject();
        try {
            json.put("nom", piece.getNom());
            json.put("murs", List.of(tabMur));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Convert JsonObject to String Format
        String userString = json.toString();
        File file = new File(context.getFilesDir(),"piecesauve");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();
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
                img.setTag("image"+nbPrises);
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
            if(nbPrises==4){
                /*for (Mur mur : tabMur) {
                    Log.i("Nom image piece :", (String) mur.getPhoto().getTag()); //Affichage correct du nom des images
                }*/
                piece = new Piece("Salle info",1,tabMur);
                Batiment batiment = new Batiment();
                batiment.ajouterPiece(piece);
                try {
                    this.sauvegarder();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Intent intent = new Intent(this, ConstructionActivity.class) ;
                startActivity(intent) ;
                Toast.makeText(PieceEnCoursActivity.this, "Votre pièce a été créée", Toast.LENGTH_SHORT).show();
            }
        }
    }
}