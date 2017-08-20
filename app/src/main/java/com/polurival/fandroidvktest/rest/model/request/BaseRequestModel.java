package com.polurival.fandroidvktest.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.polurival.fandroidvktest.CurrentUser;
import com.polurival.fandroidvktest.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Polurival
 * on 21.08.2017.
 */

public abstract class BaseRequestModel {

    @SerializedName(VKApiConst.VERSION)
    Double version = ApiConstants.DEFAULT_VERSION;

    @SerializedName(VKApiConst.ACCESS_TOKEN)
    String accessToken = CurrentUser.getAccessToken();

    public Double getVersion() {
        return version;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put(VKApiConst.VERSION, String.valueOf(getVersion()));
        if (getAccessToken() != null) {
            map.put(VKApiConst.ACCESS_TOKEN, getAccessToken());
        }

        onMapCreate(map);

        return map;
    }

    public abstract void onMapCreate(Map<String, String> map);
}
