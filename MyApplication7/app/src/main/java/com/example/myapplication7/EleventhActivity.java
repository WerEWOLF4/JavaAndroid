package com.example.myapplication7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;

public class EleventhActivity extends AppCompatActivity {

    ArrayList<State> states = new ArrayList<State>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eleventh_layout);

        // начальная инициализация списка
        setInitialData();

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set LayoutManager

        // определяем слушателя нажатия элемента в списке
        StateAdapterRecycle.OnStateClickListener stateClickListener = new StateAdapterRecycle.OnStateClickListener() {
            @Override
            public void onStateClick(State state, int position) {
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + state.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        // создаем адаптер
        StateAdapterRecycle adapter = new StateAdapterRecycle(this, states, stateClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {
        states.add(new State("Бразилия", "Бразилиа", R.drawable.brazil));
        states.add(new State("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
        states.add(new State("Колумбия", "Богота", R.drawable.columbia));
        states.add(new State("Уругвай", "Монтевидео", R.drawable.urguai));
        states.add(new State("Чили", "Сантьяго", R.drawable.chile));
    }
}
