package com.polurival.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.polurival.fandroidvktest.R;
import com.vk.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 02.10.2017.
 */

public class ImageFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView webView;

    public static ImageFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_image;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setBackgroundColor(getResources().getColor(R.color.colorDefaultWhite));

        webView.loadUrl(getArguments().getString("url"));
    }
}
