package com.example.myapplication;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> dataList;

    public MyAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ViewHolder(view);
        } catch (Exception e) {
            Log.e("MyAdapter", "Error inflating view: " + e.getMessage());
            // fallback to a simple empty view to avoid crash
            View emptyView = new View(parent.getContext());
            return new ViewHolder(emptyView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            String item = dataList.get(position);
            holder.tvItem.setText(item);
        } catch (IndexOutOfBoundsException e) {
            Log.e("MyAdapter", "Invalid index: " + e.getMessage());
            holder.tvItem.setText("Invalid item");
        } catch (Exception e) {
            Log.e("MyAdapter", "Error binding data: " + e.getMessage());
            holder.tvItem.setText("Error");
        }
    }

    @Override
    public int getItemCount() {
        return (dataList == null) ? 0 : dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
        }
    }
}