package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifth_layout);

        TextView textView = findViewById(R.id.resultTextView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            UserParcelable user = extras.getParcelable(UserParcelable.class.getSimpleName());
            if (user != null) {
                textView.setText("Name: " + user.getName() + "\nCompany: " + user.getCompany() + "\nAge: " + user.getAge());
            }
        }
    }
}
