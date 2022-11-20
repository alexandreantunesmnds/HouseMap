package com.example.housemap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.FabriqueNumero;
import com.example.housemap.model.Mur;
import com.example.housemap.model.Piece;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PieceEnCoursActivity extends AppCompatActivity {
    private String FILE_NAME;
    private final int PHOTO = 1;
    private int nbPrises;
    private Mur[] tabMur = new Mur[4];
    private Piece piece;
    private Batiment maison;
    private String nomPieceS;
    private EditText nomPiece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_en_cours);
        Toast.makeText(PieceEnCoursActivity.this, "Veuillez saisir le nom de la pièce", Toast.LENGTH_SHORT).show();
        nomPiece = findViewById(R.id.editTextTextNamePiece);
        piece = new Piece();
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
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
        List<String> listNom = new ArrayList<String>();
        for(int i=0;i<tabMur.length;i++){
            listNom.add(tabMur[i].getPhoto().getTag().toString());
        }
        JSONArray array = new JSONArray();
        for(int i = 0; i < listNom.size(); i++) {
            array.put(listNom.get(i));
        }
        try {
            json.put("nom", piece.getNom());
            json.put("num", piece.getNoPiece());
            json.put("Nom images:",array);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Convert JsonObject to String Format
        String userString = json.toString();
        File file = new File(this.getFilesDir(),FILE_NAME);
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
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
                String nameFile = nomPieceS+nbPrises+".data";
                fos = openFileOutput(nameFile, MODE_PRIVATE);
                fis = openFileInput(nameFile);
                Bitmap bm = BitmapFactory.decodeStream(fis);
                img.setImageBitmap(bm);
                img.setTag(nameFile);
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
                int numPiece = FabriqueNumero.getInstance().getNumeroEtape();
                piece.setNoPiece(numPiece);
                piece.setTabMur(tabMur);
                maison.ajouterPiece(piece);
                Toast.makeText(PieceEnCoursActivity.this, "La pièce a été ajoutée au batiment, taille bat "+this.maison.getNbPieces(), Toast.LENGTH_SHORT).show();
                try {
                    this.sauvegarder();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Intent intent = new Intent(PieceEnCoursActivity.this, ConstructionActivity.class);
                Bundle extras2 = new Bundle();
                extras2.putSerializable("maison",maison);
                intent.putExtras(extras2);
                startActivity(intent); ;
                Toast.makeText(PieceEnCoursActivity.this, "Votre pièce a été créée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clickValiderNom(View view) {
        if(!(nomPiece.getText().toString().equals(""))){//si on a écrit quelque chose
            nomPieceS = nomPiece.getText().toString();
            FILE_NAME = nomPieceS;
            piece.setNom(nomPieceS);
            Button valider = findViewById(R.id.button4);
            valider.setEnabled(false);
            valider.setVisibility(View.INVISIBLE);
            nomPiece.setVisibility(View.INVISIBLE);
        }
        else{
            Toast.makeText(PieceEnCoursActivity.this, "Veuillez saisir le nom de la pièce", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickAnnuler(View view) {
    }
}