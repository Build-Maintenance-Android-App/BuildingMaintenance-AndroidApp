package com.android.buildingmaintenanceapp.models;

import com.google.gson.annotations.SerializedName;

public  class User {

    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("buildingId")
    int buildingId;
    @SerializedName("isManager")
    boolean isManager;
    @SerializedName("password")
    String password;
    @SerializedName("salt")
    String salt;

    public User(String name, String email, int buildingId, boolean isManager, String password, String salt) {
        this.name = name;
        this.email = email;
        this.buildingId = buildingId;
        this.isManager = isManager;
        this.password = password;
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isManager() {
        return isManager;
    }

    public String getPassword() {
        return password;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public String getSalt() {
        return salt;
    }
}
