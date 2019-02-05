package com.gdetra.depeat.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gdetra.depeat.R;
import com.gdetra.depeat.Utils;
import com.gdetra.depeat.models.Restaurant;
import com.gdetra.depeat.ui.activities.adapters.RestaurantAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    List<Restaurant> listRestaurants;
    RecyclerView restaurantsRV;
    boolean isGridLayoutSelected;
    RestaurantAdapter restaurantAdapter;
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        listRestaurants = new ArrayList<>(Arrays.asList(
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant"),
                new Restaurant("Panucci", R.drawable.ic_launcher_background, "Panucci's restaurant")
        ));
        prefs = getSharedPreferences(Utils.PACKAGE_NAME, Context.MODE_PRIVATE);
        if(prefs != null){
            isGridLayoutSelected = prefs.getBoolean(Utils.IS_GRID_LAYOUT_SELECTED, false);
            if(isGridLayoutSelected){
                setSavedLayout(new GridLayoutManager(this, 2), isGridLayoutSelected);
            }else{
                setSavedLayout(new LinearLayoutManager(this), isGridLayoutSelected);
            }
        }else{
            setSavedLayout(new LinearLayoutManager(this), isGridLayoutSelected);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isGridLayoutSelected) {
            changeItemsLayout(item, new LinearLayoutManager(this), false, R.drawable.ic_apps_black_24dp);
            return true;
        } else {
            changeItemsLayout(item, new GridLayoutManager(this, 2), true, R.drawable.ic_format_list_bulleted_black_24dp);
            return true;
        }

    }

    private void changeItemsLayout(MenuItem item, RecyclerView.LayoutManager layoutManager, boolean isGridLayoutSelected, @DrawableRes int resId) {
        this.layoutManager = layoutManager;
        restaurantsRV.setLayoutManager(layoutManager);
        this.isGridLayoutSelected = isGridLayoutSelected;
        prefs.edit().putBoolean(Utils.IS_GRID_LAYOUT_SELECTED, this.isGridLayoutSelected).apply();
        restaurantAdapter = new RestaurantAdapter(listRestaurants, this.isGridLayoutSelected);
        restaurantsRV.setAdapter(restaurantAdapter);
        item.setIcon(resId);
    }

    private void setSavedLayout(RecyclerView.LayoutManager layoutManager, boolean isGridLayoutSelected) {
        restaurantAdapter = new RestaurantAdapter(listRestaurants, isGridLayoutSelected);
        this.layoutManager = layoutManager;
        restaurantsRV = findViewById(R.id.restaurants_rv);
        restaurantsRV.setAdapter(restaurantAdapter);
        restaurantsRV.setLayoutManager(layoutManager);
    }
}
