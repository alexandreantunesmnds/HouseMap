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

public class MainActivity extends AppCompatActivity {
    private Batiment maison;
    private Sauvegarde save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maison = new Batiment();
        save = Sauvegarde.getInstance();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sauv_menu:
                save.saveProject(maison,this);
            case R.id.charge_menu:
                maison = save.getProject("appart",this);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clickConstruction(View view) {
        Intent intent = new Intent(MainActivity.this, ConstructionActivity.class) ;
        Bundle extras = new Bundle();
        extras.putSerializable("maison",maison);
        intent.putExtras(extras);
        startActivityForResult(intent,20); ;
    }

    public void clickVisu(View view) {
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