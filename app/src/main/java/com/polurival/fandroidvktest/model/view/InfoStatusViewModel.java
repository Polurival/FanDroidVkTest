package com.polurival.fandroidvktest.model.view;

import android.view.View;
import android.widget.TextView;

import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.model.Group;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 30.09.2017.
 */

public class InfoStatusViewModel extends BaseViewModel {

    private String mStatus;
    private String mDescription;
    private String mSite;

    public InfoStatusViewModel(Group group) {
        mStatus = group.getStatus();
        mDescription = group.getDescription();
        mSite = group.getSite();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoStatus;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new InfoStatusViewHolder(view);
    }

    public String getStatus() {
        return mStatus;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getSite() {
        return mSite;
    }

    static class InfoStatusViewHolder extends BaseViewHolder<InfoStatusViewModel> {

        @BindView(R.id.tv_status_text)
        TextView tvStatusText;

        @BindView(R.id.tv_description_text)
        TextView tvDescriptionText;

        @BindView(R.id.tv_site_text)
        TextView tvSiteText;

        public InfoStatusViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoStatusViewModel infoStatusViewModel) {
            tvStatusText.setText(infoStatusViewModel.getStatus());
            tvDescriptionText.setText(infoStatusViewModel.getDescription());
            tvSiteText.setText(infoStatusViewModel.getSite());
        }

        @Override
        public void unbindViewHolder() {
            tvStatusText.setText(null);
            tvDescriptionText.setText(null);
            tvSiteText.setText(null);
        }
    }
}
