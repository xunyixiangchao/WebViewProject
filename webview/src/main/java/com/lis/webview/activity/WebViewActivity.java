package com.lis.webview.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lis.webview.R;
import com.lis.webview.activity.com.Constants;
import com.lis.webview.databinding.ActivityWebviewLayoutBinding;

/**
 * webview
 * Created by lis on 2021/7/30.
 */
public class WebViewActivity extends AppCompatActivity {
    ActivityWebviewLayoutBinding mBinding;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview_layout);
        url = getIntent().getStringExtra(Constants.URL);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content, WebViewFragment.getInstance(url));
        ft.commitAllowingStateLoss();

    }
}