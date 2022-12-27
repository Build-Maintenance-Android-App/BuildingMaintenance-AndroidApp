package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.Endpoint;
import com.android.buildingmaintenanceapp.MyVolleyUsage;
import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.URL;
import com.android.buildingmaintenanceapp.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private String emailInputVal,passwInputVal;

    MyVolleyUsage myVolley;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
        myVolley = new MyVolleyUsage(this);


 ;

        // when you press login btn
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailInputVal = binding.editTextTextEmailAddress.getText().toString();
                passwInputVal = binding.editTextTextPassword.getText().toString();
                if(emailInputVal.isEmpty() || passwInputVal.isEmpty())
                    Toast.makeText(LoginActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                else
                    loginUser(emailInputVal,passwInputVal);
            }
        });





    }

    private void loginUser(String emailInputVal, String passwInputVal) {
        Toast.makeText(LoginActivity.this, emailInputVal+" "+passwInputVal, Toast.LENGTH_SHORT).show();
        myVolley.sendPOSTRequestLogin(URL.BASE_URL + Endpoint.ENDPOINT_LOGIN,passwInputVal,emailInputVal);


    }


    // DELETE THIS LATER, IT S NOT NECESSARY
    public void setBitmapImage(Bitmap response) {
    }
}