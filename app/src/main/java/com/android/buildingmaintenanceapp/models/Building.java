package com.android.buildingmaintenanceapp.models;


import java.util.ArrayList;

public class Building {
    private String buildingId,buildingName,buildingAddress;
    ArrayList<Event>events;

    public Building(String buildingId, String buildingName, String buildingAddress, ArrayList<Event> events) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.buildingAddress = buildingAddress;
        this.events = events;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
