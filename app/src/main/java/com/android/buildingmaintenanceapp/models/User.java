package com.android.buildingmaintenanceapp.models;

public class User {
    private String name;
    private String email;
    private Boolean isManager;
    private int BuildingId;

    public User(String name, String email, Boolean isManager, int buildingId) {
        this.name = name;
        this.email = email;
        this.isManager = isManager;
        BuildingId = buildingId;
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

    public int getBuildingId() {
        return BuildingId;
    }
}
