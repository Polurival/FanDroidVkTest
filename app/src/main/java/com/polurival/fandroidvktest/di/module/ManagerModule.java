package com.polurival.fandroidvktest.di.module;

import com.polurival.fandroidvktest.common.manager.MyFragmentManager;
import com.polurival.fandroidvktest.common.manager.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

@Module
public class ManagerModule {

    @Singleton
    @Provides
    public MyFragmentManager provideMyFragmentManager() {
        return new MyFragmentManager();
    }

    @Singleton
    @Provides
    public NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }
}
