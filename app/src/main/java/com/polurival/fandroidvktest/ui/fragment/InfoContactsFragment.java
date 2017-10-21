package com.polurival.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.mvp.presenter.BaseFeedPresenter;
import com.polurival.fandroidvktest.mvp.presenter.InfoContactsPresenter;

import butterknife.ButterKnife;

/**
 * Фрагмент, отображающий список контактов группы
 *
 * Created by Polurival
 * on 21.10.2017.
 */

public class InfoContactsFragment extends BaseFeedFragment {

    @InjectPresenter
    InfoContactsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWithEndlessList(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApplication.getApplicationComponent().inject(this);

        ButterKnife.bind(this, view);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.title_contacts;
    }
}
