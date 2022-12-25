package com.android.buildingmaintenanceapp.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.buildingmaintenanceapp.network.ApiClient;
import com.android.buildingmaintenanceapp.network.ApiService;
import com.android.buildingmaintenanceapp.responses.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private ApiService apiService;
    public  UserRepository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<UserResponse> getUserHome(){
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        apiService.getUserHome().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call,@NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return  data;
    }
}
