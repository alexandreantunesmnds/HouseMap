package com.example.housemap;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.housemap.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AfficheMurActivity extends AppCompatActivity {
    private Mur mur;
    private ImageView img;
    private SurfaceView sv;
    private Paint paint;
    private int x = 0;
    private int y = 0;
    private int x2 = 0;
    private int y2 = 0;
    private Rect rect;
    private SurfaceHolder holder;
    private Batiment maison;
    private String nomPiece;
    private Piece pieceEnCours;
    private Sortie sortie;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_mur);
        img = findViewById(R.id.img_mur);
        if (getIntent().getExtras() != null) {
            mur = (Mur) getIntent().getSerializableExtra("mur");
            pieceEnCours = (Piece) getIntent().getSerializableExtra("piece");
            maison = (Batiment) getIntent().getSerializableExtra("maison");
            setResult(RESULT_OK, getIntent());
        }
        FileInputStream fis;
        try {
            fis = openFileInput(mur.getNomPhoto());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        // Reste à mettre bm à mettre sur l’ImageView
        img.setImageBitmap(bm);
        sv = findViewById(R.id.surfaceView);
        sv.setZOrderOnTop(true);
        holder = sv.getHolder();
        holder.setFormat(PixelFormat.TRANSPARENT);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (rect != null) {
                        coupeImage();
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (event.getPointerCount() == 2) {
                        x = (int) event.getX(0);
                        y = (int) event.getY(0);
                        x2 = (int) event.getX(1);
                        y2 = (int) event.getY(1);
                        rect = new Rect(x, y, x2, y2);
                        rect.sort();
                        dessinerRectangle(rect);
                    }
                }
                return true;
            }
        });
    }
    public void dessinerRectangle(Rect rect){
        Canvas canvas = holder.lockCanvas();
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawRect(rect, paint);
        holder.unlockCanvasAndPost(canvas);
    }
    public void coupeImage() {
        AlertDialog.Builder couper = new AlertDialog.Builder(this);
        BitmapDrawable bmpDraw = (BitmapDrawable) img.getDrawable();
        Bitmap bmp = bmpDraw.getBitmap();
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        couper.setView(input);

        couper.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                nomPiece = String.valueOf(input.getText());
                int numSortie = FabriqueNumero.getInstance().getNumeroSortie();
                if (maison.pieceIsInBat(nomPiece)) { //si la piece existe deja
                    sortie = new Sortie(maison.getPiece(nomPiece), numSortie, x, y, x2, y2);
                    mur.ajouterSortie(sortie);
                    Toast.makeText(AfficheMurActivity.this, "La sortie est ajoutée ", Toast.LENGTH_SHORT).show();
                } else {
                    if (!String.valueOf(input.getText()).equals("")) {//si on a bien écrit quelque chose
                        nomPiece = String.valueOf(input.getText());
                        int numPiece = FabriqueNumero.getInstance().getNumeroPiece();
                        Piece piece = new Piece(nomPiece, numPiece);
                        maison.ajouterPiece(piece); //on ajoute la pièce au modèle si elle n'existe pas encore
                        sortie = new Sortie(maison.getPiece(nomPiece), numSortie, x, y, x2, y2);
                        mur.ajouterSortie(sortie);
                        pieceEnCours.modifierMur(mur);
                        maison.mettreAJourPiece(pieceEnCours);
                        Toast.makeText(AfficheMurActivity.this, "La sortie est ajoutée ", Toast.LENGTH_SHORT).show();
                    } else {
                        //Sinon on fait rien
                    }

                }
            }
            });

        couper.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled
            }
        });

        Bitmap redimension = Bitmap.createScaledBitmap(bmp, img.getWidth(), img.getHeight(), false);
        if (x >= 0 && y >= 0) {
            int x = (int) (rect.left - img.getX());
            int y = (int) (rect.top - img.getX());
            int hauteur = (int) ((((rect.bottom - img.getY())) - (rect.top - img.getY())));
            int largeur = (int) ((((rect.right - img.getX())) - (rect.left - img.getX())));

            if (y + hauteur < redimension.getHeight()) {
                Bitmap rog = Bitmap.createBitmap(redimension, x, y, largeur, hauteur);

                ImageView rogView = new ImageView(this); //image récupéréé de la sortie
                rogView.setImageBitmap(rog);
                couper.show();

            } else {
                Toast.makeText(this, "Veuillez sélectionner une zone à l'intérieure de l'image", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Veuillez sélectionner une zone à l'intérieure de l'image", Toast.LENGTH_SHORT).show();
        }


    }

    public void clickValider(View view) {
        //Toast.makeText(this, "sortie vers : "+ pieceEnCours.getMur(0).getSortie(0).getNomPiece(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AfficheMurActivity.this, PieceEnCoursActivity.class);
        intent.putExtra("maison", maison);
        intent.putExtra("piece", pieceEnCours);
        //intent.putExtra("mur", mur);
        setResult(RESULT_OK, intent);
        finish();
    }
}