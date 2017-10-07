package com.polurival.fandroidvktest.model.view;

import android.view.View;

import com.polurival.fandroidvktest.model.WallItem;
import com.polurival.fandroidvktest.model.view.counter.CommentCounterViewModel;
import com.polurival.fandroidvktest.model.view.counter.LikeCounterViewModel;
import com.polurival.fandroidvktest.model.view.counter.RepostCounterViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;
import com.polurival.fandroidvktest.ui.view.holder.NewsItemFooterHolder;

/**
 * Created by Polurival
 * on 03.09.2017.
 */

public class NewsItemFooterViewModel extends BaseViewModel {

    private int mId;
    private int ownerId;
    private long mDateLong;

    private LikeCounterViewModel mLikes;
    private CommentCounterViewModel mComments;
    private RepostCounterViewModel mReposts;

    public NewsItemFooterViewModel(WallItem wallItem) {
        mId = wallItem.getId();
        ownerId = wallItem.getOwnerId();
        mDateLong = wallItem.getDate();
        mLikes = new LikeCounterViewModel(wallItem.getLikes());
        mComments = new CommentCounterViewModel(wallItem.getComments());
        mReposts = new RepostCounterViewModel(wallItem.getReposts());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemFooter;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemFooterHolder(view);
    }

    public int getId() {
        return mId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public long getDateLong() {
        return mDateLong;
    }

    public LikeCounterViewModel getLikes() {
        return mLikes;
    }

    public CommentCounterViewModel getComments() {
        return mComments;
    }

    public RepostCounterViewModel getReposts() {
        return mReposts;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setDateLong(long dateLong) {
        mDateLong = dateLong;
    }

    public void setLikes(LikeCounterViewModel likes) {
        mLikes = likes;
    }

    public void setComments(CommentCounterViewModel comments) {
        mComments = comments;
    }

    public void setReposts(RepostCounterViewModel reposts) {
        mReposts = reposts;
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }
}
