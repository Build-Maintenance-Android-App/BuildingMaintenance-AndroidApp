package com.android.buildingmaintenanceapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Building {
    @SerializedName("buildingId")
    int buildingId;
    @SerializedName("buildingName")
    String buildingName;
    @SerializedName("events") // !!!! check here later
    ArrayList<Event> events;
    @SerializedName("buildingAddress")
    String buildingAddress;

    public int getBuildingId() {
        return buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }
}
