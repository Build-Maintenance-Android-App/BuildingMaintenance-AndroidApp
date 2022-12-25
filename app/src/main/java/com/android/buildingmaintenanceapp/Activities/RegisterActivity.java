package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.buildingmaintenanceapp.R;

import com.android.buildingmaintenanceapp.databinding.ActivityLoginBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityRegister2Binding;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegister2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityRegister2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}