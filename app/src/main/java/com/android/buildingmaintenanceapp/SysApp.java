package com.android.buildingmaintenanceapp;

import com.android.buildingmaintenanceapp.models.Event;

import java.util.ArrayList;
import java.util.Collections;

public  class SysApp {
    private static ArrayList<Event>events;
    public static Event event;
    public static void addEvent(Event e){
        events.add(e);
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static void setEvents(ArrayList<Event> events) {
        SysApp.events = events;
    }

    public static void setData(ArrayList<Event> events) {
        SysApp.events = events;
    }
}
