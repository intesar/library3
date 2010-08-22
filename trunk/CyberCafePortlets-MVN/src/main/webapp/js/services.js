$(document).ready(function() {
    cc_s_getServices();
});
$(function() {
    var name = $("#cc_s_name"),
    units = $("#cc_s_units"),
    price = $("#cc_s_price"),
    allFields = $([]).add(name).add(units).add(price),
    tips = $(".validateTips");

    function updateTips(t) {
        tips
        .text(t)
        .addClass('ui-state-highlight');
        setTimeout(function() {
            tips.removeClass('ui-state-highlight', 1500);
        }, 500);
    }

    function checkLength(o,n,min,max) {

        if ( o.val().length > max || o.val().length < min ) {
            o.addClass('ui-state-error');
            updateTips("Length of " + n + " must be between "+min+" and "+max+".");
            return false;
        } else {
            return true;
        }

    }

    function checkRegexp(o,regexp,n) {

        if ( !( regexp.test( o.val() ) ) ) {
            o.addClass('ui-state-error');
            updateTips(n);
            return false;
        } else {
            return true;
        }

    }

    $("#cc_s_edit").dialog({
        autoOpen: false,
        //height: 300,
        width: 400,
        modal: true,
        buttons: {
            'Save': function() {
                var bValid = true;
                allFields.removeClass('ui-state-error');

                bValid = bValid && checkLength(name,"Name",2,16);
                //bValid = bValid && checkLength(units,"cc_s_units",6,80);
                //bValid = bValid && checkLength(price,"cc_s_price",5,16);

                bValid = bValid && checkRegexp(name,/^[a-z]([0-9a-z_])+$/i,"Name may consist of a-z, 0-9, underscores, begin with a letter.");
                bValid = bValid && checkRegexp(units,/^([0-9])+$/i,"Units should be a number.");
                bValid = bValid && checkRegexp(price,/^([0-9.])+$/i,"Price is not valid.");

                // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
                //bValid = bValid && checkRegexp(units,/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,"eg. ui@jquery.com");
                //bValid = bValid && checkRegexp(password,/^([0-9a-zA-Z])+$/,"Password field only allow : a-z 0-9");

                if (bValid) {
                    cc_s_saveService();
//                    $('#users tbody').append('<tr>' +
//                        '<td>' + name.val() + '</td>' +
//                        '<td>' + email.val() + '</td>' +
//                        '<td>' + password.val() + '</td>' +
//                        '</tr>');
//                    $(this).dialog('close');
                }
            },
            'Delete': function() {
                cc_s_deleteService();
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        },
        close: function() {
            allFields.val('').removeClass('ui-state-error');
        }
    });

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
//    document.getElementById("cc_s_view").style.display = "block";
//    document.getElementById("cc_s_edit").style.display = "none";
    cc_s_addRows("cc_s_table",dto);
    $('#cc_s_edit').dialog('close');
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
    $('#cc_s_edit').dialog('open');
    cc_s_displayNewServiceForm();
}

$('#new-service')
    .button()
    .click(function() {
        cc_s_service = 0;
        $('#cc_s_edit').dialog('open');
        cc_s_displayNewServiceForm();
    });

function cc_s_displayNewServiceForm() {
    cc_s_clearNewServiceForm("","","","","");
    if(cc_s_service != 0 ) {
        cc_s_clearNewServiceForm(cc_s_dto[cc_s_service].name,cc_s_dto[cc_s_service].units,
            cc_s_dto[cc_s_service].unitPrice,cc_s_dto[cc_s_service].saleTwoUnits,cc_s_dto[cc_s_service].saleTwoPrice);
    }
    //document.getElementById("cc_s_view").style.display = "none";
    //document.getElementById("cc_s_edit").style.display = "block";
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
        $(this).dialog('close');
    }
}
