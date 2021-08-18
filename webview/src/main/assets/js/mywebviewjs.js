var mywebviewjs={};



mywebviewjs.takeNativeAction=function(commandName,parameters){
    console.log("mywebviewjs takenativeaction")
    var request = {};
    request.name = commandName;
    request.param = parameters;
    console.log("android take native action" + JSON.stringify(request));
    window.mywebview.takeNativeAction(JSON.stringify(request));
}
//window.mywebviewjs = mywebviewjs;