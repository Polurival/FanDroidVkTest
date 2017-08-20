package com.polurival.fandroidvktest.di.module;

import com.polurival.fandroidvktest.rest.RestClient;
import com.polurival.fandroidvktest.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

@Module
public class RestModule {

    private RestClient mRestClient;

    public RestModule() {
        mRestClient = new RestClient();
    }

    @Singleton
    @Provides
    public RestClient provideRestClient() {
        return mRestClient;
    }

    @Singleton
    @Provides
    public WallApi provideWallApi() {
        return mRestClient.createService(WallApi.class);
    }
}
