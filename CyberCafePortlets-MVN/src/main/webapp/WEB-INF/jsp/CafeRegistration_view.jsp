<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<div>
    <table>
        <tr>
            <td><label>Organization ID</label></td><td><input type="text" id="cc_r_organizationId" name="cc_organizationId" /></td>
        </tr>
        <tr>
            <td><label>Organization Name</label></td><td><input type="text" id="cc_r_organizationName" name="cc_organizationName" /></td>
        </tr>
        <tr>
            <td<label>User Email</label></td><td><input type="text" id="cc_r_email" name="cc_email" /></td>
        </tr>
        <tr>
            <td></td><td><input type="button" value="Register New Cafe" onclick="cc_r_save();"/></td>
        </tr>
    </table>
</div>

<script type='text/javascript' src='/CyberCafePortlets/dwr/engine.js'></script>
<script type='text/javascript' src='/CyberCafePortlets/dwr/interface/AjaxAdminService.js'></script>
<script type="text/javascript">
    dwr.engine.setErrorHandler(cc_r_errorHandler);
    function cc_r_errorHandler(message, exception){
        alert(message);
        //Session timedout/invalidated
        if(exception && exception.javaClassName
            == 'com.bia.ccm.exceptions.LoginRequiredException'){
            //Reload or display an error etc.
            document.location.reload();
        }
    }
    function cc_r_clear() {
        document.getElementById("cc_r_organizationId").value = "";
        document.getElementById("cc_r_organizationName").value = "";
        document.getElementById("cc_r_email").value = "";
    }
    function cc_r_save() {
        var organizationId=document.getElementById("cc_r_organizationId").value;
        var organizationName=document.getElementById("cc_r_organizationName").value;
        var email=document.getElementById("cc_r_email").value;
        dwr.engine.beginBatch();
        AjaxAdminService.registerNewOrganization(organizationId,organizationName,email,cc_r_reply);
        dwr.engine.endBatch();
    }
    var cc_r_reply=function(reply){
        alert("Cafe Registration Successful!");
        cc_r_clear();
    }
</script>
