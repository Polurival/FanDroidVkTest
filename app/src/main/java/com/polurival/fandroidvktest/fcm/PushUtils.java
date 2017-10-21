package com.polurival.fandroidvktest.fcm;

import android.content.Context;
import android.util.Log;

import com.polurival.fandroidvktest.consts.ApiConstants;

import java.util.Map;

/**
 * Created by Polurival
 * on 19.10.2017.
 */

public class PushUtils {

    public static FcmMessage parseFcmMessage(Map<String, String> source) {

        FcmMessage fcmMessage;

        switch (source.get(FcmMessage.TYPE)) {
            case FcmMessage.TYPE_NEW_POST:
                fcmMessage = new NewPostFcmMessage(source);
                break;
            case FcmMessage.TYPE_COMMENT: case FcmMessage.TYPE_REPLY:
                fcmMessage = new ReplyFcmMessage(source);
                break;
            default:
                fcmMessage = null;
                break;
        }
        return fcmMessage;
    }

    public static boolean isValid(Context context, int ownerId) {
        Log.d("pushUtils", "isValid");
        return ownerId == ApiConstants.CURRENT_GROUP_ID;
    }
}
