package com.example.housemap;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Mur;
import com.example.housemap.model.Piece;
import com.example.housemap.model.Sortie;

public class ModifPorteActivity extends AppCompatActivity {
    private Batiment maison;
    private Mur mur;
    private Sortie porte;
    private Piece piece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_porte);
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
            mur = (Mur) getIntent().getSerializableExtra("mur"); //on récupère le batiment créer
            porte = (Sortie) getIntent().getSerializableExtra("sortie"); //on récupère le batiment créer
            piece = (Piece) getIntent().getSerializableExtra("piece"); //on récupère le batiment créer
        }
    }

    public void clickChangerPorte(View view) {
        Intent intent = new Intent(this, AfficheMurActivity.class) ;
        intent.putExtra("maison",maison);
        intent.putExtra("sortie",porte);
        intent.putExtra("piece",piece);
        intent.putExtra("mur",mur);
        intent.putExtra("changement",1);
        startActivityForResult(intent,16);
    }
}