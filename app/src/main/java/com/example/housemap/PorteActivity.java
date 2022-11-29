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
        extras.putSerializable("piece",piece);
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        startActivityForResult(intent,7);
    }

    public void clickOuest(View view) {
        Intent intent = new Intent(PorteActivity.this, AfficheMurActivity.class);
        Bundle extras = new Bundle();
        Mur ouest = piece.getMur(3);
        extras.putSerializable("mur",ouest);
        extras.putSerializable("piece",piece);
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        startActivityForResult(intent,7);
    }

    public void clickNord(View view) {
        Intent intent = new Intent(PorteActivity.this, AfficheMurActivity.class);
        Bundle extras = new Bundle();
        Mur nord = piece.getMur(0);
        extras.putSerializable("mur",nord);
        extras.putSerializable("piece",piece);
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        startActivityForResult(intent,7);
    }

    public void clickSud(View view) {
        Intent intent = new Intent(PorteActivity.this, AfficheMurActivity.class);
        Bundle extras = new Bundle();
        Mur sud = piece.getMur(1);
        extras.putSerializable("mur",sud);
        extras.putSerializable("piece",piece);
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        startActivityForResult(intent,7);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(PorteActivity.this, PieceEnCoursActivity.class);
                Batiment maison = (Batiment) data.getSerializableExtra("maison");
                Piece piece = (Piece) data.getSerializableExtra("piece");
                intent.putExtra("maison", maison);
                intent.putExtra("piece", piece);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}