package com.polurival.fandroidvktest.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.ui.fragment.MyPreferencesFragment;

/**
 * Created by Polurival
 * on 17.10.2017.
 */

public class SettingActivity extends BaseActivity {

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(R.id.main_wrapper, new MyPreferencesFragment())
                .commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Настройки");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFab().hide();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
