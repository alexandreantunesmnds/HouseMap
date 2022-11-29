package com.example.housemap.model;

import android.graphics.Rect;

import java.io.Serializable;

public class Sortie implements Serializable {
    private Piece piece;
    private int noSortie;
    private int x = 0;
    private int y = 0;
    private int x2 = 0;
    private int y2 = 0;

    public Sortie(Piece piece, int noSortie,int x,int y, int x2, int y2) {
        this.piece = piece;
        this.noSortie = noSortie;
    }
    public String getNomPiece(){
        return this.piece.getNom();

    }
}
