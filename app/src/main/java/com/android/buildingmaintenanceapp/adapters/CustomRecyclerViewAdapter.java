package com.android.buildingmaintenanceapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.models.Event;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.MyViewHolder> {


    private ArrayList<Event>allEvents;
    public CustomRecyclerViewAdapter(ArrayList<Event>allEvents) {
        this.allEvents=allEvents;
    }

    @NonNull
    @Override
    public CustomRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleEventItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(singleEventItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.txtEventTitle.setText(allEvents.get(position).getEventTitle());
        holder.txtEventDate.setText(allEvents.get(position).getEventDate());
        holder.txtEventDesc.setText(allEvents.get(position).getEventDescription());
        holder.txtArea.setText(allEvents.get(position).getFunctionalArea());
        holder.txtCondition.setText(allEvents.get(position).getCondition());
        holder.txtContact.setText(allEvents.get(position).getServiceContactPhone());
    }

    @Override
    public int getItemCount() {
        return allEvents.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtEventTitle,txtEventDate,txtEventDesc,txtArea,txtCondition,
                txtContact;


        public MyViewHolder(@NonNull View singleEvent) {
            super(singleEvent);
            txtEventTitle=singleEvent.findViewById(R.id.txteventTitle);
            txtEventDate=singleEvent.findViewById(R.id.txteventDate);
            txtEventDesc=singleEvent.findViewById(R.id.txteventDescription);
            txtArea=singleEvent.findViewById(R.id.txtFunctionalArea);
            txtCondition=singleEvent.findViewById(R.id.txtCondition);
            txtContact=singleEvent.findViewById(R.id.txtServiceContact);


        }
    }


}
