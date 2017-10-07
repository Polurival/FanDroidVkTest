package com.polurival.fandroidvktest.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.polurival.fandroidvktest.consts.ApiConstants;

import java.util.Map;

/**
 * Created by Polurival
 * on 01.10.2017.
 */

public class VideoGetRequestModel extends BaseRequestModel {

    @SerializedName(ApiConstants.VIDEOS)
    String videos;

    public VideoGetRequestModel() {
    }

    public VideoGetRequestModel(String ownerId, String videoId) {
        videos = ownerId + "_" + videoId;
    }

    public VideoGetRequestModel(int ownerId, int videoId) {
        videos = ownerId + "_" + videoId;
    }

    public VideoGetRequestModel(String videos) {
        this.videos = videos;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(ApiConstants.VIDEOS, getVideos());
    }
}
