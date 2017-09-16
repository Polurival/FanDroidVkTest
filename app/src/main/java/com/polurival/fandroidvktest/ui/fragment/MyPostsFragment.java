package com.polurival.fandroidvktest.ui.fragment;

import android.os.Bundle;

import com.polurival.fandroidvktest.R;

/**
 * Created by Polurival
 * on 16.09.2017.
 */

public class MyPostsFragment extends NewsFeedFragment {

    public MyPostsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setEnableIdFiltering(true);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_my_posts;
    }
}
