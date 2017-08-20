package com.polurival.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.polurival.fandroidvktest.CurrentUser;
import com.polurival.fandroidvktest.mvp.view.MainView;

/**
 * Created by Polurival
 * on 17.08.2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
        } else {
            getViewState().signedId();
        }
    }
}
