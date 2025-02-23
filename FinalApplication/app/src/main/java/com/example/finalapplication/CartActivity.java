package com.example.finalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView cartRecyclerView;
    Button confirmButton, cancelButton, backButton;
    DatabaseHelper databaseHelper;
    CartAdapter cartAdapter;
    List<Product> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart); // Uses activity_cart.xml

        // Initialize UI components
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        confirmButton = findViewById(R.id.btnConfirm);
        cancelButton = findViewById(R.id.btnCancel);
        backButton = findViewById(R.id.btnBackToProducts); // Back button

        databaseHelper = new DatabaseHelper(this);
        cartItems = databaseHelper.getAllProducts(); // Load cart items

        // Set up RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this, cartItems, databaseHelper);
        cartRecyclerView.setAdapter(cartAdapter);

        // Confirm Order Button
        confirmButton.setOnClickListener(v -> {
            if (!cartItems.isEmpty()) {
                // Save all cart items to the waiting list before clearing the cart
                for (Product product : cartItems) {
                    String currentDateTime = String.valueOf(System.currentTimeMillis()); // Timestamp
                    databaseHelper.addToWaitingList(product.getTitle(), product.getQuantity(), currentDateTime);
                }

                // Clear cart after saving the order
                databaseHelper.clearCart();
                cartItems.clear();
                cartAdapter.notifyDataSetChanged(); // Refresh RecyclerView

                Toast.makeText(this, "Order Confirmed! Added to Waiting List.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cart is already empty!", Toast.LENGTH_SHORT).show();
            }
        });


        // Cancel Order Button (Clears Cart)
        cancelButton.setOnClickListener(v -> {
            if (!cartItems.isEmpty()) {
                databaseHelper.clearCart();
                cartItems.clear();
                cartAdapter.notifyDataSetChanged(); // Refresh RecyclerView
                Toast.makeText(this, "Cart Cleared!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cart is already empty!", Toast.LENGTH_SHORT).show();
            }
        });

        // Back to Product List Button
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, ProductListActivity.class);
            startActivity(intent);
            finish(); // Close CartActivity
        });
    }
}
