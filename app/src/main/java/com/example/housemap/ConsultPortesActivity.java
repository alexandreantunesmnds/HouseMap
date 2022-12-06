package com.example.housemap;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.Mur;
import com.example.housemap.model.Piece;
import com.example.housemap.model.Sortie;

import java.util.ArrayList;
import java.util.List;

public class ConsultPortesActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Batiment maison;
    private Mur mur;
    private Piece piece;
    private RecyclerView recyclerView;
    private List<Sortie> sortiesList;
    private SortieAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_portes);
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
            mur = (Mur) getIntent().getSerializableExtra("mur"); //on récupère le batiment créer
            piece = (Piece) getIntent().getSerializableExtra("piece"); //on récupère le batiment créer

        }
        recyclerView = findViewById(R.id.porte_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sortiesList = new ArrayList<>();
        sortiesList = mur.getListSorties();
        adapter = new SortieAdapter(this,sortiesList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(this, ModifPorteActivity.class) ;
        Sortie porteChoisie = sortiesList.get(position);
        intent.putExtra("maison",maison);
        intent.putExtra("porte",porteChoisie);
        intent.putExtra("piece",piece);
        intent.putExtra("mur",mur);
        startActivityForResult(intent,15);
    }
}