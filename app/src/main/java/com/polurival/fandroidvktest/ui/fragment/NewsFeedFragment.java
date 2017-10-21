package com.polurival.fandroidvktest.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.mvp.presenter.BaseFeedPresenter;
import com.polurival.fandroidvktest.mvp.presenter.NewsFeedPresenter;
import com.polurival.fandroidvktest.rest.api.WallApi;
import com.polurival.fandroidvktest.ui.activity.CreatePostActivity;

import javax.inject.Inject;

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
    public void onResume() {
        super.onResume();
        getBaseActivity().getFab().setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CreatePostActivity.class);
            startActivityForResult(intent, 0);
        });
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public boolean needFab() {
        return true;
    }
}
