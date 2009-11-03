var org;
function fillTable() {
    //dwr.util.useLoadingMessage("Please Wait, Loading");
    AjaxAdminService.getOrganization(function(people) {
        org = people;
        dwr.util.setValues(people);
    });
}


function writePerson() {
    dwr.util.getValues(org);
    AjaxAdminService.saveOrganization(org, function (data) {
        clearMessages();
        if (  data == 'Operation succesful!') {
            writeMessage("successReply", " Profile Updated at " + new Date().toLocaleString());
            fillTable();
        } else {
            writeMessage("failureReply", "Please try with different values");
        }
    });
}