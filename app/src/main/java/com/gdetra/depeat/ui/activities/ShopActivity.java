package com.gdetra.depeat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gdetra.depeat.R;
import com.gdetra.depeat.models.Food;
import com.gdetra.depeat.models.Restaurant;
import com.gdetra.depeat.ui.activities.adapters.FoodAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;


public class ShopActivity extends AppCompatActivity implements FoodAdapter.OnQuantityChangedListener {
    RecyclerView foodRv;
    FoodAdapter foodAdapter;
    Restaurant restaurant;
    ProgressBar progressBar;
    TextView restaurantNameTv;
    TextView restaurantDescriptionTv;
    TextView totalTv;
    TextView minOrderTv;
    Button checkOutBtn;
    double total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        foodRv = findViewById(R.id.food_rv);
        progressBar = findViewById(R.id.progress);
        restaurantNameTv = findViewById(R.id.restaurant_name);
        restaurantDescriptionTv = findViewById(R.id.restaurant_description);
        totalTv = findViewById(R.id.total_tv);
        minOrderTv = findViewById(R.id.min_order_tv);
        checkOutBtn = findViewById(R.id.checkout_btn);
        restaurant = new Restaurant("Panucci", R.drawable.ic_launcher_background, "A little description of Panucci", 3.0F);
        progressBar.setMax((int)restaurant.getMinImport()*100);
        totalTv.setText(String.valueOf(total) + getString(R.string.euro));
        minOrderTv.setText(String.valueOf(restaurant.getMinImport()) + getString(R.string.euro));
        restaurantNameTv.setText(restaurant.getName());
        restaurantDescriptionTv.setText(restaurant.getDescription());
        foodAdapter = new FoodAdapter(new ArrayList<>(Arrays.asList(
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
        foodAdapter.setOnQuantityChangedListener(this);
        ((SimpleItemAnimator) foodRv.getItemAnimator()).setSupportsChangeAnimations(false);
        foodRv.setAdapter(foodAdapter);
        foodRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Menu inflater is used to inject a menu inside this activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (item.getItemId() == R.id.checkout_menu) {
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateTotal(double item){
        total += item;
        totalTv.setText(String.valueOf(total) + getString(R.string.euro));
    }

    private void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    @Override
    public void onChange(double price) {
        updateTotal(price);
        updateProgress((int)total * 100);
        tryEnableCheckoutBtn();
    }

    private void tryEnableCheckoutBtn(){
        if(total >= restaurant.getMinImport()){
            checkOutBtn.setEnabled(true);
        }else{
            checkOutBtn.setEnabled(false);
        }
    }



}
