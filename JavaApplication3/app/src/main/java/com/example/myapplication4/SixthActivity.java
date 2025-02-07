package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SixthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sixthlayout);
    }

    public  void onClick(View view) {
        Snackbar snackbar = Snackbar.make(view, "Hello Android", Snackbar.LENGTH_LONG);
        snackbar.setTextColor(0XFF81C784);
        snackbar.setBackgroundTint(0XFF555555);
        snackbar.setActionTextColor(0XFF0277BD);

        snackbar.setAction("Next...", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Next clicked!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        snackbar.show();
    }
}