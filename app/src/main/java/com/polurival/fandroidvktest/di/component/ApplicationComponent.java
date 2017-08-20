package com.polurival.fandroidvktest.di.component;

import com.polurival.fandroidvktest.di.module.ApplicationModule;
import com.polurival.fandroidvktest.di.module.ManagerModule;
import com.polurival.fandroidvktest.di.module.RestModule;
import com.polurival.fandroidvktest.ui.activity.BaseActivity;
import com.polurival.fandroidvktest.ui.activity.MainActivity;
import com.polurival.fandroidvktest.ui.fragment.NewsFeedFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    //activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    //fragments
    void inject(NewsFeedFragment fragment);
}
