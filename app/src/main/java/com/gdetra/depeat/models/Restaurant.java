package com.gdetra.depeat.models;

import android.support.annotation.DrawableRes;

public class Restaurant {
    private String name;
    @DrawableRes
    private int image;
    private String description;

    public Restaurant(String name,@DrawableRes int image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
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
}
