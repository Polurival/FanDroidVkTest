package com.polurival.fandroidvktest.rest.api;

import com.polurival.fandroidvktest.model.CommentItem;
import com.polurival.fandroidvktest.model.Topic;
import com.polurival.fandroidvktest.rest.model.response.BaseItemResponse;
import com.polurival.fandroidvktest.rest.model.response.Full;
import com.polurival.fandroidvktest.rest.model.response.ItemWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Polurival
 * on 26.09.2017.
 */

public interface BoardApi {

    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);

    @GET(ApiMethods.BOARD_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
