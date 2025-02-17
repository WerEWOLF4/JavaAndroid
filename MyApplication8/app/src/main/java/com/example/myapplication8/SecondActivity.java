package com.example.myapplication8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        TextView headerView = findViewById(R.id.selectedMenuItem);

        if (id == R.id.action_settings) {
            headerView.setText("Настройки");
            return true;
        } else if (id == R.id.open_settings) {
            headerView.setText("Открыть");
            return true;
        } else if (id == R.id.save_settings) {
            headerView.setText("Сохранить");
            return true;
        } else if (id == R.id.action_move) {
            headerView.setText("Переход");
            return true;
        } else if (id == R.id.forward) {
            headerView.setText("Вперед");
            return true;
        } else if (id == R.id.back) {
            headerView.setText("Назад");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
