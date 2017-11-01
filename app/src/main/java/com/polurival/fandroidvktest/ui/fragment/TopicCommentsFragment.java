package com.polurival.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.model.Place;
import com.polurival.fandroidvktest.mvp.presenter.BaseFeedPresenter;
import com.polurival.fandroidvktest.mvp.presenter.TopicCommentsPresenter;

import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 09.10.2017.
 */

public class TopicCommentsFragment extends BaseFeedFragment {

    public static TopicCommentsFragment newInstance(Place place) {

        Bundle args = new Bundle();
        args.putAll(place.toBundle());

        TopicCommentsFragment fragment = new TopicCommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    TopicCommentsPresenter mPresenter;

    Place mPlace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MyApplication.getApplicationComponent().inject(this);
        setWithEndlessList(true);

        mPlace = new Place(getArguments());
    }

    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }*/

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setPlace(mPlace);
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_comments;
    }
}
