package com.gdetra.depeat.ui.activities.adapters;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gdetra.depeat.R;
import com.gdetra.depeat.models.Restaurant;
import com.gdetra.depeat.ui.activities.ShopActivity;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private boolean isGridLayoutSelected;

    public RestaurantAdapter(List<Restaurant> restaurantList, boolean isGridLayoutSelected){
        this.restaurantList = restaurantList;
        this.isGridLayoutSelected = isGridLayoutSelected;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(isGridLayoutSelected){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_grid, parent, false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        }
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bind(restaurantList.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurantList != null ? restaurantList.size() : 0;
    }


    class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CardView restaurantCv;
        private ImageView restaurantAvatar;
        private TextView restaurantName;
        private TextView restaurantDescription;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantCv = itemView.findViewById(R.id.restaurant_cv);
            restaurantCv.setOnClickListener(this);
            restaurantAvatar = itemView.findViewById(R.id.avatar_restaurant);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantDescription = itemView.findViewById(R.id.restaurant_description);
        }

        public void bind(Restaurant item){
            restaurantAvatar.setBackgroundResource(item.getImage());
            restaurantName.setText(item.getName());
            restaurantDescription.setText(item.getDescription());
        }

        @Override
        public void onClick(View v) {
            if(restaurantList.get(getAdapterPosition()) != null){
                Intent intent = new Intent(v.getContext(), ShopActivity.class);
                v.getContext().startActivity(intent);
                Log.i("Position", String.valueOf(getAdapterPosition()));
            }
        }
    }
}
