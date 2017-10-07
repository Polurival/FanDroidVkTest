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

public class InfoContactsViewModel extends BaseViewModel {

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoContacts;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new InfoContactsViewHolder(view);
    }

    static class InfoContactsViewHolder extends BaseViewHolder<InfoContactsViewModel> {

        @BindView(R.id.rv_contacts)
        RelativeLayout rvContacts;
        
        public InfoContactsViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoContactsViewModel infoContactsViewModel) {

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
