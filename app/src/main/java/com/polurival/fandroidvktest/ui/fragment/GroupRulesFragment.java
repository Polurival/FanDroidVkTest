package com.polurival.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.polurival.fandroidvktest.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 21.10.2017.
 */

public class GroupRulesFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView mWebView;

    @BindString(R.string.screen_name_rules)
    String mTitle;

    public GroupRulesFragment() {
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_rules;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getBaseActivity().getProgressBar().setVisibility(View.VISIBLE);

        mWebView.loadUrl(getString(R.string.group_rules));
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                if (getBaseActivity() != null) {
                    getBaseActivity().getProgressBar().setVisibility(View.GONE);
                }
            }
        });
    }
}
