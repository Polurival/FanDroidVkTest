package com.polurival.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.consts.ApiConstants;
import com.polurival.fandroidvktest.model.Member;
import com.polurival.fandroidvktest.model.Topic;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.model.view.TopicViewModel;
import com.polurival.fandroidvktest.mvp.view.BaseFeedView;
import com.polurival.fandroidvktest.rest.api.BoardApi;
import com.polurival.fandroidvktest.rest.model.request.BoardGetTopicsRequestModel;
import com.polurival.fandroidvktest.rest.model.response.BaseItemResponse;
import com.polurival.fandroidvktest.rest.model.response.Full;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Polurival
 * on 26.09.2017.
 */

@InjectViewState
public class BoardPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    BoardApi mBoardApi;

    public BoardPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getTopics(new BoardGetTopicsRequestModel(ApiConstants.CURRENT_GROUP_ID, count, offset).toMap())
                .flatMap(new Function<Full<BaseItemResponse<Topic>>, ObservableSource<? extends Topic>>() {
                    @Override
                    public ObservableSource<? extends Topic> apply(@NonNull Full<BaseItemResponse<Topic>> baseItemResponseFull) throws Exception {
                        return Observable.fromIterable(baseItemResponseFull.response.getItems());
                    }
                })
                .doOnNext(topic -> topic.setGroupId(ApiConstants.CURRENT_GROUP_ID))
                .doOnNext(this::saveToDb)
                .map(TopicViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .map(TopicViewModel::new);
    }

    private Callable<List<Topic>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.DESCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Topic> results = realm.where(Topic.class)
                    .equalTo("groupId", ApiConstants.CURRENT_GROUP_ID)
                    .findAllSorted(sortFields, sortOrder);

            return realm.copyFromRealm(results);
        };
    }
}
