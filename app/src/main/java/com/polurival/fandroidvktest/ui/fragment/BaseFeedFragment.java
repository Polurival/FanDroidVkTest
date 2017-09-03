package com.polurival.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.BaseAdapter;

/**
 * Created by Polurival
 * on 03.09.2017.
 */

public abstract class BaseFeedFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    protected BaseAdapter mBaseAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView(view);
        setUpAdapter();
    }

    private void setUpRecyclerView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpAdapter() {
        mBaseAdapter = new BaseAdapter();
        mRecyclerView.setAdapter(mBaseAdapter);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }
}
