package com.example.housemap.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Monde {
    private List<Batiment> listBatiments;

    public Monde() {
        listBatiments = new ArrayList<>(10);
    }

    public void setBatiments(Batiment bat) {
        this.listBatiments.add(bat);
    }

    public List<Batiment> getListBatiments() {
        return listBatiments;
    }
    public Batiment getBatiment(int index){
        return listBatiments.get(index);
    }
}
