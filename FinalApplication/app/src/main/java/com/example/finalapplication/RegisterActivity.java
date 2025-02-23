package com.example.finalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnRegister, btnGoToLogin;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Initializare obiecte
        dbHelper = new DatabaseHelper(this);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnGoToLogin = findViewById(R.id.btnGoToLogin);

        // Buton pentru a înregistra utilizatorul
        btnRegister.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            // Validare câmpuri
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Vă rugăm să completați toate câmpurile!", Toast.LENGTH_SHORT).show();
            } else {
                // Creare obiect User
                User newUser = new User(0, username, password);

                // Adăugare utilizator în baza de date
                long userId = dbHelper.addUser(newUser);

                if (userId != -1) {
                    Toast.makeText(RegisterActivity.this, "Utilizator înregistrat cu succes!", Toast.LENGTH_SHORT).show();
                    Log.d("RegisterActivity", "Username: " + username);
                    Log.d("RegisterActivity", "Password: " + password);
                    Log.d("RegisterActivity", "Inserting user into database");

                    finish(); // Întoarcere la activitatea precedentă (de exemplu LoginActivity)
                } else {
                    Toast.makeText(RegisterActivity.this, "Eroare la înregistrare. Încercați din nou.", Toast.LENGTH_SHORT).show();
                }
            }
        });



        // Buton pentru a naviga la LoginActivity
        btnGoToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();  // Închide RegisterActivity pentru a nu reveni la aceasta
        });
    }
}

