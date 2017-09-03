package com.polurival.fandroidvktest.model.view.counter;

import com.polurival.fandroidvktest.model.Comments;

/**
 * Created by Polurival
 * on 03.09.2017.
 */

public class CommentCounterViewModel extends CounterViewModel {

    private Comments mComments;

    public CommentCounterViewModel(Comments comments) {
        super(comments.getCount());

        mComments = comments;
    }

    public Comments getComments() {
        return mComments;
    }
}
