package com.android.buildingmaintenanceapp.network;

import com.android.buildingmaintenanceapp.responses.EventsResponse;
import com.android.buildingmaintenanceapp.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
   @POST("registerUser")
    Call<UserResponse>getUserHome();
    @GET("events")
    Call<EventsResponse>getEventsHome();


}
