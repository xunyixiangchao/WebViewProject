package com.lis.webview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.lis.common.autoservice.IWebViewService;
import com.lis.webview.activity.WebViewActivity;
import com.lis.webview.common.Constants;

/**
 * webview接口实现
 * Created by lis on 2021/7/30.
 */
@AutoService(IWebViewService.class)
public class WebViewServiceImpl implements IWebViewService {
    private static final String TAG = WebViewServiceImpl.class.getSimpleName();

    @Override
    public void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar) {
        if (context == null) {
            Log.e(TAG, "startWebViewActivity: context不能为空");
            return;
        }
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constants.URL, url);
        intent.putExtra(Constants.TITLE, title);
        intent.putExtra(Constants.IS_SHOW_ACTION_BAR, isShowActionBar);
        context.startActivity(intent);
    }
}