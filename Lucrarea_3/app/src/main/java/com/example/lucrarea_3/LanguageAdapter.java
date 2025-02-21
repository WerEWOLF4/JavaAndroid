package com.example.lucrarea_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LanguageAdapter extends ArrayAdapter<String> {

    public LanguageAdapter(Context context, List<String> languages) {
        super(context, 0, languages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        String language = getItem(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(language);

        return convertView;
    }
}
