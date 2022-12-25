package com.android.buildingmaintenanceapp.responses;

import com.android.buildingmaintenanceapp.models.Event;
import com.android.buildingmaintenanceapp.models.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponse {
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }
}
