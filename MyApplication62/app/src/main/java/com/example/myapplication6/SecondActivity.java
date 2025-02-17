package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout; // Import ConstraintLayout
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.CompoundButton;

public class SecondActivity extends AppCompatActivity {

    private EditText numeEditText, prenumeEditText, nastereEditText;

    private TextView numeTextView, prenumeTextView, nastereTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        numeEditText = findViewById(R.id.nume);
        prenumeEditText = findViewById(R.id.prenume);
        nastereEditText = findViewById(R.id.nastere);

        numeTextView = findViewById(R.id.textView2);
        prenumeTextView = findViewById(R.id.textView3);
        nastereTextView = findViewById(R.id.textView4);

        Button okButton = findViewById(R.id.button);
        Button cancelButton = findViewById(R.id.button2);
        Button launchThirdActivityButton = findViewById(R.id.launchThirdActivityButton);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        ConstraintLayout mainLayout = findViewById(R.id.main); // Changed to ConstraintLayout

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeTextView.setText(numeEditText.getText().toString());
                prenumeTextView.setText(prenumeEditText.getText().toString());
                nastereTextView.setText(nastereEditText.getText().toString());
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeEditText.setText("");
                prenumeEditText.setText("");
                nastereEditText.setText("");
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mainLayout.setBackgroundColor(ContextCompat.getColor(SecondActivity.this, R.color.holo_blue_light));
                } else {
                    mainLayout.setBackgroundColor(ContextCompat.getColor(SecondActivity.this, R.color.holo_pink));
                }
            }
        });

        launchThirdActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("name", "John Doe");
                intent.putExtra("company", "Example Corp");
                intent.putExtra("age", 30);
                startActivity(intent);
            }
        });
    }
}
