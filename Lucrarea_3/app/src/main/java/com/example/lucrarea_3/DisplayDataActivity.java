package com.example.lucrarea_3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        TextView tvData = findViewById(R.id.tvData);

        // Retrieve data from intent
        String selectedStudyLevel = getIntent().getStringExtra("selectedStudyLevel");
        ArrayList<String> languages = getIntent().getStringArrayListExtra("languages");

        StringBuilder data = new StringBuilder();
        data.append("Selected Study Level: ").append(selectedStudyLevel).append("\n");
        data.append("Languages: ").append(languages).append("\n");

        tvData.setText(data.toString());
    }
}
