package com.lis.webviewproject.command;

import android.os.RemoteException;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.lis.base.util.ServiceLoaderUtil;
import com.lis.common.autoservice.IUserCenterService;
import com.lis.common.eventbus.LoginEvent;
import com.lis.webview.IMainToWebViewCallbackAidlInterface;
import com.lis.webview.webviewprocess.command.WebViewCommand;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * Created by lis on 2021/8/21.
 */
@AutoService(WebViewCommand.class)
public class CommandLogin implements WebViewCommand {
    private IMainToWebViewCallbackAidlInterface callback;

    @Override
    public String name() {
        return "login";
    }
    IUserCenterService service = ServiceLoaderUtil.getService(IUserCenterService.class);
    String jsCallbackName;
    public CommandLogin(){
        EventBus.getDefault().register(this);
    }
    @Override
    public void execute(Map parameters, IMainToWebViewCallbackAidlInterface callback) {
        Log.d("CommandLogin", new Gson().toJson(parameters));
        if (service != null && !service.isLogin()) {
            service.login();
            this.callback = callback;
            jsCallbackName = parameters.get("callbackname").toString();
        }else {
            Log.e("CommandLogin", "execute: service is null");
        }
    }
    @Subscribe
    public void onMessageEvent(LoginEvent event) {
        Log.d("CommandLogin", event.userName);
        HashMap map = new HashMap();
        map.put("accountName", event.userName);
        if(this.callback != null) {
            try {
                this.callback.onResult(jsCallbackName, new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}