package com.polurival.fandroidvktest.model.view.counter;

import com.polurival.fandroidvktest.model.countable.Likes;

/**
 * Created by Polurival
 * on 03.09.2017.
 */

public class LikeCounterViewModel extends CounterViewModel {

    private Likes mLikes;

    public LikeCounterViewModel(Likes likes) {
        super(likes.getCount());

        mLikes = likes;

        if (mLikes.getUserLikes() == 1) {
            setAccentColor();
        }
    }

    public Likes getLikes() {
        return mLikes;
    }
}
