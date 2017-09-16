package com.polurival.fandroidvktest.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Polurival
 * on 16.09.2017.
 */

public class Member extends RealmObject {

    public static final String ID = "id";
    public static final String GROUP_ID = "droup_id";
    public static final String PHOTO = "photo";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    @PrimaryKey
    @SerializedName(ID)
    private int id;

    @SerializedName(GROUP_ID)
    private int groupId;

    @SerializedName(PHOTO)
    private String photo;

    @SerializedName(FIRST_NAME)
    private String firstName;

    @SerializedName(LAST_NAME)
    private String lastName;

    public Member() {
    }

    public Member(Profile profile) {
        id = profile.getId();
        photo = profile.getPhoto();
        firstName = profile.getFirstName();
        lastName = profile.getLastName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int group_id) {
        this.groupId = group_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
