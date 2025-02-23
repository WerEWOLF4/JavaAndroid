package com.example.finalapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<Product> cartItems;
    private DatabaseHelper databaseHelper;

    public CartAdapter(Context context, List<Product> cartItems, DatabaseHelper databaseHelper) {
        this.context = context;
        this.cartItems = cartItems;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = cartItems.get(position);

        holder.titleTextView.setText(product.getTitle());
        holder.priceTextView.setText("Price: $" + product.getPrice());
        holder.quantityTextView.setText(String.valueOf(product.getQuantity()));

        // Increase quantity
        holder.increaseButton.setOnClickListener(v -> {
            int newQuantity = product.getQuantity() + 1;
            product.setQuantity(newQuantity);
            databaseHelper.updateProductQuantity(product.getId(), newQuantity);
            holder.quantityTextView.setText(String.valueOf(newQuantity));
            notifyItemChanged(position);
        });

        // Decrease quantity
        holder.decreaseButton.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                int newQuantity = product.getQuantity() - 1;
                product.setQuantity(newQuantity);
                databaseHelper.updateProductQuantity(product.getId(), newQuantity);
                holder.quantityTextView.setText(String.valueOf(newQuantity));
                notifyItemChanged(position);
            } else {
                new AlertDialog.Builder(context)
                        .setTitle("Remove Item")
                        .setMessage("Do you want to remove this item from the cart?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            databaseHelper.deleteProduct(product.getId());
                            cartItems.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, cartItems.size());
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        // Remove item from cart
        holder.deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Remove Item")
                    .setMessage("Are you sure you want to remove this item?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        databaseHelper.deleteProduct(product.getId());
                        cartItems.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, cartItems.size());
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, priceTextView, quantityTextView;
        ImageButton increaseButton, decreaseButton;
        ImageView deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtCartItemTitle);
            priceTextView = itemView.findViewById(R.id.txtCartItemPrice);
            quantityTextView = itemView.findViewById(R.id.txtCartItemQuantity);
            increaseButton = itemView.findViewById(R.id.btnIncreaseQuantity);
            decreaseButton = itemView.findViewById(R.id.btnDecreaseQuantity);
            deleteButton = itemView.findViewById(R.id.btnDeleteItem);
        }
    }
}
