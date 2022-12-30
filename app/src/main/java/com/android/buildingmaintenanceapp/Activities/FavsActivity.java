package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.SysApp;
import com.android.buildingmaintenanceapp.adapters.CustomRecyclerViewAdapter;
import com.android.buildingmaintenanceapp.databinding.ActivityEventsBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityFavsBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityMainBinding;
import com.android.buildingmaintenanceapp.models.Event;

import java.util.ArrayList;

public class FavsActivity extends AppCompatActivity {
ActivityFavsBinding binding;
    CustomRecyclerViewAdapter adapter;
    DatabaseHelper dbHelper;
    LinearLayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        binding = ActivityFavsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
        Intent intent = getIntent();



        dbHelper = new DatabaseHelper(this);
       SysApp.setEvents((ArrayList< Event>) EventTable.getAllEvents(dbHelper));
       for(int i=0;i<SysApp.getEvents().size()-1;i++)
       {  Log.d("eventfavs",SysApp.getEvents().get(i)+"");
       }

       SysApp.getEvents();
        adapter = new CustomRecyclerViewAdapter(this, SysApp.getEvents());

        binding.recyclerFv.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(FavsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerFv.setLayoutManager(layoutManager);










    }
}