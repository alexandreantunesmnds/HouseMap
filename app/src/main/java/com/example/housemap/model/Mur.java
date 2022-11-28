package com.example.housemap.model;

import android.widget.ImageView;

import java.io.Serializable;

public class Mur implements Serializable {
    private int noMur;
    private String nomPhoto;
    public Mur() {
        noMur = 0;
    }

    public Mur(int noMur, String nomPhoto) {
        this.noMur = noMur;
        this.nomPhoto = nomPhoto;
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

    public void setNoPiece(int noPiece) {
        this.noMur = noPiece;
    }

    public void setPhoto(String nomPhoto) {
        this.nomPhoto = nomPhoto;
    }
}
