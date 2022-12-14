package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.Endpoint;
import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.SysApp;
import com.android.buildingmaintenanceapp.URL;
import com.android.buildingmaintenanceapp.adapters.CustomRecyclerViewAdapter;
import com.android.buildingmaintenanceapp.databinding.ActivityEventsBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityLoginBinding;
import com.android.buildingmaintenanceapp.models.Event;
import com.android.buildingmaintenanceapp.models.User;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventsActivity extends AppCompatActivity {
    ActivityEventsBinding binding;
    CustomRecyclerViewAdapter adapter;
    LinearLayoutManager layoutManager;
    ArrayList<Event>eventItems;
    int jsonArraySize;

    User user =null;
    String exampleTitle;

    //Volley vars
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        binding = ActivityEventsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        user  = (User) b.getParcelable("usr");
        queue = Volley.newRequestQueue(EventsActivity.this);



    // Use received events for the adapter
      getEventsFromAPI( URL.BASE_URL+ Endpoint.ENDPOINT_FETCH_ALL_EVENTS+"/"+user.getBuildingId());



        
    }


   public void getEventsFromAPI(String urlString){

           // To get
       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
               Request.Method.GET, urlString, null,
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       Log.d("Result: ", response.toString());
                       ArrayList<Event>events=new ArrayList<Event>();
                       try {
                         JSONArray jsonArray=  response.getJSONArray("events");

                         jsonArraySize=jsonArray.length();
                           Log.d("arrayLength",jsonArray.length()-1 +"");
                         for (int i=0;i<jsonArray.length()-1;i++)
                         {
                             JSONArray arrayItem =jsonArray.getJSONArray(i);
                             Log.d("arrayItem",arrayItem.toString());
                            JSONObject objectItem=(JSONObject) arrayItem.get(0);
                           String eventTitle= objectItem.getString("eventTitle");
                           exampleTitle=eventTitle;
                           String condition =objectItem.getString("condition");
                           String functionalArea=objectItem.getString("functionalArea");
                           String serviceContact=objectItem.getString("serviceContactPhone");
                           String eventDesc =objectItem.getString("eventDescription");
                           String eventDate =objectItem.getString("date");
                           Event event= new Event(eventTitle,eventDate,eventDesc,functionalArea,condition,serviceContact);
                           Log.d("eventObj",event.getEventTitle());
                           events.add(event);

                           Log.d("eventTitle",eventTitle);
                         }


                         //gelen data bo?? ise


                       } catch (JSONException e) {
                           e.printStackTrace();


                       }


                       if(jsonArraySize==0){
                           binding.pBar.setVisibility(View.GONE);
                           binding.txtNoDatafav.setVisibility(View.VISIBLE);
                       }else{
                           eventItems = new ArrayList<>();
                           eventItems= events;
                           binding.pBar.setVisibility(View.GONE);
                           layoutManager = new LinearLayoutManager(EventsActivity.this);
                           layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


                           adapter=new CustomRecyclerViewAdapter(EventsActivity.this,eventItems);
                           adapter.notifyDataSetChanged();
                           binding.recyclereal.setAdapter(adapter);
                           binding.recyclereal.setLayoutManager(layoutManager);
                           binding.recyclereal.hasFixedSize();


                           //DEBUG
                           for(int i=0;i<eventItems.size();i++){
                               Log.d("received",eventItems.get(i).getEventTitle());

                           }

                           SysApp.setData(eventItems);
                       }






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

       // Add the request to the RequestQueue.
           queue.add(jsonObjectRequest);


   }

  public void printOneEvent(){
        Event event= eventItems.get(0);
        Log.v("D??nen ??lk item",event.toString());
  }
}