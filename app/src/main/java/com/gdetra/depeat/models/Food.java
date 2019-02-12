package com.gdetra.depeat.models;

import android.support.annotation.DrawableRes;

public class Food {
    private String name;
    private float price;
    private int quantity;
    @DrawableRes
    private int foodIdRes;


    public Food(String name, float price, int quantity,@DrawableRes int foodIdRes) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.foodIdRes = foodIdRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        if(quantity > 0) {
            this.quantity--;
        }
    }
    //public void setQuantity(int quantity) {
    //    this.quantity = quantity;
    //}

    @DrawableRes
    public int getFoodIdRes() {
        return foodIdRes;
    }

    public void setFoodIdRes(@DrawableRes int foodIdRes) {
        this.foodIdRes = foodIdRes;
    }
}
