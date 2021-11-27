package com.example.eatup.ui.model;

import android.graphics.drawable.Drawable;

public class ListResto {
    public int restaurantId;
    public String namaResto;
    public String jalanResto;
    public String bintangResto;
    public String seeResto;
    public Drawable image;

    public ListResto() {

    }

    public ListResto(int restaurantId, String namaResto, String jalanResto, Double bintangResto, Integer seeResto, Drawable image) {
        this.restaurantId = restaurantId;
        this.namaResto = namaResto;
        this.jalanResto = jalanResto;
        this.bintangResto = Double.toString(bintangResto);
        this.seeResto = Integer.toString(seeResto);
        this.image = image;
    }

}
