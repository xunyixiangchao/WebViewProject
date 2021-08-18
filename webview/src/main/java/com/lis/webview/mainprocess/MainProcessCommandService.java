package com.lis.webview.mainprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * 主线程接收WebView子线程的Command服务
 * Created by lis on 2021/8/11.
 */
public class MainProcessCommandService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return MainProcessCommandsManager.getInstance();
    }
}