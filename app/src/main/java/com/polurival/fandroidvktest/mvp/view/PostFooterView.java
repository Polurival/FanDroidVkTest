package com.polurival.fandroidvktest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.polurival.fandroidvktest.model.WallItem;
import com.polurival.fandroidvktest.model.view.counter.LikeCounterViewModel;

/**
 * Created by Polurival
 * on 08.10.2017.
 */

public interface PostFooterView extends MvpView {

    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}
