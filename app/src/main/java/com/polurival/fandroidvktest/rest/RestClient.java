package com.polurival.fandroidvktest.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

public class RestClient {

    private static final String VK_BASE_URL = "https://api.vk.com/method/";

    private Retrofit mRetrofit;

    public RestClient() {
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(VK_BASE_URL)
                .build();
    }

    public <S> S createService(Class<S> serviceClass) {
        return mRetrofit.create(serviceClass);
    }
}
