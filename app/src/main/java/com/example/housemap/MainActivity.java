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
    }

    public void clickConstruction(View view) {
        Intent intent = new Intent(MainActivity.this, ConstructionActivity.class) ;
        Bundle extras = new Bundle();
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        startActivity(intent) ;
    }

    public void clickVisu(View view) {
    }
}