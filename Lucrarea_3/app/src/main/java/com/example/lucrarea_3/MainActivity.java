package com.example.lucrarea_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etNewLanguage;
    private ListView lvLanguages, lvStudies;
    private ArrayList<String> languages;
    private ArrayAdapter<String> adapter;
    private ImageView ivSpecializationImage;
    private Map<String, Integer> specializationImages;
    private String selectedStudyLevel = ""; // Variable to store the selected study level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spSpecializations = findViewById(R.id.spSpecializations);
        RadioGroup rgStudyForm = findViewById(R.id.rgStudyForm);
        ivSpecializationImage = findViewById(R.id.ivSpecializationImage);
        etNewLanguage = findViewById(R.id.etNewLanguage);
        lvLanguages = findViewById(R.id.lvLanguages);
        lvStudies = findViewById(R.id.lvStudies);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnShowData = findViewById(R.id.btnShowData);

        // Dropdown for specializations
        String[] specializations = {"TI", "INF", "Design", "Drept"};
        ArrayAdapter<String> spAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, specializations);
        spSpecializations.setAdapter(spAdapter);

        // Map specializations to images
        specializationImages = new HashMap<>();
        specializationImages.put("TI", R.drawable.ti_image);
        specializationImages.put("INF", R.drawable.inf_image);
        specializationImages.put("Design", R.drawable.design_image);
        specializationImages.put("Drept", R.drawable.drept_image);

        // Event for selecting specialization
        spSpecializations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSpecialization = specializations[position];
                ivSpecializationImage.setImageResource(specializationImages.get(selectedSpecialization));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ivSpecializationImage.setImageResource(0);
            }
        });

        // List of foreign languages
        languages = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, languages);
        lvLanguages.setAdapter(adapter);

        // Button to add a foreign language
        findViewById(R.id.btnAddLanguage).setOnClickListener(v -> {
            String newLang = etNewLanguage.getText().toString().trim();
            if (!newLang.isEmpty()) {
                languages.add(newLang);
                adapter.notifyDataSetChanged();
                etNewLanguage.setText("");
            }
        });

        // Button to clear foreign languages
        btnCancel.setOnClickListener(v -> {
            languages.clear();
            adapter.notifyDataSetChanged();
        });

        // List of study levels
        String[] studyLevels = {"Medii", "Superioare", "Speciale", "Doctorant"};
        ArrayAdapter<String> studyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, studyLevels);
        lvStudies.setAdapter(studyAdapter);
        lvStudies.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Event for selecting a study level
        lvStudies.setOnItemClickListener((parent, view, position, id) -> {
            selectedStudyLevel = studyLevels[position];
        });

        // Button to show data
        btnShowData.setOnClickListener(v -> showData());

        // Handle OK button
        btnOk.setOnClickListener(v -> {
            // Perform OK action
            Toast.makeText(this, "OK button clicked", Toast.LENGTH_SHORT).show();
        });

        // Handle button to start DisplayDataActivity
        btnShowData.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DisplayDataActivity.class);
            intent.putExtra("selectedStudyLevel", selectedStudyLevel);
            intent.putStringArrayListExtra("languages", languages);
            startActivity(intent);
        });
    }

    private void showData() {
        StringBuilder data = new StringBuilder();
        data.append("Selected Study Level: ").append(selectedStudyLevel).append("\n");
        data.append("Languages: ").append(languages).append("\n");

        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
    }
}
