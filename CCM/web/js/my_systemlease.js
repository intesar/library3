jq(document).ready(function() {
    

    getTodaysHistory();
    function getTodaysHistory() {
        var now = new Date();
        var date = now.getFullYear() + "-" + (now.getMonth()+1) + "-" + now.getDate();
        jq('#DPC_startDate_YYYY-MM-DD').val(date);
        jq('#DPC_endDate_YYYY-MM-DD').val(date);
        AjaxAdminService.getMySystemLease(date,date, reply1 );
    }
});
function execute() {
    dwr.util.useLoadingMessage("Please wait, Loading");
    //alert ( document.getElementById("startDate").value);
    var startDate = document.getElementById("DPC_startDate_YYYY-MM-DD").value;
    var endDate = document.getElementById("DPC_endDate_YYYY-MM-DD").value;
    if ( startDate != null && startDate.length >= 9 && endDate != null && endDate.length >= 9 ) {
        AjaxCustomerService.getMySystemLease(startDate,endDate, reply1 );
    } else {
        alert ( " invalid dates ");
    }
}


var peopleCache = { };
var viewed = null;

var reply1 = function(people) {
    // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("peoplebody", {
        filter:function(tr) {
            return (tr.id != "pattern");
        }
    });
    // Create a new set cloned from the pattern row
    var person, id;
    if ( people.length == 0) {
        writeMessage ( "failureReply", "No recoreds found!");
    } else  {
        //people.sort(function(p1, p2) { return p1.macAddress.localeCompare(p2.macAddress); });
        for (var i = 0; i < people.length; i++) {
            person = people[i];
            id = person.id;
            dwr.util.cloneNode("pattern", {
                idSuffix:id
            });
            dwr.util.setValue("leaseHolderName" + id, person.leaseHolderName);
            dwr.util.setValue("system" + id, person.service);
            dwr.util.setValue("startTimeString" + id, person.startTimeString);
            dwr.util.setValue("endTimeString" + id, person.endTimeString);
            dwr.util.setValue("totalMinutesUsed" + id, person.totalMinutesUsed);
            dwr.util.setValue("payableAmount" + id, person.payableAmount);
            dwr.util.setValue("amountPaid" + id, person.amountPaid);
            var maxLength = 0;
            var issueAgentEmail = person.issueAgent;
            if ( issueAgentEmail != null && issueAgentEmail.length > 0 ) {
                maxLength = issueAgentEmail.toString().indexOf("@");
                maxLength = maxLength <= 14 ? maxLength : 14;
                dwr.util.setValue("issueAgent" + id, issueAgentEmail.toString().substring(0,maxLength));
            }
            var issueReturnedEmail = person.returnAgent;
            if ( issueReturnedEmail != null && issueReturnedEmail.length > 0 ) {
                maxLength = issueReturnedEmail.toString().indexOf("@");
                maxLength = maxLength <= 14 ? maxLength : 14;
                dwr.util.setValue("returnAgent" + id, issueReturnedEmail.toString().substring(0,maxLength));
            }
            $("pattern" + id).style.display = "";
            peopleCache[id] = person;
        }
    }

}

    