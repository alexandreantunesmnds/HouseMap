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
    private int visualisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_piece_actvity);
        if(getIntent().getExtras() != null) {
            maison = (Batiment) getIntent().getSerializableExtra("maison"); //on récupère le batiment créer
            visualisation = (int) getIntent().getSerializableExtra("visualisation"); //on récupère le batiment créer

        }
        recyclerView = findViewById(R.id.porte_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pieceList = new ArrayList<>();
        pieceList = maison.getListPieces();
        adapter = new PieceAdapter(this,pieceList,this);
        recyclerView.setAdapter(adapter);
        this.getPieces();
        //printPieces();
    }


    @SuppressLint("NotifyDataSetChanged")
    private void getPieces() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClick(int position) {
        if(visualisation==0) {
            Intent intent = new Intent(this, ChangerActivity.class);
            Piece pieceChoisie = pieceList.get(position);
            Toast.makeText(this, "Voici votre num de piece: "+ pieceChoisie.getNoPiece(), Toast.LENGTH_SHORT).show();
            intent.putExtra("maison", maison);
            intent.putExtra("piece", pieceChoisie);
            startActivityForResult(intent, 10);
        }
        else{
            Intent intent = new Intent(this, VisualisationActivity.class);
            Piece pieceChoisie = pieceList.get(position);
            intent.putExtra("maison", maison);
            intent.putExtra("piece", pieceChoisie);
            startActivity(intent);
        }
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
                Intent intent = new Intent(ConsultPieceActivity.this, ConstructionActivity.class);
                Bundle extras2 = new Bundle();
                extras2.putSerializable("maison",maison);
                intent.putExtras(extras2);
                Sauvegarde save = Sauvegarde.getInstance();
                save.saveProject(maison,this);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}