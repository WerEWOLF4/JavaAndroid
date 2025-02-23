package com.example.finalapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    private DatabaseHelper databaseHelper;

    public ProductAdapter(Context context, List<Product> productList, DatabaseHelper databaseHelper) {
        this.context = context;
        this.productList = productList;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_row, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getTitle());
        holder.price.setText("$" + product.getPrice());

        // Load product image from API using Glide
        String imageUrl = product.getImageUrl();
        Glide.with(context)
                .load(imageUrl) // Load image from API
                .placeholder(R.drawable.ic_launcher_background) // Placeholder while loading
                .error(R.drawable.ic_launcher_foreground) // Error image if fail
                .into(holder.productImage);

        // Add to Cart button
        holder.addToCartButton.setOnClickListener(v -> {
            product.setQuantity(product.getQuantity() + 1);
            databaseHelper.addOrUpdateProduct(product);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView productImage;
        Button addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
            addToCartButton = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
