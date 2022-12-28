package com.android.buildingmaintenanceapp.models;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("isManager")
    private Boolean isManager;
    @SerializedName("buildingId")
    private String buildingId;

    public User(String name, String email, Boolean isManager,String buildingId) {
        this.name = name;
        this.email = email;
        this.isManager = isManager;
        this.buildingId = buildingId;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getManager() {
        return isManager;
    }
    public String getBuildingId(){ return buildingId;}


}
