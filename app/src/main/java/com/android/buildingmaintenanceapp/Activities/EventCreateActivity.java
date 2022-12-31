package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.Endpoint;
import com.android.buildingmaintenanceapp.SysApp;
import com.android.buildingmaintenanceapp.URL;
import com.android.buildingmaintenanceapp.databinding.ActivityEventCreateBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityRegister2Binding;
import com.android.buildingmaintenanceapp.models.Event;
import com.android.buildingmaintenanceapp.models.User;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class EventCreateActivity extends AppCompatActivity {
    ActivityEventCreateBinding binding;
    User user=null;
    //Volley vars
    RequestQueue queue;
    String eventTitle, functionalArea,condition,eventDescription,serviceNo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityEventCreateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        queue = Volley.newRequestQueue(EventCreateActivity.this);

        setContentView(view);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        user  = (User) b.getParcelable("usr");

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET TEXT FROM INPUTS
                 eventTitle =binding.titleField.getEditText().getText().toString();
                functionalArea=binding.functionField.getEditText().getText().toString();
                condition = binding.conditionField.getEditText().getText().toString();
                eventDescription=binding.descriptionField.getEditableText().toString();
                serviceNo = binding.servicef.getEditableText().toString();
                //VALIDATE
                if(eventTitle.isEmpty() || functionalArea.isEmpty() || condition.isEmpty() || eventDescription.isEmpty()|| serviceNo.isEmpty())
                    Toast.makeText(EventCreateActivity.this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                else
                    createEvent();
            }
        });
    }

    private void createEvent() {
        clearFields();
        sendPOSTRequestCreate(URL.BASE_URL+ Endpoint.ENDPOINT_CREATE_EVENT);

    }

    private void clearFields(){
        binding.titleField.getEditText().setText("");
        binding.functionField.getEditText().setText("");
        binding.conditionField.getEditText().setText("");
        binding.descriptionField2.getEditText().setText("");
        binding.serviceField.getEditText().setText("");


    }

    /* Post data with JSON notation */
    public void sendPOSTRequestCreate(String urlString) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //GET CURRENT TIME
                String date;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    date=dtf.format(now).toString();

                }
                else
                    date="";

                // Add to Arraylist
                Event event= new Event(eventTitle,date,eventDescription,functionalArea,condition,serviceNo);
                //SysApp.addEvent(event);

                Toast.makeText(EventCreateActivity.this,"Event Created Successfully",Toast.LENGTH_SHORT).show();



                    //go to dashboard activity
                    Intent intent = new Intent(EventCreateActivity.this, EventsActivity.class);;
                     Bundle b = new Bundle();
                     b.putParcelable("usr", user);
                      intent.putExtras(b);
                    startActivity(intent);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EventCreateActivity.this, "Creation Failed!",
                        Toast.LENGTH_LONG).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("eventTitle",eventTitle);
                params.put("eventDescription",eventDescription);
                params.put("condition",condition);
                params.put("functionalArea",functionalArea);
                params.put("serviceContactPhone",serviceNo);
                params.put("buildingId",user.getBuildingId());
                params.put("email",user.getEmail());
                return params;
            }
        };
        queue.add(stringRequest);



    }
}