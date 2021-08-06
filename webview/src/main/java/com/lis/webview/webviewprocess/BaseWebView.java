package com.lis.webview.webviewprocess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lis.webview.WebViewCallBack;
import com.lis.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.lis.webview.webviewprocess.webchromeclient.MyWebChromeClient;
import com.lis.webview.webviewprocess.webviewclient.MyWebViewClient;

/**
 * 基础WebView
 * Created by lis on 2021/8/6.
 */
public class BaseWebView extends WebView {
    public BaseWebView(@NonNull Context context) {
        this(context, null);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WebViewDefaultSettings.getInstance().setSettings(this);
    }

    public void registerWebViewCallback(WebViewCallBack callBack) {
        setWebViewClient(new MyWebViewClient(callBack));
        setWebChromeClient(new MyWebChromeClient(callBack));
    }

    @SuppressLint("JavascriptInterface")
    public void addJsInterface(String... interfaces) {
        for (String jsInterface : interfaces) {
            addJavascriptInterface(this, jsInterface);
        }
    }
}