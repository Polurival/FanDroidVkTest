package com.polurival.fandroidvktest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.polurival.fandroidvktest.model.Profile;
import com.polurival.fandroidvktest.ui.fragment.BaseFragment;

/**
 * Created by Polurival
 * on 17.08.2017.
 */

public interface MainView extends MvpView {

    void startSignIn();

    void signedId();

    void showCurrentUser(Profile profile);

    void showFragmentFromDrawer(BaseFragment baseFragment);

    void startActivityFromDrawer(Class<?> act);
}
