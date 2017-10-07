package com.polurival.fandroidvktest.ui.view.holder.attachment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.utils.Utils;
import com.polurival.fandroidvktest.model.view.attachment.DocImageAttachmentViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 04.10.2017.
 */

public class DocImageAttachmentHolder extends BaseViewHolder<DocImageAttachmentViewModel> {

    @BindView(R.id.tv_attachment_title)
    public TextView title;

    @BindView(R.id.tv_attachment_ext)
    public TextView ext;

    @BindView(R.id.tv_attachment_size)
    public TextView size;

    @BindView(R.id.iv_attachment_image)
    public ImageView image;

    public DocImageAttachmentHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(DocImageAttachmentViewModel docImageAttachmentViewModel) {
        itemView.setOnClickListener(view ->
                Utils.openUrlInActionView(docImageAttachmentViewModel.getUrl(), view.getContext()));

        title.setText(docImageAttachmentViewModel.getTitle());
        size.setText(docImageAttachmentViewModel.getSize());
        ext.setText(docImageAttachmentViewModel.getExt());

        Glide.with(itemView.getContext())
                .load(docImageAttachmentViewModel.getImage())
                .into(image);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
        size.setText(null);
        ext.setText(null);
        image.setImageBitmap(null);
    }
}
