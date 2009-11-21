var organization;
jq(document).ready(function() {
    
    fillDetails();
    function fillDetails() {
        //dwr.util.useLoadingMessage("Please Wait, Loading");
        AjaxAdminService.getOrganization(function(_organization) {
            organization = _organization;
            dwr.util.setValues(_organization);     
        });
    }
    //    jq("#topDiv").corner();
    jq('a[rel*=facebox]').facebox({
        loading_image : 'loading.gif',
        close_image   : 'closelabel.gif'
    })
    jq("#editBtn").click(function() {
        jq(".street1")[1].value = organization.street;
        jq(".city1")[1].value  = organization.city;
        jq(".zipcode1")[1].value = organization.zipcode;
        jq(".state1")[1].value = organization.state;
        jq(".country1")[1].value = organization.country;
        jq(".contactName1")[1].value = organization.contactName;
        jq(".phone1")[1].value = organization.phone;
        jq(".contactEmail1")[1].value = organization.contactEmail;
        jq(".printEmail1")[1].value = organization.printEmail;
        jq(".timings1")[1].value = organization.timings;
    })
    
    jq(".saveBtn").live("click", function() {
        organization.street = jq(".street1")[1].value;
        organization.city = jq(".city1")[1].value;
        organization.zipcode = jq(".zipcode1")[1].value;
        organization.state = jq(".state1")[1].value;
        organization.country = jq(".country1")[1].value;
        organization.contactName = jq(".contactName1")[1].value;
        organization.phone = jq(".phone1")[1].value;
        organization.contactEmail = jq(".contactEmail1")[1].value;
        organization.printEmail = jq(".printEmail1")[1].value;
        organization.timings = jq(".timings1")[1].value;
        AjaxAdminService.saveOrganization(organization, function (reply) {
            jq.facebox("<h2>" + reply + "</h2>");
            fillDetails();
        });
    })
});