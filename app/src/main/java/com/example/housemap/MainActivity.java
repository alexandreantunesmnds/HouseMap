package com.example.housemap;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;

public class MainActivity extends AppCompatActivity {
    private Batiment maison = new Batiment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "La pièce a été ajoutée au batiment, taille bat "+this.maison.getNbPieces(), Toast.LENGTH_SHORT).show();
    }

    public void clickConstruction(View view) {
        Intent intent = new Intent(MainActivity.this, ConstructionActivity.class) ;
        intent.putExtra("maison", maison); //where user is an instance of User object
        startActivity(intent) ;
    }

    public void clickVisu(View view) {
    }
}