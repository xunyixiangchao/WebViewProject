package com.lis.webview.mainprocess;

import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;
import com.lis.base.util.ServiceLoaderUtil;
import com.lis.webview.IMainToWebViewCallbackAidlInterface;
import com.lis.webview.IWebViewToMainAidlInterface;
import com.lis.webview.webviewprocess.command.WebViewCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * 主进程处理WebiView传回的Command的Binder服务端
 * Created by lis on 2021/8/11.
 */
public class MainProcessCommandsManager extends IWebViewToMainAidlInterface.Stub {
    private static String TAG = MainProcessCommandsManager.class.getSimpleName();
    private static volatile MainProcessCommandsManager instance;
    private static HashMap<String, WebViewCommand> mCommands = new HashMap<>();

    public static MainProcessCommandsManager getInstance() {
        if (instance == null) {
            synchronized (MainProcessCommandsManager.class) {
                if (instance == null) {
                    instance = new MainProcessCommandsManager();
                }
            }
        }
        return instance;
    }

    private MainProcessCommandsManager() {
        ServiceLoader<WebViewCommand> commands = ServiceLoader.load(WebViewCommand.class);
        for (WebViewCommand command : commands) {
            if (!mCommands.containsKey(command.name())) {
                mCommands.put(command.name(), command);
            }
        }
    }

    @Override
    public void handleWebViewCommand(String commandName, String jsonParams, IMainToWebViewCallbackAidlInterface callback) throws RemoteException {
        executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class), callback);
    }

    private void executeCommand(String commandName, Map params, IMainToWebViewCallbackAidlInterface callback) {
        WebViewCommand webViewCommand = mCommands.get(commandName);
        if (webViewCommand != null) {
            webViewCommand.execute(params, callback);
        } else {
            Log.w(TAG, "WebViewCommand为null");
        }
    }
}