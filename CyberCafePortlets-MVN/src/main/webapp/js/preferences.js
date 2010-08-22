$(document).ready(function() {
    cc_setErrorHandler();
    cc_p_getPreferences();
})
$('#cc_p_refreshBtn')
    .button()
    .click(function() {
        cc_p_getPreferences();
    });

$('#cc_p_saveBtn')
    .button()
    .click(function() {
        cc_p_save();
    });
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