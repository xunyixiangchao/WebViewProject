package com.lis.webview.webviewprocess.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lis.webview.webviewprocess.WebViewCallBack;

/**
 * 自定义WebViewClient
 * Created by lis on 2021/8/6.
 */
public class MyWebViewClient extends WebViewClient {
    private static final String TAG = MyWebViewClient.class.getSimpleName();
    private WebViewCallBack mWebViewCallBack;

    public MyWebViewClient(WebViewCallBack webViewCallBack) {
        mWebViewCallBack = webViewCallBack;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (mWebViewCallBack != null) {
            mWebViewCallBack.pageStartedCallback(url);
        } else {
            Log.e(TAG, "onPageStarted: WebViewCallBack is null.");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mWebViewCallBack != null) {
            mWebViewCallBack.pageFinishedCallback(url);
        } else {
            Log.e(TAG, "onPageFinished: WebViewCallBack is null.");
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (mWebViewCallBack != null) {
            mWebViewCallBack.onErrorCallback();
        } else {
            Log.e(TAG, "onReceivedError: WebViewCallBack is null.");
        }
    }
}