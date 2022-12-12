package com.example.housemap;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Mur;
import com.example.housemap.model.Piece;

public class ChangerActivity extends AppCompatActivity {
    private Piece piece;
    private Batiment maison;
    private EditText nomPiece;
    private String nomPieceS;
    private Button initial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer);
        nomPiece = findViewById(R.id.editTextTextNamePiece3);
        initial = findViewById(R.id.termine7);
        initial.setEnabled(false);
        initial.setVisibility(View.INVISIBLE);
        if(getIntent().getExtras() != null) {
            piece = (Piece) getIntent().getSerializableExtra("piece");
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
        if(piece.getMur(0)==null){
            initial.setVisibility(View.VISIBLE);
            initial.setEnabled(true);
            Toast.makeText(ChangerActivity.this, "Vous pouvez initialiser cette pièce vide ", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickSupprimer(View view) {
        Intent intent = new Intent(ChangerActivity.this, ConsultPieceActivity.class);
        maison.retirerPiece(piece);
        //maison.decreaseNumPiece();
        Toast.makeText(ChangerActivity.this, "nb pieces : "+maison.getNbPieces(), Toast.LENGTH_SHORT).show();
        intent.putExtra("maison",maison);
        setResult(RESULT_OK, intent);
        finish();
    }
    public void clickValiderNom(View view) {
        if(!(nomPiece.getText().toString().equals(""))&&!maison.pieceIsInBat(nomPiece.getText().toString())){//si on a écrit quelque chose et si pièce pas dans le batiment
            nomPieceS = nomPiece.getText().toString();
            piece.setNom(nomPieceS);
            maison.mettreAJourPiece(piece);
            Toast.makeText(ChangerActivity.this, "Nom de la pièce modifié avec succès", Toast.LENGTH_SHORT).show();
            Button valider = findViewById(R.id.buttonValide);
            valider.setEnabled(false);
            valider.setVisibility(View.INVISIBLE);
            nomPiece.setVisibility(View.INVISIBLE);
        }
        else{
            Toast.makeText(ChangerActivity.this, "Veuillez saisir un nom valide différent d'une pièce existante", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickValider(View view) {
        Intent intent = new Intent(ChangerActivity.this, ConsultPieceActivity.class);
        Bundle extras2 = new Bundle();
        extras2.putSerializable("maison",maison);
        extras2.putSerializable("piece",piece);
        intent.putExtras(extras2);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void clickNord(View view) {
        if(piece.getMur(0)!=null) {
            Intent intent = new Intent(ChangerActivity.this, ModifHouseActivity.class);
            Bundle extras = new Bundle();
            Mur nord = piece.getMur(0);
            extras.putSerializable("mur", nord);
            extras.putSerializable("piece", piece);
            extras.putSerializable("maison", maison);
            intent.putExtras(extras);
            startActivityForResult(intent, 12);
        }
    }

    public void clickOuest(View view) {
        if(piece.getMur(3)!=null) {
            Intent intent = new Intent(ChangerActivity.this, ModifHouseActivity.class);
            Bundle extras = new Bundle();
            Mur ouest = piece.getMur(3);
            extras.putSerializable("mur", ouest);
            extras.putSerializable("piece", piece);
            extras.putSerializable("maison", maison);
            intent.putExtras(extras);
            startActivityForResult(intent, 12);
        }
    }

    public void clickSud(View view) {
        if(piece.getMur(1)!=null) {
            Intent intent = new Intent(ChangerActivity.this, ModifHouseActivity.class);
            Bundle extras = new Bundle();
            Mur sud = piece.getMur(1);
            extras.putSerializable("mur", sud);
            extras.putSerializable("piece", piece);
            extras.putSerializable("maison", maison);
            intent.putExtras(extras);
            startActivityForResult(intent, 12);
        }
    }

    public void clickEst(View view) {
        if(piece.getMur(2)!=null) {
            Intent intent = new Intent(ChangerActivity.this, ModifHouseActivity.class);
            Bundle extras = new Bundle();
            Mur est = piece.getMur(2);
            extras.putSerializable("mur", est);
            extras.putSerializable("piece", piece);
            extras.putSerializable("maison", maison);
            intent.putExtras(extras);
            startActivityForResult(intent, 12);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {//mise à jour de la maison et de la piece
                Batiment maison = (Batiment) data.getSerializableExtra("maison");
                Piece piece = (Piece) data.getSerializableExtra("piece");
                this.maison = maison;
                this.piece = piece;
            }
        }
        if (requestCode == 18) {
            if (resultCode == RESULT_OK) {//mise à jour de la maison et de la piece
                Batiment maison = (Batiment) data.getSerializableExtra("maison");
                Piece piece = (Piece) data.getSerializableExtra("piece");
                this.maison = maison;
                this.piece = piece;
                this.maison.mettreAJourPiece(piece);}
        }
    }

    public void clickInitial(View view) {
        Intent intent = new Intent(ChangerActivity.this, PieceEnCoursActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable("piece",piece);
        extras.putSerializable("maison",maison);
        extras.putSerializable("init",1);
        intent.putExtras(extras);
        startActivityForResult(intent,18);

    }

}