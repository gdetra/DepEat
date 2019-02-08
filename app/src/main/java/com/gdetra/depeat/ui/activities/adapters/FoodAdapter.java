package com.gdetra.depeat.ui.activities.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdetra.depeat.R;
import com.gdetra.depeat.models.Food;


import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<Food> listFood;
    private OnQuantityChangedListener onQuantityChangedListener;

    public FoodAdapter(List<Food> listFood){
        this.listFood = listFood;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.bind(listFood.get(position));
    }

    @Override
    public int getItemCount() {
        return listFood != null ? listFood.size() : 0;
    }

    public OnQuantityChangedListener getOnQuantityChangedListener() {
        return onQuantityChangedListener;
    }

    public void setOnQuantityChangedListener(OnQuantityChangedListener onQuantityChangedListener) {
        this.onQuantityChangedListener = onQuantityChangedListener;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView foodIv;
        private TextView foodTv;
        private TextView foodPrice;
        private Button plusBtn;
        private Button minusBtn;
        private TextView quantityTv;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodIv = itemView.findViewById(R.id.food_iv);
            foodTv = itemView.findViewById(R.id.food_name);
            foodPrice = itemView.findViewById(R.id.food_price);
            plusBtn = itemView.findViewById(R.id.plus_btn);
            minusBtn = itemView.findViewById(R.id.minus_btn);
            quantityTv = itemView.findViewById(R.id.quantity_tv);

            plusBtn.setOnClickListener(this);
            minusBtn.setOnClickListener(this);


            Log.i("adapter", String.valueOf(getAdapterPosition()));

        }

        public void bind(Food item){
            foodIv.setBackgroundResource(R.drawable.ic_launcher_background);
            foodTv.setText(item.getName());
            foodPrice.setText(String.valueOf(item.getPrice()));
            quantityTv.setText(String.valueOf(item.getQuantity()));
        }

        @Override
        public void onClick(View v) {
            Food product = listFood.get(getAdapterPosition());
            if(v.getId() == R.id.plus_btn){

                product.increaseQuantity();
                onQuantityChangedListener.onChange(product.getPrice());

            }else if(v.getId() == R.id.minus_btn){

                if (product.getQuantity() > 0) {
                    product.decreaseQuantity();
                    onQuantityChangedListener.onChange(product.getPrice() * -1);
                }
            }

            notifyItemChanged(getAdapterPosition());
        }

    }

    public interface OnQuantityChangedListener{
        void onChange(double price);
    }
}
