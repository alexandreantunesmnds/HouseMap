package com.example.housemap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.*;

public class ModifPorteActivity extends AppCompatActivity {
    private Batiment maison;
    private Mur mur;
    private Sortie porte;
    private Piece piece;
    private AlertDialog.Builder changeNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_porte);
        if (getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
            mur = (Mur) getIntent().getSerializableExtra("mur"); //on récupère le batiment créer
            porte = (Sortie) getIntent().getSerializableExtra("sortie"); //on récupère le batiment créer
            piece = (Piece) getIntent().getSerializableExtra("piece"); //on récupère le batiment créer
        }
    }

    public void clickValider(View view) {
        Intent intent = new Intent(ModifPorteActivity.this, ModifHouseActivity.class);
        intent.putExtra("maison", maison);
        intent.putExtra("piece", piece);
        //intent.putExtra("mur", mur);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void clickSupprimerPorte(View view) {
        mur.supprimerPorte(porte);
        piece.modifierMur(mur);
        maison.mettreAJourPiece(piece);
        maison.retirerPiece(maison.getPiece(porte.getNomPiece()));
        Toast.makeText(ModifPorteActivity.this, "Demande de suppression, veuillez valider", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ModifPorteActivity.this, ConsultPieceActivity.class);
        intent.putExtra("maison", maison);
        intent.putExtra("piece", piece);
        //intent.putExtra("mur", mur);
        setResult(RESULT_OK, intent);
        finish();
    }
}