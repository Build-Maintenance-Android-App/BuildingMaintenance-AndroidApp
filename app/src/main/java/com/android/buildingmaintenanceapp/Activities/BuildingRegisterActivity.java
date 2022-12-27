package com.android.buildingmaintenanceapp.Activities;

import static com.android.buildingmaintenanceapp.URL.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.Endpoint;
import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.databinding.ActivityBuildingRegisterBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityDashboardBinding;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class BuildingRegisterActivity extends AppCompatActivity {
    ActivityBuildingRegisterBinding binding;
    String emailInputVal,passwInputVal,buildingNameVal,buildingAddress,managerNameVal;
    //Volley vars
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityBuildingRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent receivedIntent = getIntent();
        queue = Volley.newRequestQueue(BuildingRegisterActivity.this);

        // when you click the building register btn
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GET THE INPUTS
                emailInputVal=binding.editTextTextBuildEmailAddress.getText().toString().trim();
                passwInputVal=binding.editTextTextBuildPassword.getText().toString().trim();
                buildingAddress=binding.editTextBuildingAddress.getText().toString().trim();
                buildingNameVal=binding.editTextBuildingName.getText().toString().trim();
                managerNameVal=binding.editTextManagerName.getText().toString().trim();

                //VALIDATION
                if(emailInputVal.isEmpty() || passwInputVal.isEmpty() || buildingAddress.isEmpty() || buildingNameVal.isEmpty() ||managerNameVal.isEmpty())
                    Toast.makeText(BuildingRegisterActivity.this, "Please,fill all field!", Toast.LENGTH_SHORT).show();
                else
                    registerBuilding();
            }
        });


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
                Intent intent = new Intent(BuildingRegisterActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }
    public void registerBuilding(){
        sendPOSTRequestRegisterBuild(BASE_URL + Endpoint.ENDPOINT_REGISTER_BUILDING,passwInputVal,emailInputVal,managerNameVal,buildingAddress,buildingNameVal);
    }
    /* Post data with JSON notation */
    public void sendPOSTRequestRegisterBuild(String urlString,String password,String email,String name,String buildingAddress,String buildingName) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(BuildingRegisterActivity.this, response,
                        Toast.LENGTH_LONG).show();

                // clear inputs
               clearInputs();

                //go to dashboard activity
                Intent intent= new Intent(BuildingRegisterActivity.this, Dashboard.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildingRegisterActivity.this, "Building Register Failed",
                        Toast.LENGTH_LONG).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", password);
                params.put("email",email);
                params.put("buildingName",buildingName);
                params.put("buildingAddress",buildingAddress);
                params.put("name",name);

                return params;
            }
        };
        queue.add(stringRequest);

    }
    public void clearInputs(){
        binding.editTextBuildingAddress.setText("");
        binding.editTextBuildingName.setText("");
        binding.editTextManagerName.setText("");
        binding.editTextTextBuildPassword.setText("");
        binding.editTextTextBuildEmailAddress.setText("");

    }
}