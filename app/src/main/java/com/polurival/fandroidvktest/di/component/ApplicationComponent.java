package com.polurival.fandroidvktest.di.component;

import com.polurival.fandroidvktest.common.manager.NetworkManager;
import com.polurival.fandroidvktest.di.module.ApplicationModule;
import com.polurival.fandroidvktest.di.module.ManagerModule;
import com.polurival.fandroidvktest.di.module.RestModule;
import com.polurival.fandroidvktest.mvp.presenter.MainPresenter;
import com.polurival.fandroidvktest.mvp.presenter.MembersPresenter;
import com.polurival.fandroidvktest.mvp.presenter.NewsFeedPresenter;
import com.polurival.fandroidvktest.ui.activity.BaseActivity;
import com.polurival.fandroidvktest.ui.activity.MainActivity;
import com.polurival.fandroidvktest.ui.fragment.NewsFeedFragment;
import com.polurival.fandroidvktest.ui.holder.NewsItemBodyHolder;
import com.polurival.fandroidvktest.ui.holder.NewsItemFooterHolder;

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

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);

    //presenters
    void inject(MainPresenter presenter);
    void inject(NewsFeedPresenter presenter);
    void inject(MembersPresenter presenter);

    //managers
    void inject(NetworkManager manager);
}
