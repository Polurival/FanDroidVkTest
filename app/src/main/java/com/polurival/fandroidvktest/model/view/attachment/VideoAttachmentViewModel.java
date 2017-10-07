package com.polurival.fandroidvktest.model.view.attachment;

import android.view.View;

import com.polurival.fandroidvktest.common.utils.Utils;
import com.polurival.fandroidvktest.model.attachment.video.Video;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;
import com.polurival.fandroidvktest.ui.view.holder.attachment.VideoAttachmentHolder;

/**
 * Created by Polurival
 * on 04.10.2017.
 */

public class VideoAttachmentViewModel extends BaseViewModel {

    private int id;
    private int ownerId;

    private String mTitle;
    private String mViewCount;
    private String mDuration;
    private String mImageUrl;

    public VideoAttachmentViewModel(Video video) {
        this.id = video.getId();
        this.ownerId = video.getOwnerId();

        if ("".equals(video.getTitle())) {
            mTitle = "Video";
        } else {
            mTitle = video.getTitle();
        }

        mViewCount = Utils.formatViewsCount(video.getViews());

        mDuration = Utils.parseDuration(video.getDuration());

        mImageUrl = video.getPhoto320();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentVideo;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new VideoAttachmentHolder(view);
    }

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getViewCount() {
        return mViewCount;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
