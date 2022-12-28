package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.buildingmaintenanceapp.databinding.ActivityEventCreateBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityRegister2Binding;

public class EventCreateActivity extends AppCompatActivity {
    ActivityEventCreateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityEventCreateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
        Intent intent = getIntent();
    }
}