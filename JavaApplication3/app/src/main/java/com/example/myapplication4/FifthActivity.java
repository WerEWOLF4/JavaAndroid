package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fithlayout);
    }

    public  void onClick(View view){
        Toast toast = Toast.makeText(this, "Hello Android!",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0,160);
        toast.show();
    }
}