package com.example.housemap;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Piece;

public class ChangerActivity extends AppCompatActivity {
    private Piece piece;
    private Batiment maison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer);
        if(getIntent().getExtras() != null) {
            piece = (Piece) getIntent().getSerializableExtra("piece");
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
    }

    public void clickSupprimer(View view) {
        Intent intent = new Intent(ChangerActivity.this, ConsultPieceActvity.class);
        maison.retirerPiece(piece);
        Toast.makeText(ChangerActivity.this, "nb pieces : "+maison.getNbPieces(), Toast.LENGTH_SHORT).show();
        intent.putExtra("maison",maison);
        setResult(RESULT_OK, intent);
        finish();
    }
}