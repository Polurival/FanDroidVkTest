package com.polurival.fandroidvktest;

import android.app.Application;

import com.polurival.fandroidvktest.di.component.ApplicationComponent;
import com.polurival.fandroidvktest.di.component.DaggerApplicationComponent;
import com.polurival.fandroidvktest.di.module.ApplicationModule;
import com.polurival.fandroidvktest.di.module.ManagerModule;
import com.vk.sdk.VKSdk;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Polurival
 * on 17.08.2017.
 */

public class MyApplication extends Application {

    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();

        VKSdk.initialize(this);

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initComponent() {
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .managerModule(new ManagerModule())
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
