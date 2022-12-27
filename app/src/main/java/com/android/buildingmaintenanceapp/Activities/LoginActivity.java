package com.android.buildingmaintenanceapp.Activities;

import static com.android.volley.toolbox.Volley.newRequestQueue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.Endpoint;
import com.android.buildingmaintenanceapp.MyVolleyUsage;
import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.URL;
import com.android.buildingmaintenanceapp.databinding.ActivityLoginBinding;
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

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private String emailInputVal,passwInputVal;
    //Volley vars
    RequestQueue queue;
    Context context;



    MyVolleyUsage myVolley;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        queue = Volley.newRequestQueue(LoginActivity.this);

        setContentView(view);



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


    // Request String by using Volley
    public void requestForString(String urlString) {
        /*
         * StringRequest parameters are method:POST or GET request, destination
         * url and two listener: Response.Listener and Response.ErrorListener
         *
         * Response.Listener, is called when the response is received and is
         * ready. In this case the onResponse method is called. This method runs
         * in the main thread so we can update, for example, some UI wigtes.
         * Response.ErrorListener: If something goes wrong, the
         * ErrorListener.onErrorResponse is called. In this method
         */
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Result", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Result",
                        "ERROR string request " + error.toString());
            }
        });

        // add request to queue
        queue.add(stringRequest);
    }

    // Image Request by using Volley
    public void requestForBinaryData(String urlString) {
        // To request binary data
        // To download an image from a remote server, ImageRequest can be used
        ImageRequest imageRequest = new ImageRequest(urlString,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                     //   ((LoginActivity)context).setBitmapImage(response);

                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "ERROR",
                                Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });
        queue.add(imageRequest);
    }

    // JSONObject request by using Volley
    public void requestJSONObject(String urlString) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, urlString, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Result: ", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Result",
                        "ERROR JSONObject request" + error.toString());
            }
        });

        // add request to queue
        queue.add(jsonObjectRequest);
    }

    // JSONArray request by using Volley
    public void requestJSOnArray(String urlString) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, urlString, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Result: ", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Result",
                        "ERROR JSONArray request" + error.toString());
            }
        });
        // add request to queue
        queue.add(jsonArrayRequest);

    }

    public void sendGETRequest(String urlString) {
        // To get
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Request", response.toString());
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError arg0) {
                Toast.makeText(context, "ERROR",
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            // Use this to add parameters to request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("num1", "10"); // num1 is the name of the parameter
                // and 10 is the value
                return params;
            }
        };
        ;
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    /* Post data with JSON notation */
    public void sendPOSTRequestLogin(String urlString,String password,String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(LoginActivity.this, response,
                        Toast.LENGTH_LONG).show();


                //go to dashboard activity
              Intent intent= new Intent(LoginActivity.this, Dashboard.class);
                startActivity(intent);
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