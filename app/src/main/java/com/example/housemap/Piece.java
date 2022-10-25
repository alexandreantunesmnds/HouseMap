package com.example.housemap;

public class Piece {
    private String nom;
    private int noPiece;
    private Mur [] tabMur = new Mur[4];
    public Piece() {
        nom = "Nom de piece inconnu";
        noPiece = 0;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNoPiece(int noPiece) {
        this.noPiece = noPiece;
    }

    public void setTabMur(Mur[] tabMur) {
        this.tabMur = tabMur;
    }
}
