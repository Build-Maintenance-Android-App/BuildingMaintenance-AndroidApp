package com.android.buildingmaintenanceapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.buildingmaintenanceapp.repositories.UserRepository;


import com.android.buildingmaintenanceapp.responses.UserResponse;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    public UserViewModel(){
        userRepository = new UserRepository();
    }
    public LiveData<UserResponse> getUserHome(){
        return userRepository.getUserHome();
    }
}
