package com.polurival.fandroidvktest.model.attachment.doc;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Polurival
 * on 01.10.2017.
 */

public class PhotoPreview extends RealmObject {

    RealmList<Size> sizes;

    public RealmList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(RealmList<Size> sizes) {
        this.sizes = sizes;
    }
}
