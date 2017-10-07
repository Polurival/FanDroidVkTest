package com.polurival.fandroidvktest.common.utils;

import com.polurival.fandroidvktest.model.Owner;
import com.polurival.fandroidvktest.model.WallItem;
import com.polurival.fandroidvktest.model.attachment.ApiAttachment;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.model.view.attachment.AudioAttachmentViewModel;
import com.polurival.fandroidvktest.model.view.attachment.DocAttachmentViewModel;
import com.polurival.fandroidvktest.model.view.attachment.DocImageAttachmentViewModel;
import com.polurival.fandroidvktest.model.view.attachment.ImageAttachmentViewModel;
import com.polurival.fandroidvktest.model.view.attachment.LinkAttachmentViewModel;
import com.polurival.fandroidvktest.model.view.attachment.LinkExternalViewModel;
import com.polurival.fandroidvktest.model.view.attachment.PageAttachmentViewModel;
import com.polurival.fandroidvktest.model.view.attachment.VideoAttachmentViewModel;
import com.polurival.fandroidvktest.rest.model.response.ItemWithSendersResponse;
import com.vk.sdk.api.model.VKAttachments;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public static List<BaseViewModel> getAttachmentVkItems(List<ApiAttachment> attachments) {
        List<BaseViewModel> attachmentVkItems = new ArrayList<>();
        for (ApiAttachment attachment : attachments) {

            switch (attachment.getType()) {
                case VKAttachments.TYPE_PHOTO:
                    attachmentVkItems.add(new ImageAttachmentViewModel(attachment.getPhoto()));
                    break;

                case VKAttachments.TYPE_AUDIO:
                    attachmentVkItems.add(new AudioAttachmentViewModel(attachment.getAudio()));
                    break;

                case VKAttachments.TYPE_VIDEO:
                    attachmentVkItems.add(new VideoAttachmentViewModel(attachment.getVideo()));
                    break;

                case VKAttachments.TYPE_DOC:
                    if (attachment.getDoc().getPreview() != null) {
                        attachmentVkItems.add(new DocImageAttachmentViewModel(attachment.getDoc()));
                    } else {
                        attachmentVkItems.add(new DocAttachmentViewModel(attachment.getDoc()));
                    }
                    break;

                case VKAttachments.TYPE_LINK:
                    if (attachment.getLink().getIsExternal() == 1) {
                        attachmentVkItems.add(new LinkExternalViewModel(attachment.getLink()));
                    } else {
                        attachmentVkItems.add(new LinkAttachmentViewModel(attachment.getLink()));
                    }
                    break;

                case "page":
                    attachmentVkItems.add(new PageAttachmentViewModel(attachment.getPage()));
                    break;

                default:
                    throw new NoSuchElementException("Attachment type " + attachment.getType() + " is not supported.");
            }
        }
        return attachmentVkItems;
    }
}
