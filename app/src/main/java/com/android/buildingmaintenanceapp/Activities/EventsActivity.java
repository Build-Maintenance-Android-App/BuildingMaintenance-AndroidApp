package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.databinding.ActivityEventsBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityLoginBinding;

public class EventsActivity extends AppCompatActivity {
ActivityEventsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityEventsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
    }
}