package com.polurival.fandroidvktest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.utils.VkListHelper;
import com.polurival.fandroidvktest.model.WallItem;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.model.view.NewsItemBodyViewModel;
import com.polurival.fandroidvktest.model.view.NewsItemFooterViewModel;
import com.polurival.fandroidvktest.model.view.NewsItemHeaderViewModel;
import com.polurival.fandroidvktest.rest.api.WallApi;
import com.polurival.fandroidvktest.rest.model.request.WallGetRequestModel;
import com.polurival.fandroidvktest.rest.model.response.GetWallResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFeedFragment {

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

        mWallApi.get(new WallGetRequestModel(-86529522).toMap()).enqueue(new Callback<GetWallResponse>() {
            @Override
            public void onResponse(Call<GetWallResponse> call, Response<GetWallResponse> response) {

                List<WallItem> wallItems = VkListHelper.getWallList(response.body().response);
                List<BaseViewModel> list = new ArrayList<>();

                for (WallItem item : wallItems) {
                    list.add(new NewsItemHeaderViewModel(item));
                    list.add(new NewsItemBodyViewModel(item));
                    list.add(new NewsItemFooterViewModel(item));
                }

                mBaseAdapter.addItems(list);

                Toast.makeText(getActivity(), "Likes: " + response.body().response.getItems().get(0).getLikes().getCount(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GetWallResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }
}
