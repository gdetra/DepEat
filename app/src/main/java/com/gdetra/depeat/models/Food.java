package com.gdetra.depeat.models;


import org.json.JSONException;
import org.json.JSONObject;

public class Food {
    private String name;
    private float price;
    private int quantity;
    private String urlImage;
    private String id;


    public Food(String name, float price, int quantity,String urlImage, String id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.urlImage = urlImage;
        this.id = id;
    }

    public Food(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString("name");
        this.price = (float)jsonObject.getDouble("price");
        this.quantity = 0;
        this.urlImage = jsonObject.getString("image_url");
        this.id = jsonObject.getString("_id");
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getId() {
        return id;
    }
}
