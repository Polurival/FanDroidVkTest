package com.polurival.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.consts.ApiConstants;
import com.polurival.fandroidvktest.model.Profile;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.model.view.MemberViewModel;
import com.polurival.fandroidvktest.mvp.view.BaseFeedView;
import com.polurival.fandroidvktest.rest.api.GroupsApi;
import com.polurival.fandroidvktest.rest.api.UsersApi;
import com.polurival.fandroidvktest.rest.model.request.GroupsGetByIdRequestModel;
import com.polurival.fandroidvktest.rest.model.request.UsersGetRequestModel;

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
public class InfoContactsPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    GroupsApi mGroupsApi;

    @Inject
    UsersApi mUsersApi;

    public InfoContactsPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupsApi.getGroupById(new GroupsGetByIdRequestModel(ApiConstants.CURRENT_GROUP_ID).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .flatMap(group -> Observable.fromIterable(group.getContactList()))
                .flatMap(contact -> mUsersApi.get(new UsersGetRequestModel(String.valueOf(contact.getUserId())).toMap()))
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(profile -> profile.setContact(true))
                .doOnNext(this::saveToDb)
                .flatMap(profile -> Observable.fromIterable(parsePojoModel(profile)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(profile -> Observable.fromIterable(parsePojoModel(profile)));
    }

    private List<BaseViewModel> parsePojoModel(List<Profile> profileList) {
        List<BaseViewModel> items = new ArrayList<>();
        for (Profile profile : profileList) {
            items.addAll(parsePojoModel(profile));
        }
        return items;
    }

    private List<BaseViewModel> parsePojoModel(Profile profile) {
        List<BaseViewModel> items = new ArrayList<>();
        items.add(new MemberViewModel(profile));

        return items;
    }

    public Callable<List<Profile>> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            List<Profile> result = realm.where(Profile.class)
                    .equalTo("isContact", true)
                    .findAll();
            return realm.copyFromRealm(result);
        };
    }
}
