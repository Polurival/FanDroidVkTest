package com.polurival.fandroidvktest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.utils.VkListHelper;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.model.view.NewsItemBodyViewModel;
import com.polurival.fandroidvktest.model.view.NewsItemFooterViewModel;
import com.polurival.fandroidvktest.model.view.NewsItemHeaderViewModel;
import com.polurival.fandroidvktest.mvp.presenter.BaseFeedPresenter;
import com.polurival.fandroidvktest.mvp.presenter.NewsFeedPresenter;
import com.polurival.fandroidvktest.rest.api.WallApi;
import com.polurival.fandroidvktest.rest.model.request.WallGetRequestModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi mWallApi;

    @InjectPresenter
    NewsFeedPresenter mPresenter;

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }
}
