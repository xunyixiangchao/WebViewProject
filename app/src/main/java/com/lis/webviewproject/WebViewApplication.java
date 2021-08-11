package com.lis.webviewproject;

import com.kingja.loadsir.core.LoadSir;
import com.lis.base.BaseApplication;
import com.lis.common.loadsir.CustomCallback;
import com.lis.common.loadsir.EmptyCallback;
import com.lis.common.loadsir.ErrorCallback;
import com.lis.common.loadsir.LoadingCallback;
import com.lis.common.loadsir.TimeoutCallback;

/**
 * Created by lis on 2021/8/6.
 */
public class WebViewApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder().addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }

}