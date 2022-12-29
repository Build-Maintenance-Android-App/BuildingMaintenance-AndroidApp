package com.android.buildingmaintenanceapp.models;

public class Event {
    String eventTitle, eventDate,eventDescription,functionalArea,condition,serviceContactPhone;

    public Event(String eventTitle, String eventDate, String eventDescription, String functionalArea, String condition, String serviceContactPhone) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.functionalArea = functionalArea;
        this.condition = condition;
        this.serviceContactPhone = serviceContactPhone;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getFunctionalArea() {
        return functionalArea;
    }

    public String getCondition() {
        return condition;
    }

    public String getServiceContactPhone() {
        return serviceContactPhone;
    }


}
