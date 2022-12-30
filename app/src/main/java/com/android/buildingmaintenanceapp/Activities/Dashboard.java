package com.android.buildingmaintenanceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.buildingmaintenanceapp.R;
import com.android.buildingmaintenanceapp.databinding.ActivityDashboardBinding;
import com.android.buildingmaintenanceapp.databinding.ActivityLoginBinding;
import com.android.buildingmaintenanceapp.models.User;

public class Dashboard extends AppCompatActivity {
    ActivityDashboardBinding binding;
    User user=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.managerIcon.setVisibility(View.INVISIBLE);
        binding.residentIcon.setVisibility(View.INVISIBLE);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();
         user  = (User) b.getParcelable("usr");
         if(user.getManager())
             binding.managerIcon.setVisibility(View.VISIBLE);
         else
             binding.residentIcon.setVisibility(View.VISIBLE);

        binding.buildingIdTv.setText("Building Id: "+user.getBuildingId());
        Log.d("usr:",user+"");
       binding.welcome.setText("Welcome Dear "+user.getName());

        binding.btnLogOut.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    Toast.makeText(Dashboard.this, "LOG OUT", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Dashboard.this,LoginActivity.class);
                    startActivity(intent);

                    return super.onDoubleTap(e);
                }
            });
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });








       binding.createEventIconView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //go to event create activity
               Intent intent = new Intent(Dashboard.this, EventCreateActivity.class);
               Bundle b = new Bundle();
               b.putParcelable("usr", user);
               intent.putExtras(b);
               startActivity(intent);


           }
       });



        binding.manageEventIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, EventsActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("usr", user);
                intent.putExtras(b);
                startActivity(intent);
            }
        });




    binding.favHome.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Dashboard.this, FavsActivity.class);
            startActivity(intent);
        }
    });


    }
}