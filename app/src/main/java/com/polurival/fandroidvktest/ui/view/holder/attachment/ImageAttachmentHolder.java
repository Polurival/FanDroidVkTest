package com.polurival.fandroidvktest.ui.view.holder.attachment;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.manager.MyFragmentManager;
import com.polurival.fandroidvktest.model.view.attachment.ImageAttachmentViewModel;
import com.polurival.fandroidvktest.ui.activity.BaseActivity;
import com.polurival.fandroidvktest.ui.fragment.ImageFragment;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 02.10.2017.
 */

public class ImageAttachmentHolder extends BaseViewHolder<ImageAttachmentViewModel> {

    @BindView(R.id.iv_attachment_image)
    ImageView image;

    @Inject
    MyFragmentManager mFragmentManager;

    public ImageAttachmentHolder(View itemView) {
        super(itemView);

        MyApplication.getApplicationComponent().inject(this);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(ImageAttachmentViewModel imageAttachmentViewModel) {

        if (imageAttachmentViewModel.needClick) {
            itemView.setOnClickListener(view -> mFragmentManager.addFragment((BaseActivity) itemView.getContext(),
                    ImageFragment.newInstance(imageAttachmentViewModel.getPhotoUrl()),
                    R.id.main_wrapper));
        }

        Glide.with(itemView.getContext())
                .load(imageAttachmentViewModel.getPhotoUrl())
                .into(image);
    }

    @Override
    public void unbindViewHolder() {
        image.setOnClickListener(null);
        image.setImageBitmap(null);
    }
}
