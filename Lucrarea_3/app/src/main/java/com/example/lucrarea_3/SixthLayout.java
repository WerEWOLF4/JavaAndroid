package com.example.lucrarea_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;


public class SixthLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sixth_layout);

        WebView browser=findViewById(R.id.webBrowser);
        browser.loadUrl("https://www.youtube.com/");
    }
}