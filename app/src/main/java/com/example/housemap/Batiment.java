package com.example.housemap;

import java.util.ArrayList;
import java.util.List;

public class Batiment {
    private String nomBat;
    private List<Piece> listPieces;
    public Batiment() {
        nomBat = "Batiment 1";
        listPieces = new ArrayList<>(10);
    }
    private void ajouterPiece(Piece piece){
        listPieces.add(piece);
    }

    private int getNbPieces(Piece piece){
        return listPieces.size();
    }
}
