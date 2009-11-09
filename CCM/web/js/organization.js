jq(document).ready(function() {
    var org;
    fillTable();
    function fillTable() {
        //dwr.util.useLoadingMessage("Please Wait, Loading");
        AjaxAdminService.getOrganization(function(people) {
            org = people;
            dwr.util.setValues(people);
        });
    }

    jq("#saveBtn").click(function() {
        dwr.util.getValues(org);
        AjaxAdminService.saveOrganization(org, function (data) {
            jq.facebox("<h2>" +data+ "</h2>");
        });
    })
});