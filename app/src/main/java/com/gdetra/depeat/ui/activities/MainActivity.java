package com.gdetra.depeat.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gdetra.depeat.R;
import com.gdetra.depeat.Utils;
import com.gdetra.depeat.models.Restaurant;
import com.gdetra.depeat.ui.activities.adapters.RestaurantAdapter;
import com.google.firebase.FirebaseApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);
    }

    List<Restaurant> listRestaurants;
    RecyclerView restaurantsRV;
    boolean isGridLayoutSelected;
    RestaurantAdapter restaurantAdapter;
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        listRestaurants = new ArrayList<>();
        prefs = getSharedPreferences(Utils.PACKAGE_NAME, Context.MODE_PRIVATE);

        if (prefs != null) {
            isGridLayoutSelected = prefs.getBoolean(Utils.IS_GRID_LAYOUT_SELECTED, false);
            if (isGridLayoutSelected) {
                setSavedLayout(new GridLayoutManager(this, 2), isGridLayoutSelected);
            } else {
                setSavedLayout(new LinearLayoutManager(this), isGridLayoutSelected);
            }
        } else {
            setSavedLayout(new LinearLayoutManager(this), isGridLayoutSelected);
        }

        request();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shop, menu);
        MenuItem item = menu.findItem(R.id.change_layout_btn);
        if (isGridLayoutSelected) {
             item.setIcon(R.drawable.ic_list_white_24dp);
            return true;
        } else {
            item.setIcon(R.drawable.ic_apps_white_24dp);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isGridLayoutSelected) {
            changeItemsLayout(item, new LinearLayoutManager(this), false, R.drawable.ic_apps_white_24dp);
            return true;
        } else {
            changeItemsLayout(item, new GridLayoutManager(this, 2), true, R.drawable.ic_list_white_24dp);
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

    private void request(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://5ba19290ee710f0014dd764c.mockapi.io/api/v1/restaurant";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("mainM", response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i = 0; i<jsonArray.length(); i++){
                                Restaurant r = new Restaurant(jsonArray.getJSONObject(i));
                                listRestaurants.add(r);
                            }
                            restaurantAdapter.setRestaurantList(listRestaurants);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("main", String.valueOf(error.networkResponse));
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

