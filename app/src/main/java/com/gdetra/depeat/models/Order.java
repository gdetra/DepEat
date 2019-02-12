package com.gdetra.depeat.models;

import java.util.List;

public class Order {
    private Restaurant restaurant;
    private List<Food> products;
    private float total;

    public Order(Restaurant restaurant, List<Food> products){
        this.restaurant = restaurant;
        this.products = products;
        setTotal(products);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Food> getProducts() {
        return products;
    }

    public void setProducts(List<Food> products) {
        this.products = products;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(List<Food> products) {
        this.total = 0;
        for(Food food : this.products){
            this.total += food.getQuantity()*food.getPrice();
        }
    }





}
