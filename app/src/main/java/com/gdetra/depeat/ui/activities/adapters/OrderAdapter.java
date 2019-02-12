package com.gdetra.depeat.ui.activities.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gdetra.depeat.R;
import com.gdetra.depeat.models.Food;
import com.gdetra.depeat.models.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Order order;
    private OnItemDeletedListener onItemDeletedListener;

    public OrderAdapter(Order order){
        this.order = order;
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder viewHolder, int i) {
        viewHolder.bind(order);
    }

    @Override
    public int getItemCount() {
        return order.getProducts().size();
    }

    public OnItemDeletedListener getOnItemDeletedListener() {
        return onItemDeletedListener;
    }

    public void setOnItemDeletedListener(OnItemDeletedListener onItemDeletedListener) {
        this.onItemDeletedListener = onItemDeletedListener;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView foodNameTv;
        private TextView foodQuantityTv;
        private TextView quantityTotalTv;
        private Button deleteBtn;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTv = itemView.findViewById(R.id.food_name);
            foodQuantityTv = itemView.findViewById(R.id.food_quantity);
            quantityTotalTv = itemView.findViewById(R.id.quantity_total);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
            deleteBtn.setOnClickListener(this);
        }

        public void bind(Order order){
            int position = getAdapterPosition();
            Food product = order.getProducts().get(position);
            foodNameTv.setText(product.getName());
            foodQuantityTv.setText(String.valueOf(product.getQuantity()));
            quantityTotalTv.setText(String.valueOf(product.getPrice() * product.getQuantity()));
        }

        @Override
        public void onClick(View v) {
            Resources resources = v.getContext().getResources();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

            builder.setTitle(resources.getString(R.string.confirm_title));
            builder.setMessage(resources.getString(R.string.confirm_question));

            builder.setPositiveButton(resources.getString(R.string.positive_text), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    order.getProducts().remove(getAdapterPosition());
                    order.setTotal(order.getProducts());
                    onItemDeletedListener.onItemDeleted(order.getTotal());
                    notifyItemRemoved(getAdapterPosition());
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton(resources.getString(R.string.nagative_text), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public interface OnItemDeletedListener{
        void onItemDeleted(float amount);
    }
}
