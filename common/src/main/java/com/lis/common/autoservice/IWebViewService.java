package com.lis.common.autoservice;


import android.content.Context;

/**
 * webview接口
 * Created by lis on 2021/7/30.
 */
public interface IWebViewService {
    void startWebViewActivity(Context context,String url,String title,boolean isShowActionBar);

    void startDemoHtml(Context context);
}
