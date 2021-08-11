package com.lis.webview.webviewprocess;

import android.graphics.Bitmap;

/**
 * Created by lis on 2021/8/6.
 */
public interface WebViewCallBack {
    void pageStartedCallback(String url);

    void pageFinishedCallback(String url);

    void onErrorCallback();

    void updateTitleCallback(String title);

    void updateIconCallback(Bitmap icon);
}
