package com.example.housemap.model;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mur implements Serializable {
    private int noMur;
    private String nomPhoto;
    private List<Sortie> listSorties;
    private ImageView img;
    public Mur() {
        noMur = 0;
    }

    public Mur(int noMur, String nomPhoto,ImageView img) {
        this.noMur = noMur;
        this.nomPhoto = nomPhoto;
        listSorties = new ArrayList<>(10);
        this.img=img;
    }

    public Mur(int nbPrises, String nameFile) {
        this.noMur = nbPrises;
        this.nomPhoto = nameFile;
        listSorties = new ArrayList<>(10);
    }

    public int getNoPiece() {
        return noMur;
    }

    public int getNoMur() {
        return noMur;
    }

    public String getNomPhoto() {
        return nomPhoto;
    }
    public Sortie getSortie(int index){
        return listSorties.get(index);
    }

    public List<Sortie> getListSorties() {
        return listSorties;
    }
    public void modifierSortie(Sortie sortie){
        listSorties.set(sortie.getNoSortie(),sortie);
    }

    public void setNoPiece(int noPiece) {
        this.noMur = noPiece;
    }

    public void setPhoto(String nomPhoto) {
        this.nomPhoto = nomPhoto;
    }
    public void ajouterSortie(Sortie sortie){
        this.listSorties.add(sortie);
    }
    public void supprimerPorte(Sortie porte){
        this.listSorties.remove(porte.getNoSortie());
    }

}
