package com.polurival.fandroidvktest.ui.view.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.manager.MyFragmentManager;
import com.polurival.fandroidvktest.common.utils.Utils;
import com.polurival.fandroidvktest.common.utils.VkListHelper;
import com.polurival.fandroidvktest.model.Place;
import com.polurival.fandroidvktest.model.WallItem;
import com.polurival.fandroidvktest.model.countable.Likes;
import com.polurival.fandroidvktest.model.view.NewsItemFooterViewModel;
import com.polurival.fandroidvktest.model.view.counter.CommentCounterViewModel;
import com.polurival.fandroidvktest.model.view.counter.LikeCounterViewModel;
import com.polurival.fandroidvktest.model.view.counter.RepostCounterViewModel;
import com.polurival.fandroidvktest.rest.api.LikeEventOnSubscribe;
import com.polurival.fandroidvktest.rest.api.WallApi;
import com.polurival.fandroidvktest.rest.model.request.WallGetByIdRequestModel;
import com.polurival.fandroidvktest.ui.activity.BaseActivity;
import com.polurival.fandroidvktest.ui.fragment.CommentsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Polurival
 * on 03.09.2017.
 */

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooterViewModel> {

    public static final String POST = "post";

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_likes_icon)
    TextView tvLikesIcon;

    @BindView(R.id.tv_likes_count)
    TextView tvLikesCount;

    @BindView(R.id.tv_comments_icon)
    TextView tvCommentsIcon;

    @BindView(R.id.tv_comments_count)
    TextView tvCommentsCount;

    @BindView(R.id.tv_reposts_icon)
    TextView tvRepostsIcon;

    @BindView(R.id.tv_reposts_count)
    TextView tvRepostsCount;

    @BindView(R.id.rl_comments)
    View rlComments;

    @BindView(R.id.rl_likes)
    View rlLikes;

    @Inject
    WallApi mWallApi;

    @Inject
    Typeface mGoogleFontTypeface;

    @Inject
    MyFragmentManager mFragmentManager;

    private Resources mResources;
    private Context mContext;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        MyApplication.getApplicationComponent().inject(this);

        mContext = itemView.getContext();
        mResources = mContext.getResources();

        tvLikesIcon.setTypeface(mGoogleFontTypeface);
        tvCommentsIcon.setTypeface(mGoogleFontTypeface);
        tvRepostsIcon.setTypeface(mGoogleFontTypeface);
    }

    @Override
    public void bindViewHolder(NewsItemFooterViewModel item) {
        tvDate.setText(Utils.parseDate(item.getDateLong(), mContext));

        bindLikes(item.getLikes());
        bindComments(item.getComments());
        bindReposts(item.getReposts());

        rlComments.setOnClickListener(view ->
                mFragmentManager.addFragment((BaseActivity) view.getContext(),
                        CommentsFragment.newInstance(new Place(String.valueOf(item.getOwnerId()), String.valueOf(item.getId()))),
                        R.id.main_wrapper));

        rlLikes.setOnClickListener(view -> like(item));
    }

    private void bindLikes(LikeCounterViewModel likes) {
        tvLikesCount.setText(String.valueOf(likes.getCount()));
        tvLikesCount.setTextColor(mResources.getColor(likes.getTextColor()));
        tvLikesIcon.setTextColor(mResources.getColor(likes.getIconColor()));
    }

    private void bindComments(CommentCounterViewModel comments) {
        tvCommentsCount.setText(String.valueOf(comments.getCount()));
        tvCommentsCount.setTextColor(mResources.getColor(comments.getTextColor()));
        tvCommentsIcon.setTextColor(mResources.getColor(comments.getIconColor()));
    }

    private void bindReposts(RepostCounterViewModel reposts) {
        tvRepostsCount.setText(String.valueOf(reposts.getCount()));
        tvRepostsCount.setTextColor(mResources.getColor(reposts.getTextColor()));
        tvRepostsIcon.setTextColor(mResources.getColor(reposts.getIconColor()));
    }

    @Override
    public void unbindViewHolder() {
        tvDate.setText(null);
        tvLikesIcon.setText(null);
        tvLikesCount.setText(null);
        tvCommentsIcon.setText(null);
        tvCommentsCount.setText(null);
        tvRepostsIcon.setText(null);
        tvRepostsCount.setText(null);
    }

    public WallItem getWallItemFromRealm(int postId) {
        Realm realm = Realm.getDefaultInstance();
        WallItem wallItem = realm.where(WallItem.class)
                .equalTo("id", postId)
                .findFirst();

        return realm.copyFromRealm(wallItem);
    }

    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    public Observable<LikeCounterViewModel> likeObservable(int ownerId, int postId, Likes likes) {
        return Observable.create(new LikeEventOnSubscribe(likes, POST, ownerId, postId))
                .observeOn(Schedulers.io())
                .flatMap(count -> mWallApi.getById(new WallGetByIdRequestModel(ownerId, postId).toMap()))
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .doOnNext(this::saveToDb)
                .map(wallItem -> new LikeCounterViewModel(wallItem.getLikes()));
    }

    public void like(NewsItemFooterViewModel item) {
        WallItem wallItem = getWallItemFromRealm(item.getId());
        likeObservable(wallItem.getOwnerId(), wallItem.getId(), wallItem.getLikes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(likes -> {
                    item.setLikes(likes);
                    bindLikes(likes);
                }, Throwable::printStackTrace);
    }
}
