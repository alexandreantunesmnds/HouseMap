package com.example.housemap.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Batiment implements Serializable {
    private String nomBat;
    private List<Piece> listPieces;
    public Batiment() {
        nomBat = "Batiment 1";
        listPieces = new ArrayList<>(10);
    }
    public void ajouterPiece(Piece piece){
        listPieces.add(piece);
    }

    public int getNbPieces(){
        return listPieces.size();
    }
}
