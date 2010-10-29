$(document).ready(function() {
    cc_s_getServices();
});
function cc_s_getServices() {
    dwr.engine.beginBatch();
    AjaxCafeService.getAllServices(themeDisplay.getScopeGroupId(),cc_s_displayServices);
    dwr.engine.endBatch();
}
var cc_s_displayServices = function (dto) {
    cc_s_deleteAllRows("cc_s_table");
    cc_s_addRows("cc_s_table",dto);
}
function cc_s_addRows(id,dto){
    for ( var x =0; x < dto.length; x++) {
        var td1 = document.createElement("TD");
        td1.appendChild(document.createTextNode(dto[x].name));

        var td2 = document.createElement("TD");
        td2.appendChild (document.createTextNode(dto[x].units));
        var td3 = document.createElement("TD");
        td3.appendChild (document.createTextNode(dto[x].unitPrice));

        var td4 = document.createElement("TD");
        td4.appendChild (document.createTextNode(dto[x].saleTwoUnits));
        var td5 = document.createElement("TD");
        td5.appendChild (document.createTextNode(dto[x].saleTwoPrice));

        var row = document.createElement("TR");
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);

        var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
        tbody.appendChild(row);
    }
}
function cc_s_deleteAllRows(id) {
    var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
    while(tbody.hasChildNodes()) {
        tbody.removeChild(tbody.childNodes[0]);
    }
}
