package com.example.housemap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Piece;

public class ConstructionActivity extends AppCompatActivity {
    private Batiment maison;
    private Piece piece;
    private final int RECYCL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
        for(int i = 0;i<maison.getNbPieces();i++){
            Toast.makeText(ConstructionActivity.this, "La pièce est :"+maison.getPiece(i).getNom(), Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void clickAjoutPiece(View view) {
        Intent intent = new Intent(ConstructionActivity.this, PieceEnCoursActivity.class) ;
        Bundle extras = new Bundle();
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        startActivityForResult(intent,3); ;
    }

    public void clickConsultPiece(View view) {
        if(!(maison.getNbPieces()==0)) {
            Intent intent = new Intent(this, ConsultPieceActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable("maison", maison);
            extras.putSerializable("visualisation",0);
            intent.putExtras(extras);
            startActivityForResult(intent, 3);
        }
        else{
            Toast.makeText(ConstructionActivity.this, "Vous n'avez aucune piece créées", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                maison = (Batiment) data.getSerializableExtra("maison");
                Toast.makeText(ConstructionActivity.this, "Nb pieces:"+maison.getNbPieces(), Toast.LENGTH_SHORT).show();

            }
        }
    }
}