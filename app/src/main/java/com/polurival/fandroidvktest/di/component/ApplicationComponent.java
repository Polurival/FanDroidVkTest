package com.polurival.fandroidvktest.di.component;

import com.polurival.fandroidvktest.common.manager.NetworkManager;
import com.polurival.fandroidvktest.di.module.ApplicationModule;
import com.polurival.fandroidvktest.di.module.ManagerModule;
import com.polurival.fandroidvktest.di.module.RestModule;
import com.polurival.fandroidvktest.model.view.CommentBodyViewModel.CommentBodyViewHolder;
import com.polurival.fandroidvktest.model.view.CommentFooterViewModel.CommentFooterHolder;
import com.polurival.fandroidvktest.model.view.TopicViewModel.TopicViewHolder;
import com.polurival.fandroidvktest.mvp.presenter.BoardPresenter;
import com.polurival.fandroidvktest.mvp.presenter.CommentsPresenter;
import com.polurival.fandroidvktest.mvp.presenter.InfoPresenter;
import com.polurival.fandroidvktest.mvp.presenter.MainPresenter;
import com.polurival.fandroidvktest.mvp.presenter.MembersPresenter;
import com.polurival.fandroidvktest.mvp.presenter.NewsFeedPresenter;
import com.polurival.fandroidvktest.mvp.presenter.OpenedCommentPresenter;
import com.polurival.fandroidvktest.mvp.presenter.OpenedPostPresenter;
import com.polurival.fandroidvktest.mvp.presenter.TopicCommentsPresenter;
import com.polurival.fandroidvktest.ui.activity.BaseActivity;
import com.polurival.fandroidvktest.ui.activity.MainActivity;
import com.polurival.fandroidvktest.ui.fragment.CommentsFragment;
import com.polurival.fandroidvktest.ui.fragment.NewsFeedFragment;
import com.polurival.fandroidvktest.ui.fragment.OpenedCommentFragment;
import com.polurival.fandroidvktest.ui.fragment.OpenedPostFragment;
import com.polurival.fandroidvktest.ui.fragment.TopicCommentsFragment;
import com.polurival.fandroidvktest.ui.view.holder.NewsItemBodyHolder;
import com.polurival.fandroidvktest.ui.view.holder.NewsItemFooterHolder;
import com.polurival.fandroidvktest.ui.view.holder.attachment.ImageAttachmentHolder;
import com.polurival.fandroidvktest.ui.view.holder.attachment.VideoAttachmentHolder;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    //activities
    void inject(BaseActivity activity);

    void inject(MainActivity activity);

    //fragments
    void inject(NewsFeedFragment fragment);

    void inject(OpenedPostFragment fragment);

    void inject(OpenedCommentFragment fragment);

    void inject(CommentsFragment fragment);

    void inject(TopicCommentsFragment fragment);

    //holders
    void inject(NewsItemBodyHolder holder);

    void inject(NewsItemFooterHolder holder);

    void inject(ImageAttachmentHolder holder);

    void inject(VideoAttachmentHolder holder);

    void inject(CommentBodyViewHolder holder);

    void inject(CommentFooterHolder holder);

    void inject(TopicViewHolder holder);

    //presenters
    void inject(MainPresenter presenter);

    void inject(NewsFeedPresenter presenter);

    void inject(MembersPresenter presenter);

    void inject(BoardPresenter presenter);

    void inject(InfoPresenter presenter);

    void inject(OpenedPostPresenter presenter);

    void inject(CommentsPresenter presenter);

    void inject(OpenedCommentPresenter presenter);

    void inject(TopicCommentsPresenter presenter);

    //managers
    void inject(NetworkManager manager);
}
