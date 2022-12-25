package com.android.buildingmaintenanceapp.responses;

import com.android.buildingmaintenanceapp.models.Event;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventsResponse {
    @SerializedName("events")
    private ArrayList<Event> events;

    public ArrayList<Event> getEventsHome() {
        return events;
    }
}
