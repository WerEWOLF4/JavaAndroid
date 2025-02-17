package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        TextView textView = new TextView(this);
        textView.setTextSize(26);
        textView.setPadding(16, 16, 16, 16);

        // Set margins in dp
        int leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        int topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        int rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        int bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        textView.setLayoutParams(params);

        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            String name = arguments.getString("name");
            String company = arguments.getString("company");
            int age = arguments.getInt("age");
            textView.setText("Name: " + name + "\nCompany: " + company + "\nAge: " + age);
        }

        // Add textView to the layout
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.addView(textView);
    }

    public void onClick(View v) {
        EditText nameText = findViewById(R.id.name);
        EditText companyText = findViewById(R.id.company);
        EditText ageText = findViewById(R.id.age);

        String name = nameText.getText().toString();
        String company = companyText.getText().toString();
        int age = Integer.parseInt(ageText.getText().toString());

        if (v.getId() == R.id.btn) {
            User user = new User(name, company, age);
            Intent intent = new Intent(this, FourthActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else if (v.getId() == R.id.btn2) {
            UserParcelable user = new UserParcelable(name, company, age);
            Intent intent = new Intent(this, FifthActivity.class);
            intent.putExtra(UserParcelable.class.getSimpleName(), user);
            startActivity(intent);
        }
    }

}
