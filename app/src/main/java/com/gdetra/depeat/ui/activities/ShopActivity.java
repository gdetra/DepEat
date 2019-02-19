package com.gdetra.depeat.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gdetra.depeat.R;
import com.gdetra.depeat.Utils;
import com.gdetra.depeat.models.Auth;
import com.gdetra.depeat.models.Restaurant;
import com.gdetra.depeat.services.RestController;
import com.gdetra.depeat.ui.activities.adapters.FoodAdapter;
import com.gdetra.depeat.ui.activities.adapters.RestaurantAdapter;

import org.json.JSONException;
import org.json.JSONObject;


public class ShopActivity extends AppCompatActivity implements FoodAdapter.OnQuantityChangedListener, View.OnClickListener, Response.Listener<String>, Response.ErrorListener {
    private static final int LOGIN_REQUEST_CODE = 3000;
    private static final int LOGIN_FOR_CHECKOUT_REQUEST_CODE = 3001;
    RecyclerView foodRv;
    FoodAdapter foodAdapter;
    Restaurant restaurant;
    ProgressBar progressBar;
    TextView restaurantNameTv;
    TextView restaurantDescriptionTv;
    TextView totalTv;
    TextView minOrderTv;
    Button checkOutBtn;
    String id;
    RestController controller;
    SharedPreferences prefs;
    Menu menu;
    double total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        prefs = getSharedPreferences(Utils.PACKAGE_NAME, Context.MODE_PRIVATE);
        controller = new RestController(this);
        id = getIntent().getStringExtra(RestaurantAdapter.RESTAURANT_ID_KEY);
        controller.getRequest(Restaurant.ENDPOINT.concat("/").concat(id), this, this);
        foodRv = findViewById(R.id.food_rv);
        progressBar = findViewById(R.id.progress);
        restaurantNameTv = findViewById(R.id.restaurant_name);
        restaurantDescriptionTv = findViewById(R.id.restaurant_description);
        totalTv = findViewById(R.id.total_tv);
        minOrderTv = findViewById(R.id.min_order_tv);
        checkOutBtn = findViewById(R.id.checkout_btn);
        checkOutBtn.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Menu inflater is used to inject a menu inside this activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
        if (prefs.getString(Auth.AUTH, null) != null) {
            menu.findItem(R.id.login_menu).setTitle(getString(R.string.profile_fixed))
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            startActivity(new Intent(ShopActivity.this, ProfileActivity.class));
                            return true;
                        }
                    });
        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {
            if (prefs.getString(Auth.AUTH, null) != null) {
                startActivity(new Intent(ShopActivity.this, ProfileActivity.class));
                return true;
            }else{
                startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST_CODE);
                return true;
            }
        } else if (item.getItemId() == R.id.checkout_menu) {
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateTotal(double item) {
        total += item;
        totalTv.setText(String.valueOf(total) + getString(R.string.euro));
    }

    private void updateProgress(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void onChange(double price) {
        updateTotal(price);
        updateProgress((int) total * 100);
        tryEnableCheckoutBtn();
    }

    private void tryEnableCheckoutBtn() {
        if (total >= restaurant.getMinImport()) {
            checkOutBtn.setEnabled(true);
        } else {
            checkOutBtn.setEnabled(false);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkout_btn) {
            String jwt = prefs.getString(Auth.AUTH, null);
            if (jwt != null) {
                Intent intent = new Intent(this, CheckoutActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, LOGIN_FOR_CHECKOUT_REQUEST_CODE);
            }
        }
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            menu.findItem(R.id.login_menu).setTitle(getString(R.string.profile_fixed))
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            startActivity(new Intent(ShopActivity.this, ProfileActivity.class));
                            return true;
                        }
                    });
        } else if (requestCode == LOGIN_FOR_CHECKOUT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(this, CheckoutActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            restaurant = new Restaurant(jsonObject);
            setUI(restaurant);
        } catch (JSONException e) {
            Log.e(ShopActivity.class.toString(), e.getMessage());
        }

    }

    private void setUI(Restaurant restaurant) {
        progressBar.setMax((int) restaurant.getMinImport() * 100);
        totalTv.setText(String.valueOf(total) + getString(R.string.euro));
        minOrderTv.setText(String.valueOf(restaurant.getMinImport()) + getString(R.string.euro));
        restaurantNameTv.setText(restaurant.getName());
        restaurantDescriptionTv.setText(restaurant.getAddress());
        foodAdapter = new FoodAdapter(restaurant);
        foodAdapter.setOnQuantityChangedListener(this);
        ((SimpleItemAnimator) foodRv.getItemAnimator()).setSupportsChangeAnimations(false);
        foodRv.setAdapter(foodAdapter);
        foodRv.setLayoutManager(new LinearLayoutManager(this));
    }
}
