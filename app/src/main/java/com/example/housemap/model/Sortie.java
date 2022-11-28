package com.example.housemap.model;

import android.graphics.Rect;

public class Sortie {
    Piece piece;
    int noSortie;
    Rect zoneSortie;

    public Sortie(Piece piece, int noSortie, Rect zoneSortie) {
        this.piece = piece;
        this.noSortie = noSortie;
        this.zoneSortie = zoneSortie;
    }
    public String getNomPiece(){
        return this.piece.getNom();

    }
}
