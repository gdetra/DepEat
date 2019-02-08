package com.gdetra.depeat.models;

import android.support.annotation.DrawableRes;

import java.util.List;

public class Restaurant {
    private String name;
    @DrawableRes
    private int image;
    private String description;
    private float minImport;
    private List<Food> products;

    public Restaurant(String name, @DrawableRes int image, String description, float minImport) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.minImport = minImport;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(@DrawableRes int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMinImport() {
        return minImport;
    }

    public void setMinImport(float minImport) {
        this.minImport = minImport;
    }

    public List<Food> getProducts() {
        return products;
    }

    public void setProducts(List<Food> products) {
        this.products = products;
    }
}
