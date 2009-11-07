jq(document).ready(function() {
    var reply1 = function(data) {
        var str1= "Total Minutes & Sale Units : " + data[0][0] ;
        var str2 = "Total Payable Amount : " + data[0][1];
        var str3 = "Total Amount Received : " + data[0][2];
        dwr.util.setValue("id1", str1);
        dwr.util.setValue("id2", str2);
        dwr.util.setValue("id3", str3);
    }
    jq('#reportBtn').click(function() {
        execute();
    })
    getTodaysHistory();
    function getTodaysHistory() {
        var now = new Date();
        var date = now.getFullYear() + "-" + (now.getMonth()+1) + "-" + now.getDate();
        jq('#DPC_startDate_YYYY-MM-DD').val(date);
        jq('#DPC_endDate_YYYY-MM-DD').val(date);
        jq('#reportBtn').click();
    }    
    function execute() {
        dwr.util.useLoadingMessage("Please Wait!");
        var startDate = document.getElementById("DPC_startDate_YYYY-MM-DD").value;
        var endDate = document.getElementById("DPC_endDate_YYYY-MM-DD").value;
        if ( startDate != null && startDate.length >= 9 && endDate != null && endDate.length >= 9 ) {
            AjaxAdminService.getReport(startDate,endDate, reply1 );
        } else {
            alert ( " invalid dates ");
        }
    }
   
})