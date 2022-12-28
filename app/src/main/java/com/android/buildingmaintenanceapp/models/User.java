package com.android.buildingmaintenanceapp.models;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User  implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("isManager")
    private Boolean isManager;
    @SerializedName("buildingId")
    private String buildingId;

    public User(String name, String email, Boolean isManager,String buildingId) {
        this.name = name;
        this.email = email;
        this.isManager = isManager;
        this.buildingId = buildingId;

    }
    protected User(Parcel in) {
        name = in.readString();
        email= in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isManager = in.readBoolean();
        }
        buildingId=in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }

    };

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getManager() {
        return isManager;
    }
    public String getBuildingId(){ return buildingId;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(email);
        dest.writeByte((byte) (isManager == null ? 0 : isManager ? 1 : 2));
        dest.writeString(buildingId);
    }
}
