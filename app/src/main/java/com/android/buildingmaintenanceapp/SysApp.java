package com.android.buildingmaintenanceapp;

import com.android.buildingmaintenanceapp.models.Event;

import java.util.ArrayList;
import java.util.Collections;

public class SysApp {
    private static ArrayList<Event>events=new ArrayList<>();
    public static void addEvent(Event e){
        events.add(e);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
