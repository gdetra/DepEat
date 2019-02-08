package com.gdetra.depeat.models;

import android.support.annotation.DrawableRes;

import com.gdetra.depeat.R;

import java.util.ArrayList;
import java.util.Arrays;
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
        getData();
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

    private void getData(){
        setProducts(new ArrayList<>(Arrays.asList(
                new Food("Food name", 1.0, 0, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 0, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 0, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 0, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 0, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 0, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 0, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 0, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 0, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 0, R.drawable.ic_launcher_background)
        )));
    }
}
