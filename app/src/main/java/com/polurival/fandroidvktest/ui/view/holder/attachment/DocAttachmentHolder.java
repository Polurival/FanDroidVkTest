package com.polurival.fandroidvktest.ui.view.holder.attachment;

import android.view.View;
import android.widget.TextView;

import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.utils.Utils;
import com.polurival.fandroidvktest.model.view.attachment.DocAttachmentViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 04.10.2017.
 */

public class DocAttachmentHolder extends BaseViewHolder<DocAttachmentViewModel> {

    @BindView(R.id.tv_attachment_title)
    public TextView title;

    @BindView(R.id.tv_attachment_ext)
    public TextView ext;

    @BindView(R.id.tv_attachment_size)
    public TextView size;

    public DocAttachmentHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(DocAttachmentViewModel docAttachmentViewModel) {
        if (docAttachmentViewModel.needClick) {
            itemView.setOnClickListener(view ->
                    Utils.openUrlInActionView(docAttachmentViewModel.getUrl(), view.getContext()));
        }

        title.setText(docAttachmentViewModel.getTitle());
        size.setText(docAttachmentViewModel.getSize());
        ext.setText(docAttachmentViewModel.getExt());
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
        size.setText(null);
        ext.setText(null);
    }
}
