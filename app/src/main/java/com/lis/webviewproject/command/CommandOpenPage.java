package com.lis.webviewproject.command;

import android.content.ComponentName;
import android.content.Intent;

import com.google.auto.service.AutoService;
import com.lis.base.BaseApplication;
import com.lis.webview.IMainToWebViewCallbackAidlInterface;
import com.lis.webview.webviewprocess.command.WebViewCommand;

import java.util.Map;

/**
 * Created by lis on 2021/8/21.
 */
@AutoService(WebViewCommand.class)
public class CommandOpenPage implements WebViewCommand {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map parameters, IMainToWebViewCallbackAidlInterface callback) {
        String target_class = (String) parameters.get("target_class");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BaseApplication.baseApp, target_class));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.baseApp.startActivity(intent);
    }
}