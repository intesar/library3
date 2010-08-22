$(document).ready(function() {
    cc_setErrorHandler();
    cc_o_getOrganization();
})
$('#cc_o_refreshBtn')
    .button()
    .click(function() {
        cc_o_getOrganization();
    });

$('#cc_o_saveBtn')
    .button()
    .click(function() {
        cc_o_save();
    });
var cc_o_dto;
function cc_o_getOrganization() {
    dwr.engine.beginBatch();
    AjaxAdminService.getOrganization(cc_o_organization);
    dwr.engine.endBatch();
}
var cc_o_organization = function (dto) {
    cc_o_setValues('','','','','','','','');
    cc_o_dto = dto;
    cc_o_setValues(cc_o_dto.contactName,cc_o_dto.contactEmail,cc_o_dto.phone,cc_o_dto.fax,
        cc_o_dto.street,cc_o_dto.city,cc_o_dto.zipcode,cc_o_dto.timings);
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
function cc_o_getValues() {
    cc_o_dto.contactName=document.getElementById("cc_o_contactPerson").value;
    cc_o_dto.contactEmail=document.getElementById("cc_o_contactEmail").value;
    cc_o_dto.phone=document.getElementById("cc_o_cafePhone").value;
    cc_o_dto.fax=document.getElementById("cc_o_cafeFax").value;
    cc_o_dto.street=document.getElementById("cc_o_street").value;
    cc_o_dto.city=document.getElementById("cc_o_city").value;
    cc_o_dto.zipcode=document.getElementById("cc_o_zipcode").value;
    cc_o_dto.timings=document.getElementById("cc_o_timing").value;
}
function cc_o_save() {
    dwr.engine.beginBatch();
    cc_o_getValues();
    AjaxAdminService.saveOrganization(cc_o_dto,function() {
        alert('Data saved!');
    });
    dwr.engine.endBatch();
}