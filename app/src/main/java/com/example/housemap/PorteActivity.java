package com.example.housemap;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Mur;
import com.example.housemap.model.Piece;

public class PorteActivity extends AppCompatActivity {
    private Piece piece;
    private Batiment maison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porte);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        if(getIntent().getExtras() != null) {
            piece = (Piece) getIntent().getSerializableExtra("piece");
            maison = (Batiment) getIntent().getSerializableExtra("maison");
        }
    }

    public void clickEst(View view) {
        Intent intent = new Intent(PorteActivity.this, AfficheMurActivity.class);
        Bundle extras = new Bundle();
        Mur est = piece.getMur(2);
        extras.putSerializable("mur",est);
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        startActivityForResult(intent,4);
    }

    public void clickOuest(View view) {
        Intent intent = new Intent(PorteActivity.this, AfficheMurActivity.class);
        Bundle extras = new Bundle();
        Mur ouest = piece.getMur(3);
        extras.putSerializable("mur",ouest);
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        startActivityForResult(intent,4);
    }

    public void clickNord(View view) {
        Intent intent = new Intent(PorteActivity.this, AfficheMurActivity.class);
        Bundle extras = new Bundle();
        Mur nord = piece.getMur(0);
        extras.putSerializable("mur",nord);
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        startActivityForResult(intent,4);
    }

    public void clickSud(View view) {
        Intent intent = new Intent(PorteActivity.this, AfficheMurActivity.class);
        Bundle extras = new Bundle();
        Mur sud = piece.getMur(1);
        extras.putSerializable("mur",sud);
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        startActivityForResult(intent,4);
    }
}