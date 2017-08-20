package com.polurival.fandroidvktest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.polurival.fandroidvktest.CurrentUser;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.rest.api.WallApi;
import com.polurival.fandroidvktest.rest.model.response.BaseItemResponse;
import com.polurival.fandroidvktest.rest.model.response.Full;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFragment {

    @Inject
    WallApi mWallApi;

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

        mWallApi.get("-86529522", CurrentUser.getAccessToken(), 1, "5.67").enqueue(new Callback<Full<BaseItemResponse>>() {
            @Override
            public void onResponse(Call<Full<BaseItemResponse>> call, Response<Full<BaseItemResponse>> response) {
                Toast.makeText(getActivity(), "Count: " + response.body().response.count, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Full<BaseItemResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

}
