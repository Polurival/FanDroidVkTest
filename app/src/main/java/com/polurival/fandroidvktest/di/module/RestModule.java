package com.polurival.fandroidvktest.di.module;

import com.polurival.fandroidvktest.rest.RestClient;
import com.polurival.fandroidvktest.rest.api.AccountApi;
import com.polurival.fandroidvktest.rest.api.BoardApi;
import com.polurival.fandroidvktest.rest.api.GroupsApi;
import com.polurival.fandroidvktest.rest.api.UsersApi;
import com.polurival.fandroidvktest.rest.api.VideoApi;
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

    @Singleton
    @Provides
    public UsersApi provideUsersApi() {
        return mRestClient.createService(UsersApi.class);
    }

    @Singleton
    @Provides
    public GroupsApi provideGroupsApi() {
        return mRestClient.createService(GroupsApi.class);
    }

    @Singleton
    @Provides
    public BoardApi provideBoardApi() {
        return mRestClient.createService(BoardApi.class);
    }

    @Singleton
    @Provides
    public VideoApi provideVideoApi() { return mRestClient.createService(VideoApi.class); }

    @Singleton
    @Provides
    public AccountApi provideAccountApi() {
        return mRestClient.createService(AccountApi.class);
    }
}
