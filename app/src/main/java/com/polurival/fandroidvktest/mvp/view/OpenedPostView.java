package com.polurival.fandroidvktest.mvp.view;

import com.polurival.fandroidvktest.model.view.NewsItemFooterViewModel;

/**
 * Created by Polurival
 * on 05.10.2017.
 */

public interface OpenedPostView extends BaseFeedView {

    void setFooter(NewsItemFooterViewModel viewModel);
}
