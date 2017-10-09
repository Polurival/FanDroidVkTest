package com.polurival.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.common.utils.VkListHelper;
import com.polurival.fandroidvktest.consts.ApiConstants;
import com.polurival.fandroidvktest.model.WallItem;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.model.view.NewsItemFooterViewModel;
import com.polurival.fandroidvktest.model.view.OpenedPostHeaderViewModel;
import com.polurival.fandroidvktest.model.view.OpenedPostRepostHeaderViewModel;
import com.polurival.fandroidvktest.mvp.view.OpenedPostView;
import com.polurival.fandroidvktest.rest.api.WallApi;
import com.polurival.fandroidvktest.rest.model.request.WallGetByIdRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by Polurival
 * on 05.10.2017.
 */

@InjectViewState
public class OpenedPostPresenter extends BaseFeedPresenter<OpenedPostView> {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Inject
    WallApi mWallApi;

    public OpenedPostPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mWallApi.getById(new WallGetByIdRequestModel(ApiConstants.MY_GROUP_ID, id).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(wallItem -> {
                    NewsItemFooterViewModel newsItemFooterViewModel = new NewsItemFooterViewModel(wallItem);
                    getViewState().setFooter(newsItemFooterViewModel);
                })
                .observeOn(Schedulers.io())
                .doOnNext(this::saveToDb)
                .flatMap(wallItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(wallItem));

                    list.addAll(VkListHelper.getAttachmentVkItems(wallItem.getAttachments()));
                    if (wallItem.haveSharedRepost()) {

                        forwardedList.add(new OpenedPostRepostHeaderViewModel(wallItem.getSharedRepost()));
                        forwardedList.addAll(VkListHelper.getAttachmentVkItems(wallItem.getSharedRepost().getAttachments()));
                    }
                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(wallItem -> {
                    NewsItemFooterViewModel newsItemFooterViewModel = new NewsItemFooterViewModel(wallItem);

                    getViewState().setFooter(newsItemFooterViewModel);
                })

                .observeOn(Schedulers.io())
                .flatMap(wallItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(wallItem));

                    list.addAll(VkListHelper.getAttachmentVkItems(wallItem.getAttachments()));
                    if (wallItem.haveSharedRepost()) {

                        forwardedList.add(new OpenedPostRepostHeaderViewModel(wallItem.getSharedRepost()));
                        forwardedList.addAll(VkListHelper.getAttachmentVkItems(wallItem.getSharedRepost().getAttachments()));
                    }
                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });
    }

    private Callable<WallItem> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            WallItem wallItem = realm.where(WallItem.class).equalTo("id", id).findFirst();

            return realm.copyFromRealm(wallItem);
        };
    }
}
