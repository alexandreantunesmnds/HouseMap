package com.example.housemap.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mur implements Serializable {
    private int noMur;
    private String nomPhoto;
    private List<Sortie> listSorties;
    public Mur() {
        noMur = 0;
    }

    public Mur(int noMur, String nomPhoto) {
        this.noMur = noMur;
        this.nomPhoto = nomPhoto;
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

    public void setNoPiece(int noPiece) {
        this.noMur = noPiece;
    }

    public void setPhoto(String nomPhoto) {
        this.nomPhoto = nomPhoto;
    }
    public void ajouterSortie(Sortie sortie){
        this.listSorties.add(sortie);
    }

}
