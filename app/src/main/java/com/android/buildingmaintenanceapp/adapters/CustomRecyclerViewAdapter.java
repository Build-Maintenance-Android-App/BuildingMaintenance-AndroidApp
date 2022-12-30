package com.android.buildingmaintenanceapp.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.buildingmaintenanceapp.Activities.DatabaseHelper;
import com.android.buildingmaintenanceapp.Activities.EventTable;
import com.android.buildingmaintenanceapp.Activities.MainActivity;
import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.SysApp;
import com.android.buildingmaintenanceapp.models.Event;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.MyViewHolder> {
    DatabaseHelper dbHelper;
    Context context;

    private ArrayList<Event>allEvents;
    public CustomRecyclerViewAdapter(Context context,ArrayList<Event>allEvents) {
        this.allEvents=allEvents;
        this.context=context;
        dbHelper = new DatabaseHelper(context);
    }


    @NonNull
    @Override
    public CustomRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleEventItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(singleEventItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewAdapter.MyViewHolder holder, int position) {
        final Event event = SysApp.getEvents().get(position);
        holder.txtEventTitle.setText(event.getEventTitle());
        holder.txtEventDate.setText(event.getEventDate());
        holder.txtEventDesc.setText(event.getEventDescription());
        holder.txtArea.setText(event.getFunctionalArea());
        holder.txtCondition.setText(event.getCondition());
        holder.txtContact.setText(event.getServiceContactPhone());

        holder.btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                boolean resinser = EventTable.insertEvent(dbHelper,event.getEventTitle(), event.getEventDate(), event.getCondition(), event.getServiceContactPhone(), event.getFunctionalArea() ,event.getEventDescription());
                Log.d("insert",resinser+"");
            }
        });


    }

    @Override
    public int getItemCount() {
        return allEvents.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtEventTitle,txtEventDate,txtEventDesc,txtArea,txtCondition,
                txtContact;
        ImageView btnfav,btndelete;


        public MyViewHolder(@NonNull View singleEvent) {
            super(singleEvent);
            txtEventTitle=singleEvent.findViewById(R.id.txteventTitle);
            txtEventDate=singleEvent.findViewById(R.id.txteventDate);
            txtEventDesc=singleEvent.findViewById(R.id.txteventDescription);
            txtArea=singleEvent.findViewById(R.id.txtFunctionalArea);
            txtCondition=singleEvent.findViewById(R.id.txtCondition);
            txtContact=singleEvent.findViewById(R.id.txtServiceContact);
            btnfav =singleEvent.findViewById(R.id.btnfav);
            btndelete=singleEvent.findViewById(R.id.btnImageDelete);



        }
    }


}
