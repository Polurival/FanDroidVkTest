package com.polurival.fandroidvktest.consts;

import com.vk.sdk.VKScope;

/**
 * Created by Polurival
 * on 17.08.2017.
 */

public class ApiConstants {

    // для разрешений при отправки запросов на сервер VK
    public static final String[] DEFAULT_LOGIN_SCOPE = {VKScope.EMAIL};

    public static final Double DEFAULT_VERSION = 5.68;
    public static final int DEFAULT_COUNT = 10;

    public static final String DEFAULT_USER_FIELDS = "photo_100";
    public static final String DEFAULT_MEMBER_FIELDS = "name,photo_100";

    public static final int MY_GROUP_ID = -86529522;
}
