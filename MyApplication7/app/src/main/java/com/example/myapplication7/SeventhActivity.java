package com.example.myapplication7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class SeventhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seventh_layout);

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Картофель", "кг."));
        products.add(new Product("Чай", "шт."));
        products.add(new Product("Яйца", "шт."));
        products.add(new Product("Молоко", "л."));
        products.add(new Product("Макароны", "кг."));
        ListView productList = findViewById(R.id.productList);
        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item_2, products);
        productList.setAdapter(adapter);
    }
}
