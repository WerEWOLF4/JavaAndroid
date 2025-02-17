package com.example.myapplication7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

public class NineActivity extends AppCompatActivity {

    String[] cities = {"Moscova", "Samara", "Volgoda", "Volgograd", "Saratov", "Voronej"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nine_layout);

        // Получаем ссылку на элемент AutoCompleteTextView в разметке
        MultiAutoCompleteTextView autoCompleteTextView = findViewById(R.id.autocomplete);

        // Создаем адаптер для автозаполнения элемента MultiAutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        autoCompleteTextView.setAdapter(adapter);

        // установка запятой в качестве разделителя
        autoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
