package com.example.housemap.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void mettreAJourPiece(Piece piece){
        listPieces.set(piece.getNoPiece(),piece);
    }

    public void retirerPiece(Piece piece){
        for(int i =0;i<listPieces.size();i++){
            if(piece.getNoPiece()==listPieces.get(i).getNoPiece()){
                listPieces.remove(i);
            }
        }
    }

    public int getNbPieces(){
        return listPieces.size();
    }

    public Piece getPiece(int index){
        return listPieces.get(index);
    }
    public List<Piece> getListPieces(){
        return listPieces;
    }
    public Boolean pieceIsInBat(String nomPiece){
        for (Piece listPiece : listPieces) {
            if (Objects.equals(listPiece.getNom(), nomPiece)) {
                return true;
            }
        }
        return false;
    }
    public Piece getPiece(String nomPiece){
        for (Piece listPiece : listPieces) {
            if (Objects.equals(listPiece.getNom(), nomPiece)) {
                return listPiece;
            }
        }
        return null;
    }
}
