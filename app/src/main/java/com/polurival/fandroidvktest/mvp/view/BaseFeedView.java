package com.polurival.fandroidvktest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.polurival.fandroidvktest.model.view.BaseViewModel;

import java.util.List;

/**
 * Created by Polurival
 * on 10.09.2017.
 */

public interface BaseFeedView extends MvpView {

    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    void showError(String message);

    void setItems(List<BaseViewModel> items);

    void addItems(List<BaseViewModel> items);

}
