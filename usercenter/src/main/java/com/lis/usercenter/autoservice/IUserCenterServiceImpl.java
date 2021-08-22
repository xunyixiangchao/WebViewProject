package com.lis.usercenter.autoservice;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.lis.base.BaseApplication;
import com.lis.common.autoservice.IUserCenterService;
import com.lis.usercenter.ui.login.LoginActivity;

/**
 * Created by lis on 2021/8/21.
 */
@AutoService(IUserCenterService.class)
public class IUserCenterServiceImpl implements IUserCenterService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.baseApp, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.baseApp.startActivity(intent);
    }
}