package com.polurival.fandroidvktest.rest.model.response;

import com.polurival.fandroidvktest.model.Group;
import com.polurival.fandroidvktest.model.Owner;
import com.polurival.fandroidvktest.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Polurival
 * on 27.08.2017.
 */

public class ItemWithSendersResponse<T> extends BaseItemResponse<T> {

    private List<Profile> profiles = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();

    private List<Profile> getProfiles() {
        return profiles;
    }

    private List<Group> getGroups() {
        return groups;
    }

    private List<Owner> getAllSenders() {
        List<Owner> all = new ArrayList<>();
        all.addAll(getProfiles());
        all.addAll(getGroups());
        return all;
    }

    public Owner getSender(int id) {
        for (Owner owner : getAllSenders()) {
            if (owner.getId() == Math.abs(id)) {
                return owner;
            }
        }
        return null;
    }
}
