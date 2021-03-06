package com.polurival.fandroidvktest.rest.api;

import com.polurival.fandroidvktest.model.CommentItem;
import com.polurival.fandroidvktest.rest.model.response.Full;
import com.polurival.fandroidvktest.rest.model.response.GetWallByIdResponse;
import com.polurival.fandroidvktest.rest.model.response.GetWallResponse;
import com.polurival.fandroidvktest.rest.model.response.ItemWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Observable<GetWallResponse> get(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<GetWallByIdResponse> getById(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
