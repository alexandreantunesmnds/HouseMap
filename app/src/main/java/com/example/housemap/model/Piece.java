package com.example.housemap.model;

import android.util.Log;

import java.io.Serializable;

public class Piece implements Serializable {
    private String nom;
    private int noPiece;
    private Mur [] tabMur = new Mur[4];

    public Piece() {
    }

    public Piece(String nom, int noPiece) {
        this.nom = nom;
        this.noPiece = noPiece;
    }

    public String getNom() {
        return nom;
    }

    public int getNoPiece() {
        return noPiece;
    }

    public Mur[] getTabMur() {
        return tabMur;
    }
    public Mur getMur(int i){
        if(i<tabMur.length) {
            return tabMur[i];
        }
        return null;
    }
    public void ajouterMur(Mur mur){
        this.tabMur[mur.getNoMur()] = mur;
    }
    public void modifierMur(Mur mur){
        this.tabMur[mur.getNoMur()] = mur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNoPiece(int noPiece) {
        this.noPiece = noPiece;
    }

    public void setTabMur(Mur[] tabMur) {
        this.tabMur = tabMur;
    }
    public void decreaseNumeroPiece(){
        this.noPiece--;
    }
}
