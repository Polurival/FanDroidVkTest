package com.polurival.fandroidvktest.common.utils;

import com.polurival.fandroidvktest.model.Owner;
import com.polurival.fandroidvktest.model.WallItem;
import com.polurival.fandroidvktest.rest.model.response.ItemWithSendersResponse;

import java.util.List;

/**
 * Created by Polurival
 * on 27.08.2017.
 */

public class VkListHelper {

    /**
     * заполняет поля senderName и senderPhoto для отправителя записи,
     * а также поля для отправителя репоста, если запись является репостом
     */
    public static List<WallItem> getWallList(ItemWithSendersResponse<WallItem> response) {
        List<WallItem> wallItems = response.items;

        for (WallItem wallItem : wallItems) {
            Owner sender = response.getSender(wallItem.getFromId());
            wallItem.setSenderName(sender.getFullName());
            wallItem.setSenderPhoto(sender.getPhoto());

            wallItem.setAttachmentsString(Utils.convertAttachmentsToFontIcons(wallItem.getAttachments()));

            if (wallItem.haveSharedRepost()) {
                Owner repostSender = response.getSender(wallItem.getSharedRepost().getFromId());
                wallItem.getSharedRepost().setSenderName(repostSender.getFullName());
                wallItem.getSharedRepost().setSenderPhoto(repostSender.getPhoto());

                wallItem.getSharedRepost().setAttachmentsString(Utils.convertAttachmentsToFontIcons(
                        wallItem.getSharedRepost().getAttachments()
                ));
            }
        }

        return wallItems;
    }
}
