package com.example.myapplication7;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.Button;

public class Style2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.style_layout_second);

        // Finding the Button view by its ID
        Button helloButton = findViewById(R.id.helloButton);

        // Setting text to the Button
        helloButton.setText("Hello Android");
    }
}

