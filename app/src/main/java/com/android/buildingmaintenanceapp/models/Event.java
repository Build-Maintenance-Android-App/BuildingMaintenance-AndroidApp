package com.android.buildingmaintenanceapp.models;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("eventTitle")
    String eventTitle;
    @SerializedName("eventDescription")
    String eventDescription;
    @SerializedName("functionalArea")
    String functionalArea;
    @SerializedName("condition")
    String condition;
    @SerializedName("serviceContactPhone")
    String serviceContactPhone;

}
