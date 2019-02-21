package com.gdetra.depeat.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Restaurant {
    public static final String ENDPOINT = "restaurants";
    private String name;
    private String urlImage;
    private String address;
    private float minImport;
    private String id;
    private List<Food> products;

    public Restaurant(String name, String urlImage, String address, float minImport) {
        this.name = name;
        this.urlImage = urlImage;
        this.address = address;
        this.minImport = minImport;
        getData();
    }

    public Restaurant(JSONObject jsonRestaurant) throws JSONException {
        this.name = jsonRestaurant.getString("name");
        this.id = jsonRestaurant.getString("id");
        this.address = jsonRestaurant.getString("address");
        this.minImport = (float)jsonRestaurant.getDouble("min_order");
        this.urlImage = jsonRestaurant.getString("image_url");
        this.products = new ArrayList<>();
        JSONArray array = jsonRestaurant.getJSONArray("products");
        for(int i = 0; i < array.length(); i++){
            this.products.add(new Food(array.getJSONObject(i)));
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                new Food("Food name", 4.0F, 0, "",""),
                new Food("Food name", 3.0F, 0, "",""),
                new Food("Food name", 3.0F, 0, "",""),
                new Food("Food name", 3.0F, 0, "",""),
                new Food("Food name", 3.0F, 0, "",""),
                new Food("Food name", 3.0F, 0, "",""),
                new Food("Food name", 3.0F, 0, "",""),
                new Food("Food name", 3.0F, 0, "",""),
                new Food("Food name", 3.0F, 0, "",""),
                new Food("Food name", 3.0F, 0, "","")
        )));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
