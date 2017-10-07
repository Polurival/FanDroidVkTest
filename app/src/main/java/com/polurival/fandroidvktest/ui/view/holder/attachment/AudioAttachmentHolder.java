package com.polurival.fandroidvktest.ui.view.holder.attachment;

import android.view.View;
import android.widget.TextView;

import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.model.view.attachment.AudioAttachmentViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 04.10.2017.
 */

public class AudioAttachmentHolder extends BaseViewHolder<AudioAttachmentViewModel> {

    @BindView(R.id.tv_audio_name)
    public TextView name;

    @BindView(R.id.tv_audio_artist)
    public TextView artist;

    @BindView(R.id.tv_audio_duration)
    public TextView duration;

    public AudioAttachmentHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(AudioAttachmentViewModel audioAttachmentViewModel) {
        name.setText(audioAttachmentViewModel.getTitle());
        artist.setText(audioAttachmentViewModel.getArtist());
        duration.setText(audioAttachmentViewModel.getDuration());
    }

    @Override
    public void unbindViewHolder() {
        name.setText(null);
        artist.setText(null);
        duration.setText(null);
    }
}
