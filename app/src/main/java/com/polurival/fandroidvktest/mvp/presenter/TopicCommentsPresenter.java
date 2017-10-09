package com.polurival.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.common.utils.VkListHelper;
import com.polurival.fandroidvktest.model.CommentItem;
import com.polurival.fandroidvktest.model.Place;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.model.view.CommentBodyViewModel;
import com.polurival.fandroidvktest.model.view.CommentFooterViewModel;
import com.polurival.fandroidvktest.model.view.CommentHeaderViewModel;
import com.polurival.fandroidvktest.mvp.view.BaseFeedView;
import com.polurival.fandroidvktest.rest.api.BoardApi;
import com.polurival.fandroidvktest.rest.model.request.BoardGetCommentsRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Polurival
 * on 09.10.2017.
 */

@InjectViewState
public class TopicCommentsPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    BoardApi mBoardApi;

    private Place mPlace;

    public void setPlace(Place place) {
        mPlace = place;
    }

    public TopicCommentsPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getComments(new BoardGetCommentsRequestModel(Integer.parseInt(mPlace.getOwnerId()),
                Integer.parseInt(mPlace.getPostId()), offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getCommentsList(full.response, true)))
                .doOnNext(commentItem -> commentItem.setPlace(mPlace))
                .doOnNext(this::saveToDb)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .filter(commentItem -> commentItem.getPlace().equals(mPlace) && commentItem.isFromTopic)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }

    private List<BaseViewModel> parsePojoModel(CommentItem commentItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new CommentHeaderViewModel(commentItem));
        baseItems.add(new CommentBodyViewModel(commentItem));
        baseItems.add(new CommentFooterViewModel(commentItem));
        return baseItems;
    }

    private Callable<List<CommentItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"id"};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<CommentItem> results = realm.where(CommentItem.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };
    }
}
