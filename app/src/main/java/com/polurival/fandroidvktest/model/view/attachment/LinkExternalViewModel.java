package com.polurival.fandroidvktest.model.view.attachment;

import android.view.View;

import com.polurival.fandroidvktest.model.attachment.Link;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;
import com.polurival.fandroidvktest.ui.view.holder.attachment.LinkExternalAttachmentHolder;

/**
 * Created by Polurival
 * on 05.10.2017.
 */

public class LinkExternalViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    private String mImageUrl;

    public LinkExternalViewModel(Link link) {

        if (link.getTitle() == null || "".equals(link.getTitle())) {
            if (link.getName() != null) {
                mTitle = link.getName();
            } else {
                mTitle = "Link";
            }
        } else {
            mTitle = link.getTitle();
        }

        mUrl = link.getUrl();

        mImageUrl = link.getPhoto().getPhoto604();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentLinkExternal;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new LinkExternalAttachmentHolder(view);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
