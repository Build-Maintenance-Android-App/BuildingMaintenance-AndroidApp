package com.android.buildingmaintenanceapp.Activities;

import static com.android.buildingmaintenanceapp.URL.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.Endpoint;
import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.databinding.ActivityBuildingRegisterBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityDashboardBinding;
import com.android.buildingmaintenanceapp.models.Building;
import com.android.buildingmaintenanceapp.models.Event;
import com.android.buildingmaintenanceapp.models.User;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
        binding.progressBar.setVisibility(View.INVISIBLE);

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
                Intent intent = new Intent(BuildingRegisterActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    public void registerBuilding(){
        sendBuildingStringRequest(BASE_URL + Endpoint.ENDPOINT_REGISTER_BUILDING,passwInputVal,emailInputVal,managerNameVal,buildingAddress,buildingNameVal);
    }


    public void sendBuildingStringRequest(String urlString,String password,String email,String name,String buildingAddress,String buildingName){


        binding.progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                binding.progressBar.setVisibility(View.INVISIBLE);

                Toast.makeText(BuildingRegisterActivity.this,"succes login",Toast.LENGTH_SHORT).show();
                try {
                    // Getting JSONobject
                    JSONObject jsonobject ;
                    jsonobject =new JSONObject(response);
                    JSONObject obj= jsonobject.getJSONObject("user");
                    Log.d("obj:",obj+"");

                    ArrayList<Event>events= new ArrayList<>();


                    Toast.makeText(BuildingRegisterActivity.this, "Bina id:"+obj.getString("buildingId")+" registered successfully",
                            Toast.LENGTH_LONG).show();

                    //go to login activity
                    Intent intent = new Intent(BuildingRegisterActivity.this,LoginActivity.class);
                    startActivity(intent);


                } catch (JSONException e) {

                    Toast.makeText(BuildingRegisterActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(BuildingRegisterActivity.this, "Building Register Failed!",
                        Toast.LENGTH_LONG).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", password);
                params.put("email",email);
                params.put("name",name);
                params.put("buildingName",buildingName);
                params.put("buildingAddress",buildingAddress);

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