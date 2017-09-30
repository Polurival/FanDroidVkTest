package com.polurival.fandroidvktest.rest.api;

import com.polurival.fandroidvktest.model.Group;
import com.polurival.fandroidvktest.model.Member;
import com.polurival.fandroidvktest.rest.model.response.BaseItemResponse;
import com.polurival.fandroidvktest.rest.model.response.Full;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Polurival
 * on 16.09.2017.
 */

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<Full<BaseItemResponse<Member>>> getMembers(@QueryMap Map<String, String> map);

    @GET(ApiMethods.GROUPS_GET_BY_ID)
    Observable<Full<List<Group>>> getGroupById(@QueryMap Map<String, String> map);
}
