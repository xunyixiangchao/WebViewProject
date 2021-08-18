package com.lis.webviewproject.command;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.lis.base.BaseApplication;
import com.lis.webview.IMainToWebViewCallbackAidlInterface;
import com.lis.webview.webviewprocess.command.WebViewCommand;

import java.util.Map;

/**
 * 显示Toast
 * Created by lis on 2021/8/18.
 */
@AutoService(WebViewCommand.class)
public class CommandShowToast implements WebViewCommand {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map parameters, IMainToWebViewCallbackAidlInterface callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.baseApp, String.valueOf(parameters.get("message")),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}