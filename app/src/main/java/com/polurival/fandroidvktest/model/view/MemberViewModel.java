package com.polurival.fandroidvktest.model.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.model.Member;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Polurival
 * on 16.09.2017.
 */

public class MemberViewModel extends BaseViewModel {

    private int mId;
    private int mGroupId;
    private String mPhoto;
    private String mFullName;

    public MemberViewModel(Member member) {
        mId = member.getId();
        mGroupId = member.getGroupId();
        mPhoto = member.getPhoto();
        mFullName = member.getFullName();
    }

    public int getId() {
        return mId;
    }

    public int getGroupId() {
        return mGroupId;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public String getFullName() {
        return mFullName;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Member;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new MemberViewHolder(view);
    }

    static class MemberViewHolder extends BaseViewHolder<MemberViewModel> {

        @BindView(R.id.civ_profile_image)
        CircleImageView civProfilePhoto;

        @BindView(R.id.tv_profile_name)
        TextView civProfileName;

        public MemberViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(MemberViewModel memberViewModel) {
            Context context = itemView.getContext();

            Glide.with(context)
                    .load(memberViewModel.getPhoto())
                    .into(civProfilePhoto);
            civProfileName.setText(memberViewModel.getFullName());
        }

        @Override
        public void unbindViewHolder() {
            civProfileName.setText(null);
            civProfilePhoto.setImageBitmap(null);
        }
    }
}
