package com.lis.webview.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lis.webview.R;
import com.lis.webview.common.Constants;
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
        mBinding.back.setOnClickListener(v -> finish());
        mBinding.title.setText(getIntent().getStringExtra(Constants.TITLE));
        mBinding.titleBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR
                , false) ? View.VISIBLE : View.GONE);
        url = getIntent().getStringExtra(Constants.URL);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content, WebViewFragment.getInstance(url, true));
        ft.commitAllowingStateLoss();

    }

    public void updateTitle(String title) {
        mBinding.title.setText(title);
    }
}