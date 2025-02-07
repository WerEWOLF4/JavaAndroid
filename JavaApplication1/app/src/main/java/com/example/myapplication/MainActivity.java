package com.example.myapplication;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         Crearea constructorului textview
        TextView textView = new TextView(this);
//        Incadrarea Textului în TextView
        textView.setText("Hello Metanit.com!");
//        Înscrierea textului
        textView.setTextSize(28);
//        Încadrarea interfesului vizual pentru activity
//        setContentView((textView)); Remove the dublicate It crushes the app

//        Crearea obiectului constraintLayout
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

//        Înscrierea parametrilor de mărimea și amplasarea elementelor
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

//       Îndreptarea pe partea stîngă a ecranului
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;

//       Îndreptare pe granița de sus a lui ConstraintLayout
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

//      Înscriem parametrii pentru textView
        textView.setLayoutParams(layoutParams);

//      Înscriem TextView în ConstraintLayout
        constraintLayout.addView(textView);

//      În calitate de rădâcină
        setContentView(constraintLayout);
    }
}