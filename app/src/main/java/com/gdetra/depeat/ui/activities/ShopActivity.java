package com.gdetra.depeat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gdetra.depeat.R;
import com.gdetra.depeat.models.Food;
import com.gdetra.depeat.ui.activities.adapters.FoodAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ShopActivity extends AppCompatActivity {
    RecyclerView foodRv;
    FoodAdapter foodAdapter;
    List<Food> listFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        foodRv = findViewById(R.id.food_rv);
        foodAdapter = new FoodAdapter(new ArrayList<>(Arrays.asList(
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background),
                new Food("Food name", 3.0, 50, R.drawable.ic_launcher_background)
        )));
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
}
