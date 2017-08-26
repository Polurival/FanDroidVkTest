package com.polurival.fandroidvktest.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.polurival.fandroidvktest.model.view.BaseViewModel;

/**
 * Created by Polurival
 * on 26.08.2017.
 */

public abstract class BaseViewHolder<Item extends BaseViewModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * заполняет макет данными с модели Item
     * @param item - модель данных
     */
    public abstract void bindViewHolder(Item item);

    /**
     * разгружает макет, когда он не используется
     */
    public abstract void unbindViewHolder();
}
