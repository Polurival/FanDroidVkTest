package com.polurival.fandroidvktest.rest.api;

import com.polurival.fandroidvktest.rest.model.response.BaseItemResponse;
import com.polurival.fandroidvktest.rest.model.response.Full;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Call<Full<BaseItemResponse>> get(@Query("owner_id") String ownerId,
                                     @Query("access_token") String accessToken,
                                     @Query("extended") Integer extended,
                                     @Query("v") String version);
}
