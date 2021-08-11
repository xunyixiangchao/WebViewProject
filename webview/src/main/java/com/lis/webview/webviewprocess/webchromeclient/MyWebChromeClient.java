package com.lis.webview.webviewprocess.webchromeclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.lis.webview.webviewprocess.WebViewCallBack;

/**
 * Created by lis on 2021/8/6.
 */
public class MyWebChromeClient extends WebChromeClient {
    private static final String TAG = MyWebChromeClient.class.getSimpleName();
    private WebViewCallBack mWebViewCallBack;

    public MyWebChromeClient(WebViewCallBack webViewCallBack) {
        mWebViewCallBack = webViewCallBack;
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        if (mWebViewCallBack != null) {
            mWebViewCallBack.updateIconCallback(icon);
        } else {
            Log.d(TAG, "onReceivedIcon:  WebViewCallBack is null.");
        }
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (mWebViewCallBack != null) {
            mWebViewCallBack.updateTitleCallback(title);
        } else {
            Log.d(TAG, "onReceivedIcon:  WebViewCallBack is null.");
        }
    }

    //打印日志
    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(TAG, consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}