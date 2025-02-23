package com.example.finalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private DatabaseHelper databaseHelper;
    private FloatingActionButton miniCartButton;
    private FloatingActionButton btnWaitingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recyclerView);
        miniCartButton = findViewById(R.id.btnMiniCart); // Fixed FloatingActionButton reference
        btnWaitingList = findViewById(R.id.btnWaitingList); // Button for WaitingListActivity

        databaseHelper = new DatabaseHelper(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 items per row

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList, databaseHelper);
        recyclerView.setAdapter(productAdapter);

        fetchProducts(); // Fetch and display products

        // Mini Cart Button Click
        miniCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Waiting List Button Click
        btnWaitingList.setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, WaitingListActivity.class);
            startActivity(intent);
        });
    }

    private void fetchProducts() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Product>> call = apiService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear(); // Clear existing list before adding new data
                    productList.addAll(response.body());
                    productAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ProductListActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "Error fetching products: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
