package com.example.myapplication7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;

public class FifthActivity extends AppCompatActivity {

    ArrayList<String> users = new ArrayList<>();
    ArrayList<String> selectedUsers = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifth_layout);

        // добавляем начальные элементы
        Collections.addAll(users, "Tom", "Bob", "Sam", "Alice");
        // получаем элемент ListView
        usersList = findViewById(R.id.usersList);
        // создаем адаптер
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, users);
        // устанавливаем для списка адаптер
        usersList.setAdapter(adapter);
        usersList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // обработка установки и снятия отметки в списке
        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String user = adapter.getItem(position);
                if (usersList.isItemChecked(position)) {
                    selectedUsers.add(user);
                    Log.d("SelectedUsers", "Added: " + user);
                } else {
                    selectedUsers.remove(user);
                    Log.d("SelectedUsers", "Removed: " + user);
                }

                // Debug information
                Log.d("SelectedUsers", selectedUsers.toString());
            }
        });
    }

    public void add(View view) {
        EditText userName = findViewById(R.id.userName);
        String user = userName.getText().toString();
        if (!user.isEmpty()) {
            adapter.add(user);
            userName.setText("");
            adapter.notifyDataSetChanged();
        }
    }

    public void remove(View view) {
        // Debug information
        Log.d("BeforeRemoval", "Selected users before removal: " + selectedUsers.toString());
        Log.d("BeforeRemoval", "Users list before removal: " + users.toString());

        // получаем и удаляем выделенные элементы
        for (int i = 0; i < selectedUsers.size(); i++) {
            adapter.remove(selectedUsers.get(i));
        }
        // снимаем все ранее установленные отметки
        usersList.clearChoices();
        // очищаем массив выбраных объектов
        selectedUsers.clear();

        // Debug information
        Log.d("AfterRemoval", "Selected users after removal: " + selectedUsers.toString());
        Log.d("AfterRemoval", "Users list after removal: " + users.toString());

        adapter.notifyDataSetChanged();
    }
}
