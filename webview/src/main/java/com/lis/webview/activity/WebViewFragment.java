package com.lis.webview.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.lis.webview.R;
import com.lis.webview.WebViewCallBack;
import com.lis.webview.common.Constants;
import com.lis.webview.databinding.FragmentWebviewBinding;

/**
 * webview的Fragment
 * Created by lis on 2021/7/30.
 */
public class WebViewFragment extends Fragment implements WebViewCallBack {

    private FragmentWebviewBinding mBinding;

    private WebViewFragment() {

    }

    private String mUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUrl = arguments.getString(Constants.URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        mBinding.webview.registerWebViewCallback(this);
        mBinding.webview.addJsInterface("mywebview");
        mBinding.webview.loadUrl(mUrl);
        //设置webViewClient，防止http链接跳转到原生浏览器打开
        return mBinding.getRoot();

    }

    public static WebViewFragment getInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(bundle);
        return webViewFragment;
    }

    @Override
    public void pageStartedCallback(String url) {

    }

    @Override
    public void pageFinishedCallback(String url) {

    }

    @Override
    public void onErrorCallback() {

    }

    @Override
    public void updateTitleCallback(String title) {

    }

    @Override
    public void updateIconCallback(Bitmap icon) {

    }
}