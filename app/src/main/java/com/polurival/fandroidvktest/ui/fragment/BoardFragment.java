package com.polurival.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.mvp.presenter.BaseFeedPresenter;
import com.polurival.fandroidvktest.mvp.presenter.BoardPresenter;

import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 26.09.2017.
 */

public class BoardFragment extends BaseFeedFragment {

    @InjectPresenter
    BoardPresenter mPresenter;

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO: 26.09.2017 проверить что будет, если убрать это переопределение
        super.onCreate(savedInstanceState);
    }*/

    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO: 26.09.2017 проверить что будет, если убрать это переопределение
        ButterKnife.bind(this, view);
    }*/

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_topics;
    }
}
