package com.lis.webview.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lis.webview.bean.JsParamVo;
import com.lis.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.lis.webview.webviewprocess.webchromeclient.MyWebChromeClient;
import com.lis.webview.webviewprocess.webviewclient.MyWebViewClient;

/**
 * 基础WebView
 * Created by lis on 2021/8/6.
 */
public class BaseWebView extends WebView {
    private static String TAG = BaseWebView.class.getSimpleName();

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
        WebViewCommandDispatcher.getInstance().initAidlConnection();
        WebViewDefaultSettings.getInstance().setSettings(this);
    }

    public void registerWebViewCallback(WebViewCallBack callBack) {
        //设置webViewClient，防止http链接跳转到原生浏览器打开
        setWebViewClient(new MyWebViewClient(callBack));
        setWebChromeClient(new MyWebChromeClient(callBack));
    }

    public void addJsInterface(String... interfaces) {
        for (String jsInterface : interfaces) {
            addJavascriptInterface(this, jsInterface);
        }
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        if (!TextUtils.isEmpty(jsParam)) {
            JsParamVo jsParamVo = new Gson().fromJson(jsParam, JsParamVo.class);
            if (jsParamVo != null) {
                WebViewCommandDispatcher.getInstance().executeCommand(jsParamVo.name,
                        new Gson().toJson(jsParamVo.param), this);
            }
        }
    }

    public void handleCallback(final String callbackName, final String response) {
        if (!TextUtils.isEmpty(callbackName) && !TextUtils.isEmpty(response)) {
            //同一线程才能调用
            post(() -> {
                String js = "javascript:mywebviewjs.callback('" + callbackName + "'," + response + ")";
                Log.i(TAG, js);
                evaluateJavascript(js, null);
            });
        }
    }
}