package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private EditText numeEditText, prenumeEditText, nastereEditText;
    private TextView numeTextView, prenumeTextView, nastereTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeEditText = findViewById(R.id.nume);
        prenumeEditText = findViewById(R.id.prenume);
        nastereEditText = findViewById(R.id.nastere);

        numeTextView = findViewById(R.id.textView2);
        prenumeTextView = findViewById(R.id.textView3);
        nastereTextView = findViewById(R.id.textView4);

        Button okButton = findViewById(R.id.button);
        Button cancelButton = findViewById(R.id.button2);

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
    }
}
