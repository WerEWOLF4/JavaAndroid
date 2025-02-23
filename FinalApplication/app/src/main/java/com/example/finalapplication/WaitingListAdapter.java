package com.example.finalapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WaitingListAdapter extends RecyclerView.Adapter<WaitingListAdapter.ViewHolder> {
    private Context context;
    private List<WaitingListItem> waitingList;

    public WaitingListAdapter(Context context, List<WaitingListItem> waitingList) {
        this.context = context;
        this.waitingList = waitingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_waiting_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WaitingListItem item = waitingList.get(position);
        holder.title.setText(item.getTitle());
        holder.quantity.setText("Quantity: " + item.getQuantity());
        holder.dateTime.setText("Confirmed: " + item.getConfirmationDate());
    }

    @Override
    public int getItemCount() {
        return waitingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, quantity, dateTime;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTitle);
            quantity = itemView.findViewById(R.id.txtQuantity);
            dateTime = itemView.findViewById(R.id.txtDateTime);
        }
    }
}
