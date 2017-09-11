package com.polurival.fandroidvktest.model.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;

import io.realm.RealmObject;

/**
 * Created by Polurival
 * on 01.09.2017.
 */

public class Page extends RealmObject implements Attachment {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("view_url")
    @Expose
    private String url;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getType() {
        return VKAttachments.TYPE_WIKI_PAGE;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
