$(document).ready(function() {
    cc_s_getServices();
});
var cc_s_dto = {};
var cc_s_service = 0;
function cc_s_getServices() {
    dwr.engine.beginBatch();
    AjaxAdminService.getAllServices(cc_s_displayServices);
    dwr.engine.endBatch();
}
var cc_s_displayServices = function (dto) {
    // Delete all the rows except for the "pattern" row
    cc_s_deleteAllRows("cc_s_table");
    // Create a new set cloned from the pattern row
    document.getElementById("cc_s_view").style.display = "block";
    document.getElementById("cc_s_edit").style.display = "none";
    cc_s_addRows("cc_s_table",dto);
}
function cc_s_addRows(id,dto){
    for ( var x =0; x < dto.length; x++) {
        cc_s_dto[dto[x].id] = dto[x];

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

        var td6 = document.createElement("TD");
        var link = document.createElement('a');
        link.setAttribute('href', 'javascript:cc_s_edit('+ dto[x].id +')');
        link.appendChild(document.createTextNode("Edit"));
        td6.appendChild(link);

        var row = document.createElement("TR");
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);

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
function cc_s_edit(id){
    cc_s_service = id;
    cc_s_displayNewServiceForm();
}
function cc_s_new(){
    cc_s_service = 0;
    cc_s_displayNewServiceForm();
}
function cc_s_displayNewServiceForm() {
    cc_s_clearNewServiceForm("","","","","");
    if(cc_s_service != 0 ) {
        cc_s_clearNewServiceForm(cc_s_dto[cc_s_service].name,cc_s_dto[cc_s_service].units,
            cc_s_dto[cc_s_service].unitPrice,cc_s_dto[cc_s_service].saleTwoUnits,cc_s_dto[cc_s_service].saleTwoPrice);
    }
    document.getElementById("cc_s_view").style.display = "none";
    document.getElementById("cc_s_edit").style.display = "block";
}
function cc_s_clearNewServiceForm(arg1,arg2,arg3,arg4,arg5) {
    document.getElementById("cc_s_name").value=arg1;
    document.getElementById("cc_s_units").value=arg2;
    document.getElementById("cc_s_price").value=arg3;
    document.getElementById("cc_s_secondUnits").value=arg4;
    document.getElementById("cc_s_secondPrice").value=arg4;
}
function cc_s_saveService() {
    var service;
    if ( cc_s_service != 0 ) {
        service = cc_s_dto[cc_s_service];
    } else {
        service = {
            id:null,
            name:null,
            units:null,
            unitPrice:null,
            saleTwoUnits:null,
            saleTwoPrice:null
        };
    }
    service.name = document.getElementById("cc_s_name").value;
    service.units = document.getElementById("cc_s_units").value;
    service.unitPrice = document.getElementById("cc_s_price").value;
    service.saleTwoUnits = document.getElementById("cc_s_secondUnits").value;
    service.saleTwoPrice = document.getElementById("cc_s_secondPrice").value;
    dwr.engine.beginBatch();
    AjaxAdminService.saveService(service, cc_s_displayServices);
    dwr.engine.endBatch();
}
function cc_s_cancel() {
    document.getElementById("cc_s_view").style.display = "block";
    document.getElementById("cc_s_edit").style.display = "none";
}
function cc_s_deleteService() {
    if (confirm(" Are you sure you want to delete this item ? ")) {
        dwr.engine.beginBatch();
        AjaxAdminService.deleteService(cc_s_service, cc_s_displayServices);
        dwr.engine.endBatch();
    }
}
