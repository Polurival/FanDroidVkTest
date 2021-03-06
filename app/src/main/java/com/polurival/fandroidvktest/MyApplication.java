package com.polurival.fandroidvktest;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.polurival.fandroidvktest.di.component.ApplicationComponent;
import com.polurival.fandroidvktest.di.component.DaggerApplicationComponent;
import com.polurival.fandroidvktest.di.module.ApplicationModule;
import com.polurival.fandroidvktest.di.module.ManagerModule;
import com.polurival.fandroidvktest.fcm.MyPreferencesManager;
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

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Glide.with(imageView.getContext()).load(uri).into(imageView);
            }
        });

        //MyPreferencesManager.getInstance().init(this);
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
