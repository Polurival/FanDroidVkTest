package com.polurival.fandroidvktest.rest.api;

import com.polurival.fandroidvktest.rest.model.response.Full;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Polurival
 * on 17.10.2017.
 */

public interface AccountApi {

    @GET(ApiMethods.ACCOUNT_REGISTER_DEVICE)
    Observable<Full<Integer>> registerDevice(@QueryMap Map<String, String> map);
}
