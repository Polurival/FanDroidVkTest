package com.polurival.fandroidvktest.model.view.attachment;

import android.view.View;

import com.polurival.fandroidvktest.common.utils.Utils;
import com.polurival.fandroidvktest.model.attachment.Audio;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;
import com.polurival.fandroidvktest.ui.view.holder.attachment.AudioAttachmentHolder;

/**
 * Created by Polurival
 * on 04.10.2017.
 */

public class AudioAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mArtist;

    private String mDuration;

    public AudioAttachmentViewModel(Audio audio) {
        if ("".equals(audio.getTitle())) {
            mTitle = "Title";
        } else {
            mTitle = audio.getTitle();
        }

        if ("".equals(audio.getArtist())) {
            mArtist = "Various Artist";
        } else {
            mArtist = audio.getArtist();
        }

        mDuration = Utils.parseDuration(audio.getDuration());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentAudio;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new AudioAttachmentHolder(view);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getDuration() {
        return mDuration;
    }
}
