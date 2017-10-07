package com.polurival.fandroidvktest.model.view.counter;

import com.polurival.fandroidvktest.model.countable.Reposts;

/**
 * Created by Polurival
 * on 03.09.2017.
 */

public class RepostCounterViewModel extends CounterViewModel {

    private Reposts mReposts;

    public RepostCounterViewModel(Reposts reposts) {
        super(reposts.getCount());

        mReposts = reposts;
        if (mReposts.getUserReposted() == 1) {
            setAccentColor();
        }
    }

    public Reposts getReposts() {
        return mReposts;
    }
}
