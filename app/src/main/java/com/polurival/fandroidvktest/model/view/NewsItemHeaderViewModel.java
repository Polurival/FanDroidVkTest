package com.polurival.fandroidvktest.model.view;

import android.view.View;

import com.polurival.fandroidvktest.model.WallItem;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;
import com.polurival.fandroidvktest.ui.view.holder.NewsItemHeaderHolder;

/**
 * Created by Polurival
 * on 27.08.2017.
 */

public class NewsItemHeaderViewModel extends BaseViewModel {

    private int mId;

    private String mProfilePhoto;
    private String mProfileName;

    private boolean mIsRepost;
    private String mRepostProfileName;

    public NewsItemHeaderViewModel(WallItem wallItem) {
        mId = wallItem.getId();
        mProfilePhoto = wallItem.getSenderPhoto();
        mProfileName = wallItem.getSenderName();
        mIsRepost = wallItem.haveSharedRepost();
        if (mIsRepost) {
            mRepostProfileName = wallItem.getSharedRepost().getSenderName();
        }
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemHeader;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemHeaderHolder(view);
    }

    public int getId() {
        return mId;
    }

    public String getProfilePhoto() {
        return mProfilePhoto;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public boolean isRepost() {
        return mIsRepost;
    }

    public String getRepostProfileName() {
        return mRepostProfileName;
    }
}
