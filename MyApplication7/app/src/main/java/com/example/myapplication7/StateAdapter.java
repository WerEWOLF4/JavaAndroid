package com.example.myapplication7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class StateAdapter extends ArrayAdapter<State> {

    private Context context;
    private int resource;
    private ArrayList<State> states;

    public StateAdapter(Context context, int resource, ArrayList<State> states) {
        super(context, resource, states);
        this.context = context;
        this.resource = resource;
        this.states = states;
    }

    private static class ViewHolder {
        ImageView flagView;
        TextView nameView;
        TextView capitalView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.flagView = convertView.findViewById(R.id.flag);
            viewHolder.nameView = convertView.findViewById(R.id.name);
            viewHolder.capitalView = convertView.findViewById(R.id.capital);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        State state = states.get(position);
        viewHolder.flagView.setImageResource(state.getFlagResource());
        viewHolder.nameView.setText(state.getName());
        viewHolder.capitalView.setText(state.getCapital());

        return convertView;
    }
}
