package com.example.housemap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
    private Button ajout;
    private Button ajoutPorte;
    private Button valider;
    private Mur mur;
    private int init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_en_cours);
        nomPiece = findViewById(R.id.editTextTextNamePiece);
        ajout = findViewById(R.id.button3);
        ajoutPorte = findViewById(R.id.button10);
        valider = findViewById(R.id.termine);
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
            piece = (Piece) getIntent().getSerializableExtra("piece");
            if(getIntent().getSerializableExtra("init") != null) {
                init = (int) getIntent().getSerializableExtra("init");
            }
        }
        if(piece==null){
            piece = new Piece();
        }
        if(maison.getNbPieces()==0||!maison.pieceIsInBat(piece.getNom())) { //si la piece n'est pas encore créer alors on la créer
            Toast.makeText(PieceEnCoursActivity.this, "Veuillez saisir le nom de la pièce", Toast.LENGTH_SHORT).show();
            int numPiece = FabriqueNumero.getInstance().getNumeroPiece();
            piece.setNoPiece(numPiece);
            ajout.setEnabled(false);
            ajoutPorte.setEnabled(false);
            valider.setEnabled(false);
            ajout.setBackgroundColor(Color.GRAY);
            ajoutPorte.setBackgroundColor(Color.GRAY);
            valider.setBackgroundColor(Color.GRAY);
            maison.ajouterPiece(piece);
        }
        else{
            if(piece.getNom()!=null) {
                nomPieceS = piece.getNom();
            }
            Button valider = findViewById(R.id.button4); //on fait disparaître la saisie du nom de pièce si déjà effectuée
            valider.setEnabled(false);
            valider.setVisibility(View.INVISIBLE);
            nomPiece.setVisibility(View.INVISIBLE);
        }
    }

    public void clickAjoutMur(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PHOTO);
        }
    }
    /** public void sauvegarder() throws IOException {

        JSONObject json = new JSONObject();
        List<String> listNom = new ArrayList<String>();
        for(int i=0;i<tabMur.length;i++){
            listNom.add(tabMur[i].getNomPhoto());
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

        //Intent intent = new Intent(PieceEnCoursActivity.this, ConstructionActivity.class);
        //Bundle extras2 = new Bundle();
        //extras2.putSerializable("maison",maison);
        //intent.putExtras(extras2);
        //setResult(RESULT_OK) ; // ou RESULT_CANCELED
        //startActivityForResult(intent,3);
    }
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO && resultCode == RESULT_OK) {
            switch (nbPrises) {
                case 0:
                    Toast.makeText(PieceEnCoursActivity.this, "Prenez la photo Sud", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(PieceEnCoursActivity.this, "Prenez la photo Est", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(PieceEnCoursActivity.this, "Prenez la photo Ouest", Toast.LENGTH_SHORT).show();
                    break;
            }
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
                Mur mur = new Mur(nbPrises,nameFile);
                this.piece.ajouterMur(mur);
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
                //this.sauvegarder();
                ajoutPorte.setEnabled(true);
                valider.setEnabled(true);
                ajout.setEnabled(false);
                ajout.setVisibility(View.INVISIBLE); //on fait disparaître le bouton si les 4photos sont prises
                valider.setBackgroundColor(Color.parseColor("#7CB342"));
                ajoutPorte.setBackgroundColor(Color.parseColor("#CCFF90"));
                //Toast.makeText(PieceEnCoursActivity.this, "Vous pouvez désormais ajouter des portes à la pièce ou valider la pièce", Toast.LENGTH_SHORT).show();
            }
        }
        // check that it is the SecondActivity with an OK result
        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                maison = (Batiment) data.getSerializableExtra("maison");
                piece = (Piece) data.getSerializableExtra("piece");
            }
        }
    }

    public void clickValiderNom(View view) {
        if(!(nomPiece.getText().toString().equals(""))&&!maison.pieceIsInBat(nomPiece.getText().toString())){//si on a écrit quelque chose
            ajout.setEnabled(true);
            ajout.setBackgroundColor(Color.parseColor("#0097A7"));
            nomPieceS = nomPiece.getText().toString();
            FILE_NAME = nomPieceS;
            piece.setNom(nomPieceS);
            Button valider = findViewById(R.id.button4);
            valider.setEnabled(false);
            valider.setVisibility(View.INVISIBLE);
            nomPiece.setVisibility(View.INVISIBLE);
            Toast.makeText(PieceEnCoursActivity.this, "Prenez la photo Nord", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(PieceEnCoursActivity.this, "Veuillez saisir un nom valide différent d'une pièce existante", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickAnnuler(View view) {
        if(this.piece != null && this.maison != null) {
            this.maison.retirerPiece(this.piece);
        }
        Intent intent = new Intent(PieceEnCoursActivity.this, ConstructionActivity.class);
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    public void clickBoussole(View view) {
        Intent intent = new Intent(PieceEnCoursActivity.this, CapteursActivity.class);
        startActivity(intent) ;
    }

    public void clickAjoutPorte(View view) {
        Intent intent = new Intent(PieceEnCoursActivity.this, PorteActivity.class);
        Bundle extras2 = new Bundle();
        extras2.putSerializable("piece",piece);
        extras2.putSerializable("maison",maison);
        intent.putExtras(extras2);
        startActivityForResult(intent,6); ;
    }

    public void clickValider(View view) {
        Intent intent = new Intent(PieceEnCoursActivity.this, ConstructionActivity.class);
        Bundle extras2 = new Bundle();
        extras2.putSerializable("maison",maison);
        extras2.putSerializable("piece",piece);
        intent.putExtras(extras2);
        Sauvegarde save = Sauvegarde.getInstance();
        save.saveProject(maison,this);
        setResult(RESULT_OK, intent);
        finish();
    }

}