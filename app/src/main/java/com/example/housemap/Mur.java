package com.example.housemap;

import android.widget.ImageView;

public class Mur {
    private int noMur;
    private ImageView photo;
    public Mur() {
        noMur = 0;
    }

    public Mur(int noMur, ImageView photo) {
        this.noMur = noMur;
        this.photo = photo;
    }

    public int getNoPiece() {
        return noMur;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setNoPiece(int noPiece) {
        this.noMur = noPiece;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
}
