package com.polurival.fandroidvktest.model.attachment.doc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.polurival.fandroidvktest.model.attachment.video.Video;

import io.realm.RealmObject;

/**
 * Created by Polurival
 * on 01.10.2017.
 */

public class Preview extends RealmObject {

    @SerializedName("photo")
    @Expose
    public PhotoPreview photoPreview;

    @SerializedName("video")
    @Expose
    public Video video;

    public PhotoPreview getPhotoPreview() {
        return photoPreview;
    }

    public void setPhotoPreview(PhotoPreview photoPreview) {
        this.photoPreview = photoPreview;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
