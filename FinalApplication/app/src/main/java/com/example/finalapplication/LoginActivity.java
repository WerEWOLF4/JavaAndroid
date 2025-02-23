package com.example.finalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalapplication.User;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginButton, btnGoToRegister;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        btnGoToRegister = findViewById(R.id.btnGoToRegister);

        // Buton pentru a naviga către RegisterActivity
        btnGoToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();  // Închide LoginActivity pentru a nu reveni la aceasta
        });

        loginButton.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (validateCredentials(user, pass)) {
                // Start a new Activity (e.g., ProductListActivity)
                Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateCredentials(String username, String password) {
        User user = dbHelper.getUserByUsername(username);

        // Verificăm dacă utilizatorul există și dacă parola se potrivește
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }
}
