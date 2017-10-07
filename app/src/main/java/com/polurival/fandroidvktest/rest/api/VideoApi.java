package com.polurival.fandroidvktest.rest.api;

import com.polurival.fandroidvktest.rest.model.response.Full;
import com.polurival.fandroidvktest.rest.model.response.VideosResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Polurival
 * on 01.10.2017.
 */

public interface VideoApi {

    @GET(ApiMethods.VIDEO_GET)
    Observable<Full<VideosResponse>> get(@QueryMap Map<String, String> map);
}
