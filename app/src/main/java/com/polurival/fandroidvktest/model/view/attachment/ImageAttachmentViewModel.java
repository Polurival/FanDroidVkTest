package com.polurival.fandroidvktest.model.view.attachment;

import android.view.View;

import com.polurival.fandroidvktest.model.attachment.Photo;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.ui.view.holder.attachment.ImageAttachmentHolder;

/**
 * Created by Polurival
 * on 02.10.2017.
 */

public class ImageAttachmentViewModel extends BaseViewModel {

    private String mPhotoUrl;

    public boolean needClick = true;

    public ImageAttachmentViewModel(String url) {
        mPhotoUrl = url;
        needClick = false;
    }

    public ImageAttachmentViewModel(Photo photo) {
        mPhotoUrl = photo.getPhoto604();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentImage;
    }

    @Override
    protected ImageAttachmentHolder onCreateViewHolder(View view) {
        return new ImageAttachmentHolder(view);
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }
}
