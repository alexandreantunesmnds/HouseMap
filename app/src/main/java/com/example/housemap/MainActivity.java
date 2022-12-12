package com.example.housemap;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import com.example.housemap.model.FabriqueNumero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private Batiment maison;
    private Sauvegarde save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = Sauvegarde.getInstance();
        maison = save.getProject("appart", this);
        if(maison==null) {
            maison = new Batiment();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.charger:
                maison = save.getProject("appart",this);
            case R.id.quitter:
                finish();
            case R.id.supprime_menu:
                if(maison!=null){
                    save.deleteProjet(this.getFilesDir(), "appart");
                    maison.supprimerTout();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clickConstruction(View view) {
        Intent intent = new Intent(MainActivity.this, ConstructionActivity.class) ;
        Bundle extras = new Bundle();
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        startActivityForResult(intent,20);
    }

    public void clickVisu(View view) {
        if(maison.getNbPieces()>0) {
            Intent intent = new Intent(MainActivity.this, ConsultPieceActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable("maison", maison);
            extras.putSerializable("visualisation", 1);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                maison = (Batiment) data.getSerializableExtra("maison");
            }
        }
    }
}