package com.example.myapplicationfragments1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentSendDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, BlankFragment.class, null)
                    .commit();
        }
    }

    @Override
    public void onSendData(String selectedItem) {
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailFragment);
        if (fragment != null) {
            fragment.setSelectedItem(selectedItem);
        }
    }
}
