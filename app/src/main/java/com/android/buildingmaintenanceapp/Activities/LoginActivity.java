package com.android.buildingmaintenanceapp.Activities;

import static com.android.volley.toolbox.Volley.newRequestQueue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.buildingmaintenanceapp.Endpoint;
import com.android.buildingmaintenanceapp.URL;
import com.android.buildingmaintenanceapp.databinding.ActivityLoginBinding;
import com.android.buildingmaintenanceapp.models.User;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;


import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private String emailInputVal,passwInputVal;
    //Volley vars
    RequestQueue queue;
    Context context;

    LoaderManager loaderManager=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        queue = Volley.newRequestQueue(LoginActivity.this);

        setContentView(view);

        Intent receivedIntent = getIntent();

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

        binding.radioButtonResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        binding.radioButtonBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,BuildingRegisterActivity.class);
                startActivity(intent);
            }
        });





    }

    private void loginUser(String emailInputVal, String passwInputVal) {
        Toast.makeText(LoginActivity.this, emailInputVal+" "+passwInputVal, Toast.LENGTH_SHORT).show();
       sendPOSTRequestLogin(URL.BASE_URL + Endpoint.ENDPOINT_LOGIN,passwInputVal,emailInputVal);
        Toast.makeText(LoginActivity.this, "change activity started", Toast.LENGTH_SHORT).show();


    }




    /* Post data with JSON notation */
    public void sendPOSTRequestLogin(String urlString,String password,String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    Toast.makeText(LoginActivity.this,"succes login",Toast.LENGTH_SHORT).show();
                try {
                    // Getting JSONobject
                    JSONObject jsonobject ;
                    jsonobject =new JSONObject(response);
                    JSONObject obj= jsonobject.getJSONObject("user");
                    Log.d("obj:",obj+"");


                  User usr= new User(obj.getString("name"),obj.getString("email"),obj.getBoolean("isManager"),obj.getString("buildingId"));

                    Toast.makeText(LoginActivity.this, "Bina id:"+usr.getBuildingId(),
                            Toast.LENGTH_LONG).show();

                    //go to dashboard activity
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    Bundle b = new Bundle();
                    b.putParcelable("usr", usr);
                    intent.putExtras(b);
                    startActivity(intent);


                } catch (JSONException e) {
                    Toast.makeText(LoginActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Login Failed:Invalid email or password!",
                        Toast.LENGTH_LONG).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", password);
                params.put("email",email);
                return params;
            }
        };
        queue.add(stringRequest);



    }
}