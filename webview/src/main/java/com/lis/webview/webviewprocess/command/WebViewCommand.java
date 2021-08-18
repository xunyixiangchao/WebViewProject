package com.lis.webview.webviewprocess.command;

import com.lis.webview.IMainToWebViewCallbackAidlInterface;

import java.util.Map;

/**
 * Created by lis on 2021/8/11.
 */
public interface WebViewCommand {
    String name();
    void execute(Map parameters, IMainToWebViewCallbackAidlInterface callback);
}