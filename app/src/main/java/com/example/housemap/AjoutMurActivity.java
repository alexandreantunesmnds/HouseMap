package com.example.housemap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AjoutMurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_mur);
        setResult(RESULT_OK) ; // ou RESULT_CANCELED
        finish();
    }
}