package com.android.buildingmaintenanceapp;

import static java.security.AccessController.getContext;

import java.security.AccessControlContext;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.Activities.Dashboard;
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
   String isLoggedIn = "fail";
   Context loginContext;

    public MyVolleyUsage(Context context){
        this.context =context;
        loginContext=(LoginActivity)context;
        queue = Volley.newRequestQueue(context);
    }

}