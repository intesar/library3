<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<div>
    <table>
        <tr><th>Email</th><th>Time</th></tr>
        <tr>
            <td>
                <table border="0">
                    <tr><td><input type="text" id="cc_p_email0" /> (example@gmail.com)</td></tr>
                    <tr><td><input type="text" id="cc_p_email1" /></td></tr>
                    <tr><td><input type="text" id="cc_p_email2" /></td></tr>
                    <tr><td><input type="text" id="cc_p_email3" /></td></tr>
                    <tr><td><input type="text" id="cc_p_email4" /></td></tr>
                </table>
            </td>
            <td>
                <table  border="0">
                    <tr><td><input type="text" id="cc_p_timing0" /> (0-2400 eg 900 is 9AM)</td></tr>
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
<script type="text/javascript" src="/CyberCafePortlets/js/preferences.js"></script>
