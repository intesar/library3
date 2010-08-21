var filesadded="" //list of files already added
var isErrorHandlerSet = false;
function checkloadjscssfile(filename, filetype){
    if (filesadded.indexOf("["+filename+"]")==-1){
        loadjscssfile(filename, filetype);
        filesadded+="["+filename+"]"; //List of files added in the form "[filename1],[filename2],etc"
    }
}
function loadjscssfile(filename, filetype){
    var fileref;
    if (filetype=="js"){ //if filename is a external JavaScript file
        fileref=document.createElement('script');
        fileref.setAttribute("type","text/javascript");
        fileref.setAttribute("src", filename);
    }
    else if (filetype=="css"){ //if filename is an external CSS file
        fileref=document.createElement("link");
        fileref.setAttribute("rel", "stylesheet");
        fileref.setAttribute("type", "text/css");
        fileref.setAttribute("href", filename);
    }
    if (typeof fileref!="undefined")
        document.getElementsByTagName("head")[0].appendChild(fileref);
}
function cc_setErrorHandler() {
    if (!isErrorHandlerSet) {
        dwr.engine.setErrorHandler(errorHandler);
        isErrorHandlerSet = true;
    }
}
function errorHandler(message, exception){
    alert(message);
    //Session timedout/invalidated
    if(exception && exception.javaClassName
        == 'com.bia.ccm.exceptions.LoginRequiredException'){
        //Reload or display an error etc.
        document.location.reload();
    }
}
