package com.polurival.fandroidvktest.common;

import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Polurival
 * on 26.08.2017.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>> {

    private List<BaseViewModel> mList = new ArrayList<>();

    private ArrayMap<Integer, BaseViewModel> mTypeInstances = new ArrayMap<>();

    @Override
    public BaseViewHolder<BaseViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTypeInstances.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<BaseViewModel> holder, int position) {
        holder.bindViewHolder(getItem(position));
    }

    @Override
    public void onViewRecycled(BaseViewHolder<BaseViewModel> holder) {
        super.onViewRecycled(holder);
        holder.unbindViewHolder();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().getValue();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public BaseViewModel getItem(int position) {
        return mList.get(position);
    }

    public void registerTypeInstance(BaseViewModel item) {
        if (!mTypeInstances.containsKey(item.getType().getValue())) {
            mTypeInstances.put(item.getType().getValue(), item);
        }
    }

    public void addItems(List<? extends BaseViewModel> newItems) {
        for (BaseViewModel newItem : newItems) {
            registerTypeInstance(newItem);
        }

        mList.addAll(newItems);

        notifyDataSetChanged();
    }

    public void setItems(List<BaseViewModel> items) {
        clearList();
        addItems(items);
    }

    public void clearList() {
        mList.clear();
    }

    public int getRealItemCount() {
        int count = 0;
        for (int i = 0; i < getItemCount(); i++) {
            if (!getItem(i).isItemDecorator()) {
                count += 1;
            }
        }
        return count;
    }
}
