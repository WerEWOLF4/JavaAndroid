package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_layout);

        TextView textView = findViewById(R.id.resultTextView);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user"); // Retrieve the user object

        if (user != null) {
            textView.setText("Name: " + user.getName() + "\nCompany: " + user.getCompany() + "\nAge: " + user.getAge());
        }


    }
}
