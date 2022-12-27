package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.databinding.ActivityBuildingRegisterBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityDashboardBinding;

public class BuildingRegisterActivity extends AppCompatActivity {
    ActivityBuildingRegisterBinding binding;
    String emailInputVal,passwInputVal,buildingNameVal,buildingAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityBuildingRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent receivedIntent = getIntent();

        // go to login screen
        binding.radioButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingRegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        // go to create resident screen
        binding.radioButtonCreateResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingRegisterActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}