package com.polurival.fandroidvktest.rest.api;

import com.polurival.fandroidvktest.rest.model.response.WallGetResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Call<WallGetResponse> get(@QueryMap Map<String, String> map);
}
