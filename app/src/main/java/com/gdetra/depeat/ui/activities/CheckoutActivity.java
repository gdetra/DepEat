package com.gdetra.depeat.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.gdetra.depeat.R;
import com.gdetra.depeat.models.Food;
import com.gdetra.depeat.models.Order;
import com.gdetra.depeat.models.Restaurant;
import com.gdetra.depeat.ui.activities.adapters.OrderAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckoutActivity extends AppCompatActivity implements OrderAdapter.OnItemDeletedListener {
    TextView restaurantNameTv;
    TextView restaurantDescriptionTv;
    RecyclerView checkoutRv;
    Button payBtn;
    TextView total;
    Order order;
    OrderAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        restaurantNameTv = findViewById(R.id.restaurant_name);
        restaurantDescriptionTv = findViewById(R.id.restaurant_description);
        checkoutRv = findViewById(R.id.checkout_rv);
        payBtn = findViewById(R.id.pay_btn);
        total = findViewById(R.id.total);
        order = new Order(new Restaurant("Panucci", R.drawable.ic_launcher_background, "A little description of Panucci", 3.0F),
                new ArrayList<>(Arrays.asList(
                        new Food("Pizza", 4.0F, 3, R.drawable.ic_launcher_background),
                        new Food("Pasta", 3.0F, 5, R.drawable.ic_launcher_background),
                        new Food("Noodles", 3.0F, 1, R.drawable.ic_launcher_background),
                        new Food("Sushi", 1.0F, 1, R.drawable.ic_launcher_background)
                )));
        restaurantNameTv.setText(order.getRestaurant().getName());
        restaurantDescriptionTv.setText(order.getRestaurant().getDescription());
        adapter = new OrderAdapter(order);
        total.setText(String.valueOf(order.getTotal()) + "€");
        adapter.setOnItemDeletedListener(this);
        checkoutRv.setAdapter(adapter);
        checkoutRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemDeleted(float amount) {
        total.setText(String.valueOf(amount) + "€");
        if(amount <= order.getRestaurant().getMinImport()){
            payBtn.setEnabled(false);
        }
    }
}
