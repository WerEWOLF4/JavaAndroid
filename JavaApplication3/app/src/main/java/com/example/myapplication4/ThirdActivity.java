package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdlayout);





        Button button = findViewById(R.id.button);
        EditText editText1 = findViewById(R.id.editText);
        TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Prelucrare Butonului
                textView.setText("Bine Ați Venit, " + editText1.getText());
            }
        });


        Button button_fourth = findViewById(R.id.button_fourth);
        button_fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
                startActivity(intent);
            }
        });
    }
}