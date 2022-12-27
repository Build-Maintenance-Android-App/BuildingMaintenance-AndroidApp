package com.android.buildingmaintenanceapp.Activities;

import static com.android.buildingmaintenanceapp.URL.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.Endpoint;
import com.android.buildingmaintenanceapp.R;

import com.android.buildingmaintenanceapp.databinding.ActivityLoginBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityRegister2Binding;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

//RESIDENT REGISTER
public class RegisterActivity extends AppCompatActivity {
    ActivityRegister2Binding binding;
    //Input vars
    private String nameVal,passwInputVal,emailVal,buildingId;
    //Volley vars
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityRegister2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        queue = Volley.newRequestQueue(RegisterActivity.this);

        // when you click the register btn
        binding.registerResidentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GET THE INPUTS
                emailVal = binding.editTextResidentRegEmailAddress.getText().toString().trim();
                passwInputVal = binding.editTextResidentRegPassword.getText().toString().trim();
                nameVal = binding.editTextResidentRegName.getText().toString().trim();
                buildingId =binding.editTextResidentRegNumber.getText().toString();

                //VALIDATION
                if(emailVal.isEmpty() || passwInputVal.isEmpty() || nameVal.isEmpty() || buildingId.isEmpty())
                    Toast.makeText(RegisterActivity.this, "Please,fill all the fields", Toast.LENGTH_SHORT).show();
                else
                    registerResident();

            }
        });

        // radio buttons events
        binding.radioButtonLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.radioButtonCreateBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,BuildingRegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    public void registerResident(){
        sendPOSTRequestRegister(BASE_URL+ Endpoint.ENDPOINT_REGISTER_RESIDENT,passwInputVal,emailVal,nameVal,buildingId);

    }
    /* Post data with JSON notation */
    public void sendPOSTRequestRegister(String urlString,String password,String email,String name,String buildingId) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterActivity.this, response,
                        Toast.LENGTH_LONG).show();


                //go to dashboard activity
                Intent intent= new Intent(RegisterActivity.this, Dashboard.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Registered Failed:Invalid email or password or Building Id!",
                        Toast.LENGTH_LONG).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", password);
                params.put("email",email);
                params.put("buildingId",buildingId);
                params.put("name",nameVal);

                return params;
            }
        };
        queue.add(stringRequest);

    }
}