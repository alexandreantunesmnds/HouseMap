package com.example.housemap.model;

import android.graphics.Rect;

import java.io.Serializable;

public class Sortie implements Serializable {
    private Piece piece;
    private int noSortie;
    private int x;
    private int y;
    private int x2;
    private int y2;
    private Rect rect;

    public Sortie(Piece piece, int noSortie,int x,int y, int x2, int y2) {
        this.piece = piece;
        this.noSortie = noSortie;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }
    public String getNomPiece(){
        return this.piece.getNom();

    }
    public void setNoSortie(int noSortie){
        this.noSortie = noSortie;
    }


    public int getNoSortie() {
        return noSortie;
    }

    public void setCoord(int x,int y,int x2,int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX() {
        return x;
    }

    public void setNomSortie(String nomSortie){
        piece.setNom(nomSortie);
    }

    public Rect getRect() {
        rect = new Rect(x,y,x2,y2);
        rect.sort();
        return rect;
    }
}
