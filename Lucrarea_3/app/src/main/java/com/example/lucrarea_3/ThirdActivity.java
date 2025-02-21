package com.example.lucrarea_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private ProgressBar indicatorBar;
    private TextView statusView;
    private Button btnFetch;
    private ProgressViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        indicatorBar = findViewById(R.id.indicator);
        statusView = findViewById(R.id.statusView);
        btnFetch = findViewById(R.id.progressBtn);

        viewModel = new ViewModelProvider(this).get(ProgressViewModel.class);

        viewModel.getProgress().observe(this, progress -> {
            indicatorBar.setProgress(progress);
            statusView.setText("Status: " + progress);
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.startTask();
            }
        });
    }
}