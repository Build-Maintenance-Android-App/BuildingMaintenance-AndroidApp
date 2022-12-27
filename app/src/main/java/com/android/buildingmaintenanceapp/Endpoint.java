package com.android.buildingmaintenanceapp;

public interface Endpoint {
    public static final String ENDPOINT_LOGIN = "/loginUser";
    public static final String ENDPOINT_REGISTER_RESIDENT="/registerUser";
    public static final String ENDPOINT_REGISTER_BUILDING="/api/registerBuilding";
    public static final String ENDPOINT_CREATE_EVENT="/createEvent";  // createEvent/:id
    public  static  final String ENDPOINT_DELETE_EVENT="/deleteEvent" ; //deleteEvent/:id
    public static final String ENDPOINT_FETCH_ALL_EVENTS="/fetchEvents"; // fetchEvents/:buildingId

}
