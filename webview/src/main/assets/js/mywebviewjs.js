var mywebviewjs={};
mywebviewjs.os={};
mywebviewjs.os.isIOS=/iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
mywebviewjs.os.isAndroid=!mywebviewjs.os.isIOS;
mywebviewjs.callbacks={};

mywebviewjs.callback= function (callbackname,response){
    var callbackobject=mywebviewjs.callbacks[callbackname];
    console.log("callback:"+callbackname)
    if(callbackobject !== undefined){
        if(callbackobject.callback !== undefined){
            console.log("callback:"+response);
            var ret = callbackobject.callback(response);
            if(ret=== false){
                return false
            }
            delete mywebviewjs.callbacks[callbackname];
        }
    }
}

mywebviewjs.takeNativeAction=function(commandName,parameters){
    console.log("mywebviewjs takenativeaction")
    var request = {};
    request.name = commandName;
    request.param = parameters;
    console.log("android take native action" + JSON.stringify(request));
    window.mywebview.takeNativeAction(JSON.stringify(request));
}

mywebviewjs.takeNativeActionWithCallback= function(commandName,parameters,callback){
    var callbackname = "nativetojs_callback_" +(new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    console.log("mywebviewjs takeNativeActionWithCallback "+callbackname)
    mywebviewjs.callbacks[callbackname] ={callback:callback};

    var request = {};
    request.name = commandName;
    request.param = parameters;
    request.param.callbackname = callbackname;
    if(window.mywebviewjs.os.isAndroid){
        window.mywebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.mywebview.postMessage(JSON.stringify(request))
    }
}