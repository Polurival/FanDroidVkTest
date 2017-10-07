package com.polurival.fandroidvktest.model.view;

import android.view.View;
import android.widget.RelativeLayout;

import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 30.09.2017.
 */

public class InfoLinksViewModel extends BaseViewModel {

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new InfoLinksViewHolder(view);
    }

    static class InfoLinksViewHolder extends BaseViewHolder<InfoLinksViewModel> {

        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;
        
        public InfoLinksViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
