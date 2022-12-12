package com.example.housemap;

import android.content.Context;
import android.util.Log;
import com.example.housemap.model.Batiment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Sauvegarde {
    private static final Sauvegarde sauvegarde = new Sauvegarde();

    private Sauvegarde(){}

    public static Sauvegarde getInstance(){
        return sauvegarde;
    }

    public void saveProject(Batiment maison, Context context){
        ObjectOutputStream oos = null;

        final FileOutputStream fichierOut;
        try {
            fichierOut = context.openFileOutput(maison.getNomBat()+".ser",MODE_PRIVATE);
            oos = new ObjectOutputStream(fichierOut);
            oos.writeObject(maison);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (oos != null) {
                oos.close();
            }
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    public Batiment getProject(String name, Context context){
        ObjectInputStream ois = null;
        Batiment maison = null;

        try {
            final FileInputStream fichierIn = context.openFileInput(name+".ser");
            ois = new ObjectInputStream(fichierIn);
            maison = (Batiment) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        try {
            if (ois != null) {
                ois.close();
            }
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
        return maison;
    }

    public void deleteProjet(File repertoire, String nomProjet){
        for(File fichier : repertoire.listFiles()){
            if(!fichier.isDirectory()){
                if(fichier.getName().contains(nomProjet)){
                    fichier.delete();
                }
            }
        }
    }
}
