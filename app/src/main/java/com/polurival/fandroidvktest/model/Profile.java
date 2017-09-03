package com.polurival.fandroidvktest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Polurival
 * on 27.08.2017.
 */

public class Profile implements Owner {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("photo_50")
    @Expose
    public String photo50;

    @SerializedName("photo_100")
    @Expose
    public String photo100;

    @SerializedName("first_name")
    @Expose
    public String firstName;

    @SerializedName("last_name")
    @Expose
    public String lastName;

    @SerializedName("sex")
    @Expose
    public int sex;

    @SerializedName("screen_name")
    @Expose
    public String screenName;

    @SerializedName("online")
    @Expose
    public int online;

    @SerializedName("hidden")
    @Expose
    public int hidden;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getPhoto50() {
        return photo50;
    }

    public void setPhoto50(String photo50) {
        this.photo50 = photo50;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public boolean isGroup() {
        return false;
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getPhoto() {
        return photo100;
    }

    public String getDisplayProfilePhoto() {
        return photo100;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
