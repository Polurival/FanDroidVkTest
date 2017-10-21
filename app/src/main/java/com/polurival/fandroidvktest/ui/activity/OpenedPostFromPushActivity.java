package com.polurival.fandroidvktest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.polurival.fandroidvktest.MyApplication;
import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.manager.MyFragmentManager;
import com.polurival.fandroidvktest.model.Place;
import com.polurival.fandroidvktest.ui.fragment.OpenedPostFragment;

import javax.inject.Inject;

/**
 * активити, которое открывается при клике на уведомлении
 *
 * Created by Polurival
 * on 19.10.2017.
 */

public class OpenedPostFromPushActivity extends BaseActivity {

    @Inject
    MyFragmentManager myFragmentManager;

    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);

        //из интента получаем place
        Bundle bundle = getIntent().getBundleExtra(Place.PLACE);

        Place place = new Place(bundle);
        //открываем пост по id из place
        myFragmentManager.addFragment(this,
                OpenedPostFragment.newInstance(Integer.valueOf(place.getPostId())),
                R.id.main_wrapper);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_opened_post_from_push;
    }

    @Override
    public void onBackPressed() {

        Log.d("BACKSTACK", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));

        //управляем стеком переходов назад, из открытого поста выходим в приложение
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }
}
