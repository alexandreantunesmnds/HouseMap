package com.example.housemap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.Batiment;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class ConsultPieceActvity extends AppCompatActivity {
    private static final String FILE_NAME="salon";
    private String mur1;
    private String mur2;
    private String mur3;
    private String mur4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_piece_actvity);



        StringBuffer output = new StringBuffer();
        File file = new File(this.getFilesDir(), FILE_NAME);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file.getAbsoluteFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while (true) {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            output.append(line + "\n");
        }
        String response = output.toString();
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.i("lecture sauv", response);

        try {
            JSONObject pieceObject = new JSONObject(response);
            mur1 = pieceObject.get("mur1").toString();
            mur2 = pieceObject.get("mur2").toString();
            mur3 = pieceObject.get("mur3").toString();
            mur4 = pieceObject.get("mur4").toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        FileInputStream fis = null;
        FileInputStream fis2 = null;
        FileInputStream fis3 = null;
        FileInputStream fis4 = null;
        try {
            fis = openFileInput(mur1);
            fis2 = openFileInput(mur2);
            fis3 = openFileInput(mur3);
            fis4 = openFileInput(mur4);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        Bitmap bm2 = BitmapFactory.decodeStream(fis2);
        Bitmap bm3 = BitmapFactory.decodeStream(fis3);
        Bitmap bm4 = BitmapFactory.decodeStream(fis4);
        ImageView img = findViewById(R.id.img_piece);
        ImageView img2 = findViewById(R.id.img_piece2);
        ImageView img3 = findViewById(R.id.img_piece3);
        ImageView img4 = findViewById(R.id.img_piece4);
        // Reste à mettre bm à mettre sur l’ImageView
        img.setImageBitmap(bm);
        img2.setImageBitmap(bm2);
        img3.setImageBitmap(bm3);
        img4.setImageBitmap(bm4);

    }
}