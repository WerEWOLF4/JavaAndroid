package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to activity_main.xml
        setContentView(R.layout.activity_main);

        // Get resources from values/strings.xml
        String app_name = getResources().getString(R.string.app_name);

        // Retrieve the existing TextView and update its text
        TextView existingTextView = findViewById(R.id.text_layout);
        existingTextView.setText(R.string.message);

        // Create a new TextView
        TextView newTextView = new TextView(this);
        newTextView.setTextSize(30);
        newTextView.setText(app_name);

        // Create and set LayoutParams with margins for the new TextView
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 80, 0, 0);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        newTextView.setLayoutParams(layoutParams);

        // Add the new TextView to the existing ConstraintLayout
        ConstraintLayout constraintLayout = findViewById(R.id.main);
        constraintLayout.addView(newTextView);

        // Add the second TextView with the welcome message
        String userName = "Nichita";
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String text = getString(R.string.welcome_message, userName, hour, minute);
        TextView textView_resource_header = new TextView(this);
        textView_resource_header.setText(text);
        textView_resource_header.setTextSize(28);

        // Create and set LayoutParams with margins for the second TextView
        ConstraintLayout.LayoutParams layout_resources_params = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layout_resources_params.setMargins(120, 160, 0, 0);
        layout_resources_params.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layout_resources_params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        textView_resource_header.setLayoutParams(layout_resources_params);

        // Add the second TextView to the existing ConstraintLayout
        constraintLayout.addView(textView_resource_header);

        // Retrieve and display the quantity string for roses
        String rose = getResources().getQuantityString(R.plurals.flowers, 21, 21);

        TextView textView_roses = new TextView(this);
        textView_roses.setText(rose);
        textView_roses.setTextSize(26);

        // Create and set LayoutParams with margins for the third TextView
        ConstraintLayout.LayoutParams layout_resources_roses = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layout_resources_roses.setMargins(120, 360, 0, 0);
        layout_resources_roses.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layout_resources_roses.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        textView_roses.setLayoutParams(layout_resources_roses);

        // Add the third TextView to the existing ConstraintLayout
        constraintLayout.addView(textView_roses);


        Resources res = getResources();
        String[] languages = res.getStringArray(R.array.languages);
        String allLangs = "";
        for (String lang : languages) {
            allLangs += lang + " ";
        }
        TextView textView_strings = new TextView(this);
        textView_strings.setText(allLangs);
        textView_strings.setTextSize(28);

// Create and set LayoutParams with margins for textView_strings
        ConstraintLayout.LayoutParams layout_params_strings = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layout_params_strings.setMargins(120, 420, 0, 0);
        layout_params_strings.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layout_params_strings.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        textView_strings.setLayoutParams(layout_params_strings);

// Add textView_strings to the existing ConstraintLayout
        constraintLayout.addView(textView_strings);

        Resources resources = getResources();
        float textSize = resources.getDimension(R.dimen.text_size);
        int hMargin = (int)resources.getDimension(R.dimen.horizontal_margin);
        int vMargin = (int)resources.getDimension(R.dimen.vertical_margin);




        TextView textView_resources_dimens = new TextView(this);
        textView_resources_dimens.setText("Hello Android");
        textView_resources_dimens.setBackgroundColor(0XFFEAEAEA);
        // устанавливаем размер шрифт по ресурсу
        textView_resources_dimens.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        // Create and set LayoutParams with hMargin and vMargin for textView_resources_dimens
        ConstraintLayout.LayoutParams layoutParams_resources_dimens = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams_resources_dimens.setMargins(hMargin, vMargin, hMargin, vMargin);
        textView_resources_dimens.setLayoutParams(layoutParams_resources_dimens);

// Add textView_resources_dimens to the existing ConstraintLayout
        constraintLayout.addView(textView_resources_dimens);


        Resources resources_color = getResources();
        int textColor = resources_color.getColor(R.color.black,  null);
        int backgroundColor = resources_color.getColor(R.color.purple_700,  null);

        // используем ресурсы color
        textView_resource_header.setTextColor(textColor);
        textView_resource_header.setBackgroundColor(backgroundColor);

    }
}
