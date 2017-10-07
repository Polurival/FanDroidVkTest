package com.polurival.fandroidvktest.model.view.attachment;

import android.view.View;

import com.polurival.fandroidvktest.model.attachment.Page;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;
import com.polurival.fandroidvktest.ui.view.holder.attachment.PageAttachmentHolder;

/**
 * Created by Polurival
 * on 05.10.2017.
 */

public class PageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public PageAttachmentViewModel(Page page) {
        mUrl = page.getUrl();
        mTitle = page.getTitle();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
