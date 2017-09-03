package com.polurival.fandroidvktest.common.utils;

import android.content.Context;

import com.polurival.fandroidvktest.model.attachment.ApiAttachment;
import com.vk.sdk.api.model.VKAttachments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Polurival
 * on 01.09.2017.
 */

public class Utils {

    public static String convertAttachmentsToFontIcons(List<ApiAttachment> attachments) {
        String attachmentsString = "";

        for (ApiAttachment attachment : attachments) {
            switch (attachment.getType()) {
                case VKAttachments.TYPE_PHOTO:
                    attachmentsString += new String(new char[]{0xE251}) + " ";
                    break;
                case VKAttachments.TYPE_AUDIO:
                    attachmentsString += new String(new char[]{0xE310}) + " ";
                    break;
                case VKAttachments.TYPE_VIDEO:
                    attachmentsString += new String(new char[]{0xE02C}) + " ";
                    break;
                case VKAttachments.TYPE_LINK:
                    attachmentsString += new String(new char[]{0xE250}) + " ";
                    break;
                case VKAttachments.TYPE_DOC:
                    attachmentsString += new String(new char[]{0xE24D}) + " ";
                    break;
            }
        }
        return attachmentsString;
    }

    public static String parseDate(long initialDate, Context context) {
        Locale currentLocale = context.getResources().getConfiguration().locale;

        Date date = new Date(initialDate * 1000);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy в H:mm", currentLocale);

        if (calendar.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR) &&
                calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
            sdf = new SimpleDateFormat("сегодня в H:mm", currentLocale);
        } else if (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
            sdf = new SimpleDateFormat("d, MMM в H:mm", currentLocale);
        }

        return sdf.format(date);
    }
}
