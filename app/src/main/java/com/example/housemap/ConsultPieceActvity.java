package com.example.housemap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Piece;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultPieceActvity extends AppCompatActivity {
    private static final String FILE_NAME = "salon";
    RecyclerView recyclerView;
    List<Piece> pieceList;
    PieceAdapter adapter;
    Batiment maison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_piece_actvity);
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
        }
        recyclerView = findViewById(R.id.piece_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pieceList = new ArrayList<>();
        adapter = new PieceAdapter(this,pieceList);
        recyclerView.setAdapter(adapter);
        this.getPieces();
        //printPieces();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            maison = (Batiment) extras.get("maison");
            pieceList = maison.getListPieces();
            Toast.makeText(ConsultPieceActvity.this, "La pièce est :"+pieceList.get(0), Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private void getPieces() {
        adapter.notifyDataSetChanged();
    }
}