package com.polurival.fandroidvktest.model.view;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.manager.MyFragmentManager;
import com.polurival.fandroidvktest.ui.activity.BaseActivity;
import com.polurival.fandroidvktest.ui.fragment.InfoLinksFragment;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

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

    public static class InfoLinksViewHolder extends BaseViewHolder<InfoLinksViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;

        public InfoLinksViewHolder(View itemView) {
            super(itemView);
            MyApplication.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {
            itemView.setOnClickListener(view -> {
                Log.d("CLICK_LINK", "click to InfoLinksViewModel");
                mFragmentManager.addFragment((BaseActivity) view.getContext(), new InfoLinksFragment(),
                        R.id.main_wrapper);
            });
        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
