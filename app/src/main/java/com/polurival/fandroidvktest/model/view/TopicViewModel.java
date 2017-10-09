package com.polurival.fandroidvktest.model.view;

import android.view.View;
import android.widget.TextView;

import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.manager.MyFragmentManager;
import com.polurival.fandroidvktest.model.Place;
import com.polurival.fandroidvktest.model.Topic;
import com.polurival.fandroidvktest.ui.activity.BaseActivity;
import com.polurival.fandroidvktest.ui.fragment.TopicCommentsFragment;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 26.09.2017.
 */

public class TopicViewModel extends BaseViewModel {

    private int mId;
    private int mGroupId;
    private String mTitle;
    private String mCommentsCount;

    public TopicViewModel() {
    }

    public TopicViewModel(Topic topic) {
        mId = topic.getId();
        mGroupId = topic.getGroupId();
        mTitle = topic.getTitle();
        mCommentsCount = topic.getComments() + " сообщений";
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Topic;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new TopicViewHolder(view);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getGroupId() {
        return mGroupId;
    }

    public void setGroupId(int groupId) {
        mGroupId = groupId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getCommentsCount() {
        return mCommentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        mCommentsCount = commentsCount;
    }

    public static class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_comments_count)
        TextView tvComments;

        @Inject
        MyFragmentManager mFragmentManager;

        public TopicViewHolder(View itemView) {
            super(itemView);
            MyApplication.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(TopicViewModel topicViewModel) {
            tvTitle.setText(topicViewModel.getTitle());
            tvComments.setText(topicViewModel.getCommentsCount());

            itemView.setOnClickListener(view ->
            mFragmentManager.addFragment((BaseActivity) itemView.getContext(),
                    TopicCommentsFragment.newInstance(new Place(String.valueOf(topicViewModel.getGroupId()),
                            String.valueOf(topicViewModel.getId()))),
                    R.id.main_wrapper));
        }

        @Override
        public void unbindViewHolder() {
            tvTitle.setText(null);
            tvComments.setText(null);
        }
    }
}
