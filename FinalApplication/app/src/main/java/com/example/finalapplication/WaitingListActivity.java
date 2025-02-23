package com.example.finalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WaitingListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WaitingListAdapter adapter;
    private List<WaitingListItem> waitingListItems;
    private Button btnBackToProducts;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_list);

        // Initialize UI Components
        recyclerView = findViewById(R.id.recyclerViewWaitingList);
        btnBackToProducts = findViewById(R.id.btnBackToProducts);
        emptyTextView = findViewById(R.id.text); // Add this in XML

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch Data from Database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        waitingListItems = databaseHelper.getAllWaitingListItems();

        // Check if waiting list is empty
        if (waitingListItems.isEmpty()) {
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            adapter = new WaitingListAdapter(this, waitingListItems);
            recyclerView.setAdapter(adapter);
        }

        // Handle Back Button Click
        btnBackToProducts.setOnClickListener(v -> {
            // Clear the list in memory (not database)
            waitingListItems.clear();
            adapter.notifyDataSetChanged(); // Refresh RecyclerView

            // Hide RecyclerView and show "empty" message
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

            // Log action
            Log.d("WaitingListActivity", "Waiting list content cleared from UI!");

            // Navigate back to product list
            Intent intent = new Intent(WaitingListActivity.this, ProductListActivity.class);
            startActivity(intent);
            finish(); // Close current activity
        });
    }
}
