package com.polurival.fandroidvktest.model.attachment.video;

import io.realm.RealmObject;

/**
 * Created by Polurival
 * on 01.10.2017.
 */

public class File extends RealmObject {

    public String external;

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }
}
