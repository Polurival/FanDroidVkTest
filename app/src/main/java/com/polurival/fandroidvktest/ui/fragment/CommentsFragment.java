package com.polurival.fandroidvktest.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.model.Place;
import com.polurival.fandroidvktest.mvp.presenter.BaseFeedPresenter;
import com.polurival.fandroidvktest.mvp.presenter.CommentsPresenter;
import com.polurival.fandroidvktest.ui.activity.CreatePostActivity;

import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 08.10.2017.
 */

public class CommentsFragment extends BaseFeedFragment {

    @InjectPresenter
    CommentsPresenter mPresenter;

    Place mPlace;

    public static CommentsFragment newInstance(Place place) {

        Bundle args = new Bundle();
        args.putAll(place.toBundle());

        CommentsFragment fragment = new CommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MyApplication.getApplicationComponent().inject(this);

        mPlace = new Place(getArguments());
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().getFab().setOnClickListener(view -> {
            Intent intent = new Intent(getBaseActivity(), CreatePostActivity.class);
            intent.putExtra("type", "comment");
            intent.putExtra("owner_id", Integer.parseInt(mPlace.getOwnerId()));
            intent.putExtra("id", Integer.parseInt(mPlace.getPostId()));
            startActivityForResult(intent, 0);
        });
    }

    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }*/

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    @Override
    public boolean needFab() {
        return true;
    }
}
