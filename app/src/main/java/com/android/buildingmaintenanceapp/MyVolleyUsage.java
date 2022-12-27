package com.android.buildingmaintenanceapp;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.Activities.LoginActivity;
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


// Add the following to dependencies to be able to use Volley library
/*
implementation 'com.android.volley:volley:1.2.1'
+
to the manifest file for the application add   android:usesCleartextTraffic="true"

or create network_security_config.xml under res/xml/ then
to the manifest add android:networkSecurityConfig="@xml/network_security_config"
 */

public class MyVolleyUsage {
    RequestQueue queue;
    Context context;

    public MyVolleyUsage(Context context){
        this.context =context;
        queue = Volley.newRequestQueue(context);
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
                        ((LoginActivity)context).setBitmapImage(response);

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
                Toast.makeText(context, response,
                        Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(),
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