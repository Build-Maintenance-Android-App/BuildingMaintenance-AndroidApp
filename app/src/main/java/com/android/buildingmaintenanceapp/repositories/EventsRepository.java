package com.android.buildingmaintenanceapp.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.buildingmaintenanceapp.network.ApiClient;
import com.android.buildingmaintenanceapp.network.ApiService;
import com.android.buildingmaintenanceapp.responses.EventsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsRepository {
    private ApiService apiService;
    public  EventsRepository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<EventsResponse> getEventsHome(){
        MutableLiveData<EventsResponse> data = new MutableLiveData<>();
        apiService.getEventsHome().enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(@NonNull Call<EventsResponse> call, @NonNull Response<EventsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<EventsResponse> call,@NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return  data;
    }
}
