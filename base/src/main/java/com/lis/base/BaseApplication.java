package com.lis.base;

import android.app.Application;

/**
 * Created by lis on 2021/8/6.
 */
public class BaseApplication extends Application {
    public static Application baseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
    }
}