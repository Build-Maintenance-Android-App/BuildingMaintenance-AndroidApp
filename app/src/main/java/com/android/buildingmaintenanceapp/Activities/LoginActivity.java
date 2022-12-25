package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private String emailInputVal,passwInputVal;
    private boolean isLoggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);


        emailInputVal = binding.editTextTextEmailAddress.getText().toString();
        passwInputVal = binding.editTextTextPassword.getText().toString();

        // when you press login btn
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoggedIn= loginUser(emailInputVal,passwInputVal);
            }
        });

        if (isLoggedIn)
            Toast.makeText(this, "Succesful logged in", Toast.LENGTH_SHORT).show();


    }

    private boolean loginUser(String emailInputVal, String passwInputVal) {
        return  false;
    }


}