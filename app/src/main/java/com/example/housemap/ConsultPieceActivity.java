package com.example.housemap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class ConsultPieceActivity extends AppCompatActivity implements RecyclerViewInterface{
    private static final String FILE_NAME = "salon";
    RecyclerView recyclerView;
    List<Piece> pieceList;
    PieceAdapter adapter;
    Batiment maison;
    private final int RECYCL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_piece_actvity);
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
        recyclerView = findViewById(R.id.porte_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pieceList = new ArrayList<>();
        pieceList = maison.getListPieces();
        adapter = new PieceAdapter(this,pieceList,this);
        recyclerView.setAdapter(adapter);
        this.getPieces();
        Toast.makeText(ConsultPieceActivity.this, "La pièce est :"+pieceList.get(0).getNom(), Toast.LENGTH_SHORT).show();
        //printPieces();
    }


    @SuppressLint("NotifyDataSetChanged")
    private void getPieces() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(this, ChangerActivity.class) ;
        Piece pieceChoisie = pieceList.get(position);
        intent.putExtra("maison",maison);
        intent.putExtra("piece",pieceChoisie);
        startActivityForResult(intent,10);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                //Toast.makeText(ConsultPieceActvity.this, "nb pieces : "+maison.getNbPieces(), Toast.LENGTH_SHORT).show();
                maison = (Batiment) data.getSerializableExtra("maison");
                pieceList = maison.getListPieces();
                adapter = new PieceAdapter(this,pieceList,this);
                recyclerView.setAdapter(adapter);
                this.getPieces();
            }
        }
    }
}