// IWebViewToMainAidlInterface.aidl
package com.lis.webview;
import com.lis.webview.IMainToWebViewCallbackAidlInterface;

interface IWebViewToMainAidlInterface {
    void handleWebViewCommand(String commandName,String jsonParams,in
    IMainToWebViewCallbackAidlInterface callback);
}