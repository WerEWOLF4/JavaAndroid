package com.example.myapplication7;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.TextView;

public class Style1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.style_layout);

        // Finding views by their ID
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);

        // Setting text to the TextViews
        textView1.setText("Android Lollipop");
        textView2.setText("Android Marshmallow");
        textView3.setText("Android Nougat");
    }
}

