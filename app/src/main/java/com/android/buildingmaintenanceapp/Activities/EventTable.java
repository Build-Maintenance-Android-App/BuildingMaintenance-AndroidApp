package com.android.buildingmaintenanceapp.Activities;

import android.content.ContentValues;
import android.database.Cursor;


import com.android.buildingmaintenanceapp.models.Event;

import java.util.ArrayList;

public class EventTable {
    public static final String TABLE_NAME="events";
    //public static final String FIELD_ID = "_id";
    public static final String FIELD_TITLE = "eventTitle";
    public static final String FIELD_DATE = "eventDate";
    public static final String FIELD_CONDITION = "condition";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_FUNCTIONAL_AREA = "functionalArea";
    public static final String FIELD_DESCRIPTION = "description";


    public static final String CREATE_TABLE_SQL = "CREATE TABLE "+TABLE_NAME+" " +
            "("+FIELD_TITLE+" text, "+
            FIELD_DATE +" text, "+
            FIELD_CONDITION +" text, "+
            FIELD_PHONE+" text, "+
            FIELD_FUNCTIONAL_AREA +" text, " +
            FIELD_DESCRIPTION+" text);";
    public static final String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<Event> getAllEvents (DatabaseHelper db){
        Cursor cursor = db.getAllRecords(TABLE_NAME, null);
        //Cursor cursor  db.getAllRecordsMethod2("SELECT * FROM "+TABLE_NAME, null)
        ArrayList<Event> data=new ArrayList<>();
        Event med = null;
        while (cursor.moveToNext()) {
            String title  = cursor.getString(0);
            String date = cursor.getString(1);

            String condition  = cursor.getString(2);
            String phone = cursor.getString(3);

            String functionalArea  = cursor.getString(4);
            String description = cursor.getString(5);


            med = new Event(title, date, condition, phone, functionalArea, description);
            data.add(med);
        }
        return data;
    }

    public static ArrayList<Event> findEvent(DatabaseHelper db, String key){
        String where = FIELD_TITLE+" like '%"+key+"%'";
        Cursor cursor = db.getSomeRecords(TABLE_NAME,null, where);
        ArrayList<Event> data=new ArrayList<>();
        Event med = null;
        while (cursor.moveToNext()) {
            String title  = cursor.getString(0);
            String date = cursor.getString(1);

            String condition  = cursor.getString(2);
            String phone = cursor.getString(3);

            String functionalArea  = cursor.getString(4);
            String description = cursor.getString(5);

            med = new Event(title, date, condition, phone, functionalArea, description);
            data.add(med);
        }
        return data;
    }

    public static boolean insertEvent(DatabaseHelper db, String title, String date, String condition, String phone, String functionalArea, String description){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_TITLE, title);
        contentValues.put(FIELD_DATE, date);
        contentValues.put(FIELD_CONDITION, condition);
        contentValues.put(FIELD_PHONE, phone);

        contentValues.put(FIELD_FUNCTIONAL_AREA, functionalArea);
        contentValues.put(FIELD_DESCRIPTION, description);

        boolean res = db.insert(TABLE_NAME,contentValues);

        return res;
    }


    public static boolean deleteMed (DatabaseHelper db, int id){
        String where = FIELD_TITLE +" = "+ id;
        boolean res = db.delete(TABLE_NAME,where);
        return res;
    }
}