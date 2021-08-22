package com.lis.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.lis.base.BaseApplication;
import com.lis.webview.IMainToWebViewCallbackAidlInterface;
import com.lis.webview.IWebViewToMainAidlInterface;
import com.lis.webview.mainprocess.MainProcessCommandService;

/**
 * webview命令分发器
 * Created by lis on 2021/8/11.
 */
public class WebViewCommandDispatcher implements ServiceConnection {
    private static volatile WebViewCommandDispatcher instance;
    private IWebViewToMainAidlInterface mWebViewToMainAidlInterface;

    public static WebViewCommandDispatcher getInstance() {
        if (instance == null) {
            synchronized (WebViewCommandDispatcher.class) {
                if (instance == null) {
                    instance = new WebViewCommandDispatcher();
                }
            }
        }
        return instance;
    }

    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.baseApp, MainProcessCommandService.class);
        BaseApplication.baseApp.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mWebViewToMainAidlInterface = IWebViewToMainAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mWebViewToMainAidlInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        mWebViewToMainAidlInterface = null;
        initAidlConnection();
    }

    public void executeCommand(String commandName, String param, BaseWebView webView) {
        if (mWebViewToMainAidlInterface != null) {
            try {
                mWebViewToMainAidlInterface.handleWebViewCommand(commandName, param, new IMainToWebViewCallbackAidlInterface.Stub() {
                    @Override
                    public void onResult(String callbackName, String response) throws RemoteException {
                        Log.i("WebViewDispatcher", "onResult: " + response);
                        webView.handleCallback(callbackName, response);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}