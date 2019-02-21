package com.gdetra.depeat.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gdetra.depeat.R;
import com.gdetra.depeat.Utils;
import com.gdetra.depeat.models.Food;
import com.gdetra.depeat.models.Order;
import com.gdetra.depeat.models.Restaurant;
import com.gdetra.depeat.models.User;
import com.gdetra.depeat.services.RestController;
import com.gdetra.depeat.ui.activities.adapters.OrderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements OrderAdapter.OnItemDeletedListener, View.OnClickListener, Response.Listener<JSONObject>, Response.ErrorListener {
    TextView restaurantNameTv;
    TextView restaurantDescriptionTv;
    RecyclerView checkoutRv;
    Button payBtn;
    TextView total;
    Order order;
    OrderAdapter adapter;
    RestController controller;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        controller = new RestController(this);
        prefs = getSharedPreferences(Utils.PACKAGE_NAME, Context.MODE_PRIVATE);
        restaurantNameTv = findViewById(R.id.restaurant_name);
        restaurantDescriptionTv = findViewById(R.id.restaurant_description);
        checkoutRv = findViewById(R.id.checkout_rv);
        payBtn = findViewById(R.id.pay_btn);
        total = findViewById(R.id.total);
        order = new Order(new Restaurant("Panucci", "", "A little description of Panucci", 3.0F),
                new ArrayList<>(Arrays.asList(
                        new Food("Pizza", 4.0F, 3, "",""),
                        new Food("Pasta", 3.0F, 5, "",""),
                        new Food("Noodles", 3.0F, 1, "",""),
                        new Food("Sushi", 1.0F, 1, "","")
                )));
        payBtn.setOnClickListener(this);
        restaurantNameTv.setText(order.getRestaurant().getName());
        restaurantDescriptionTv.setText(order.getRestaurant().getAddress());
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.pay_btn){
            try {
                JSONObject params = buildJSONObjectToSend();
                controller.postAddOrderRequest(params,this, this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        try {
            Log.i("aaa", new String(error.networkResponse.data, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.i("ORDER","Order Sent");
    }

    private JSONObject buildJSONObjectToSend() throws JSONException {
        String userId = prefs.getString(User.USER_ID, null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("restaurant", order.getRestaurant().getId());
        jsonObject.put("user", userId);
        jsonObject.put("amount", order.getTotal());
        JSONArray jsonProducts = new JSONArray();
        for(int i = 0; i< order.getProducts().size(); i++){
            JSONObject jsonSingleProduct = new JSONObject();
            Food product = order.getProducts().get(i);
            jsonSingleProduct.put("id", product.getId());
            jsonSingleProduct.put("quantity", product.getQuantity());
            jsonProducts.put(i, jsonSingleProduct);
        }
        jsonObject.put("products", jsonProducts);
        return jsonObject;

    }


}

