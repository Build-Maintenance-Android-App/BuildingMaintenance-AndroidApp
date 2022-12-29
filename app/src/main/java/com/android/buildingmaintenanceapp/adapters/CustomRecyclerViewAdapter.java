package com.android.buildingmaintenanceapp.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.buildingmaintenanceapp.models.Event;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder >{
    Context context;
    private ArrayList<Event> recyclerItemValues;

    public CustomRecyclerViewAdapter(Context context, ArrayList<Event> recyclerItemValues) {
        this.context = context;
        this.recyclerItemValues = recyclerItemValues;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
