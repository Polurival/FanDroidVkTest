package com.polurival.fandroidvktest.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.polurival.fandroidvktest.model.attachment.video.Video;

import java.util.List;

/**
 * Created by Polurival
 * on 01.10.2017.
 */

public class VideosResponse {

    public int count;

    @SerializedName("items")
    @Expose
    public List<Video> items;
}
