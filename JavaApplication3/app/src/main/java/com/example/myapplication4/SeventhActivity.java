package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class SeventhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seventhlayout);
    }

    public void onCheckboxClicked(View view) {
        // Получаем флажок
        CheckBox checkBox = (CheckBox) view;
        // Получаем, отмечен ли данный флажок
        boolean checked = checkBox.isChecked();

        TextView selection1 = findViewById(R.id.selection1);
        TextView selection2 = findViewById(R.id.selection2);

        // Смотрим, какой именно из флажков отмечен
        if (view.getId() == R.id.enabled) {
            if (checked) {
                selection1.setText("Pornit");
                checkBox.setText("Inchis");
            } else {
                selection1.setText("Inchis");
                checkBox.setText("Pornit");
            }
        } else if (view.getId() == R.id.java) {
            if (checked) {
                Toast.makeText(this, "Ati ales Java", Toast.LENGTH_LONG).show();
            }
        } else if (view.getId() == R.id.kotlin) {
            if (checked) {
                Toast.makeText(this, "Ati ales Kotlin", Toast.LENGTH_LONG).show();
            }
        } else {
            selection2.setText("");
        }
    }
}
