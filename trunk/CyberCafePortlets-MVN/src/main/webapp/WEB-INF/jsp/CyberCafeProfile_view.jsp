<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div>
    <table>
        <tr>
            <td>
                <table>
                    <tr><td><label>Contact Person</label></td><td><input type="text" id="cc_o_contactPerson" /></td></tr>
                    <tr><td><label>Contact Email</label></td><td><input type="text" id="cc_o_contactEmail" /></td></tr>
                    <tr><td><label>Cafe Phone</label></td><td><input type="text" id="cc_o_cafePhone" /></td></tr>
                    <tr><td><label>Cafe Fax</label></td><td><input type="text" id="cc_o_cafeFax" /></td></tr>
                </table>
            </td>
            <td>
                <table>
                    <tr><td><label>Street</label></td><td><input type="text" id="cc_o_street" /></td></tr>
                    <tr><td><label>City</label></td><td><input type="text" id="cc_o_city" /></td></tr>
                    <tr><td><label>Zipcode</label></td><td><input type="text" id="cc_o_zipcode" /></td></tr>
                    <tr><td><label>Timings</label></td><td><input type="text" id="cc_o_timing" /></td></tr>
                </table>
            </td>
        </tr>
        <tr><td></td><td></td></tr>
        <tr><td></td><td><input type="button" value="Save" onclick="cc_o_save()" />
                <input type="button" value="Cancel Changes" onclick="cc_o_getOrganization()" /></td></tr>
    </table>
</div>
<script type='text/javascript' src='/CyberCafePortlets/dwr/engine.js'></script>
<script type='text/javascript' src='/CyberCafePortlets/dwr/interface/AjaxAdminService.js'></script>
<script type="text/javascript" src="/CyberCafePortlets/js/cybercafeprofile.js"></script>