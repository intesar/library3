<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<div>
    <table>
        <tr>
            <td>
                <table border="0">
                    <tr><td><input type="text" id="cc_p_email0" /></td></tr>
                    <tr><td><input type="text" id="cc_p_email1" /></td></tr>
                    <tr><td><input type="text" id="cc_p_email2" /></td></tr>
                    <tr><td><input type="text" id="cc_p_email3" /></td></tr>
                    <tr><td><input type="text" id="cc_p_email4" /></td></tr>
                </table>
            </td>
            <td>
                <table  border="0">
                    <tr><td><input type="text" id="cc_p_timing0" /></td></tr>
                    <tr><td><input type="text" id="cc_p_timing1" /></td></tr>
                    <tr><td><input type="text" id="cc_p_timing2" /></td></tr>
                    <tr><td><input type="text" id="cc_p_timing3" /></td></tr>
                    <tr><td><input type="text" id="cc_p_timing4" /></td></tr>
                </table>
            </td>
        </tr>
    </table>

    <div>
        <input type="button" value="Save" onclick="cc_p_save();" />
        <input type="button" value="Cancel Changes" onclick="cc_p_getPreferences();" />
    </div>
</div>

<script type='text/javascript' src='/CyberCafePortlets/dwr/engine.js'></script>
<script type='text/javascript' src='/CyberCafePortlets/dwr/interface/AjaxAdminService.js'></script>
<script type="text/javascript">
    dwr.engine.setErrorHandler(cc_p_errorHandler);
    function cc_p_errorHandler(message, exception){
        alert("inside errorHandler " + message+ " " +exception.javaClassName);
        //Session timedout/invalidated
        if(exception && exception.javaClassName
            == 'org.directwebremoting.extend.LoginRequiredException'){
            //Reload or display an error etc.
            document.location.reload();
        }
    }
    window.onload = function() {cc_p_getPreferences();}
    var cc_p_dto;
    var cc_p_max = 5;
    function cc_p_getPreferences() {
        dwr.engine.beginBatch();
        AjaxAdminService.getPreferences(cc_p_preferences);
        dwr.engine.endBatch();
    }
    var cc_p_preferences = function (dto) {
        cc_p_clear();
        cc_p_dto = dto;
        for ( var x =0; x < cc_p_max && x < dto.emails.length; x++) {
            document.getElementById("cc_p_email"+x).value = dto.emails[x];
        }
        for ( var x =0; x < cc_p_max && x < dto.timings.length; x++) {
            document.getElementById("cc_p_timing"+x).value = dto.timings[x];
        }
    }

    function cc_p_clear() {
        for ( var x =0; x < cc_p_max; x++) {
            document.getElementById("cc_p_email"+x).value = "";
            document.getElementById("cc_p_timing"+x).value = "";
        }
    }
    function cc_p_save() {
        for ( var x =0; x < cc_p_max; x++) {
            cc_p_dto.emails[x]=document.getElementById("cc_p_email"+x).value;
        }
        for ( var x =0; x < cc_p_max; x++) {
            var time = document.getElementById("cc_p_timing"+x).value;
            cc_p_dto.timings[x]= (time.length > 0) ? time : 0;
        }
        dwr.engine.beginBatch();
        AjaxAdminService.savePreferences(cc_p_dto);
        dwr.engine.endBatch();
    }
</script>
