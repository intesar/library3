<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div>
    <table>
        <tr>
            <td>
                <table>
                    <tr><td>Contact Person</td><td><input type="text" id="cc_o_contactPerson" /></td></tr>
                    <tr><td>Contact Email</td><td><input type="text" id="cc_o_contactEmail" /></td></tr>
                    <tr><td>Cafe Phone</td><td><input type="text" id="cc_o_cafePhone" /></td></tr>
                    <tr><td>Cafe Fax</td><td><input type="text" id="cc_o_cafeFax" /></td></tr>
                </table>
            </td>
            <td>
                <table>
                    <tr><td>Street</td><td><input type="text" id="cc_o_street" /></td></tr>
                    <tr><td>City</td><td><input type="text" id="cc_o_city" /></td></tr>
                    <tr><td>Zipcode</td><td><input type="text" id="cc_o_zipcode" /></td></tr>
                    <tr><td>Timings</td><td><input type="text" id="cc_o_timing" /></td></tr>
                </table>
            </td>
        </tr>
        <tr><td></td><td></td></tr>
        <tr><td></td><td><input type="button" value="Save" onclick="cc_o_save()" />
                <input type="button" value="Cancel Changes" onclick="cc_o_cancel()" /></td></tr>
    </table>
</div>

<script type='text/javascript' src='/CyberCafePortlets/dwr/engine.js'></script>
<script type='text/javascript' src='/CyberCafePortlets/dwr/interface/AjaxAdminService.js'></script>
<script type="text/javascript">
    dwr.engine.setErrorHandler(cc_o_errorHandler);
    function cc_o_errorHandler(message, exception){
        alert(message);
        //Session timedout/invalidated
        if(exception && exception.javaClassName
            == 'com.bia.ccm.exceptions.LoginRequiredException'){
            //Reload or display an error etc.
            document.location.reload();
        }
    }
    window.onload = function() {cc_o_getOrganization();}
    var cc_o_dto;
    function cc_o_getOrganization() {
        dwr.engine.beginBatch();
        AjaxAdminService.getOrganization(cc_o_organization);
        dwr.engine.endBatch();
    }
    var cc_o_organization = function (dto) {
        cc_o_setValues('','','','','','','','');
        cc_o_setValues(cc_o_dto.contactName,cc_o_dto.contactEmail,cc_o_dto.phone,cc_o_dto.fax,
        cc_o_dto.street,cc_o_dto.city,cc_o_dto.zipcode,cc_o_dto.timings);
        cc_p_dto = dto;
    }
    function cc_o_setValues(contactPerson,contactEmail,cafePhone,cafeFax,street,city,zipcode,timing) {
        document.getElementById("cc_o_contactPerson").value = contactPerson;
        document.getElementById("cc_o_contactEmail").value = contactEmail;
        document.getElementById("cc_o_cafePhone").value = cafePhone;
        document.getElementById("cc_o_cafeFax").value = cafeFax;
        document.getElementById("cc_o_street").value = street;
        document.getElementById("cc_o_city").value = city;
        document.getElementById("cc_o_zipcode").value = zipcode;
        document.getElementById("cc_o_timing").value = timing;
    }
    function cc_o_getValues(contactPerson,contactEmail,cafePhone,cafeFax,street,city,zipcode,timing) {
        cc_o_dto.contactName=document.getElementById("cc_o_contactPerson").value;
        cc_o_dto.contactEmail=document.getElementById("cc_o_ContactEmail").value;
        cc_o_dto.phone=document.getElementById("cc_o_cafePhone").value;
        cc_o_dto.fax=document.getElementById("cc_o_cafeFax").value;
        cc_o_dto.street=document.getElementById("cc_o_street").value;
        cc_o_dto.city=document.getElementById("cc_o_city").value;
        cc_o_dto.zipcode=document.getElementById("cc_o_zipcode").value;
        cc_o_dto.timings=document.getElementById("cc_o_timing").value;
    }
    function cc_p_save() {
        dwr.engine.beginBatch();
        cc_o_getValues();
        AjaxAdminService.savePreferences(cc_o_dto,function() {
            alert('Date saved!');
        });
        dwr.engine.endBatch();
    }
</script>