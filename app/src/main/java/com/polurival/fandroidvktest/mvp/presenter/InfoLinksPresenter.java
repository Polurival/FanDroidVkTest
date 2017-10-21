package com.polurival.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.consts.ApiConstants;
import com.polurival.fandroidvktest.model.Group;
import com.polurival.fandroidvktest.model.attachment.Link;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.model.view.attachment.LinkAttachmentViewModel;
import com.polurival.fandroidvktest.mvp.view.BaseFeedView;
import com.polurival.fandroidvktest.rest.api.GroupsApi;
import com.polurival.fandroidvktest.rest.model.request.GroupsGetByIdRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by Polurival
 * on 21.10.2017.
 */

@InjectViewState
public class InfoLinksPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    GroupsApi mGroupsApi;

    public InfoLinksPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupsApi.getGroupById(new GroupsGetByIdRequestModel(ApiConstants.CURRENT_GROUP_ID).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb)
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    private List<BaseViewModel> parsePojoModel(Group group) {
        List<BaseViewModel> items = new ArrayList<>();
        for (Link link : group.getLinks()) {
            items.add(new LinkAttachmentViewModel(link));
        }
        return items;
    }

    private Callable<Group> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Group result = realm.where(Group.class)
                    .equalTo("id", Math.abs(ApiConstants.CURRENT_GROUP_ID))
                    .findFirst();
            return realm.copyFromRealm(result);
        };
    }
}
