package com.polurival.fandroidvktest.model.view;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.manager.MyFragmentManager;
import com.polurival.fandroidvktest.ui.activity.BaseActivity;
import com.polurival.fandroidvktest.ui.fragment.InfoContactsFragment;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 30.09.2017.
 */

public class InfoContactsViewModel extends BaseViewModel {

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoContacts;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new InfoContactsViewHolder(view);
    }

    public static class InfoContactsViewHolder extends BaseViewHolder<InfoContactsViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.rv_contacts)
        RelativeLayout rvContacts;
        
        public InfoContactsViewHolder(View itemView) {
            super(itemView);
            MyApplication.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoContactsViewModel infoContactsViewModel) {
            itemView.setOnClickListener(view -> {
                Log.d("CLICK_CONTACTS", "click to InfoContactsViewModel");
                mFragmentManager.addFragment((BaseActivity) view.getContext(), new InfoContactsFragment(),
                        R.id.main_wrapper);
            });
        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
