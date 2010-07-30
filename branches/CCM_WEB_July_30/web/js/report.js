jq(document).ready(function() {
    var reply1 = function(data) {
        var str1= "Total Minutes & Sale Units : " + data[0][0] ;
        var str2 = "Total Payable Amount : " + data[0][1];
        var str3 = "Total Amount Received : " + data[0][2];
        dwr.util.setValue("id1", str1);
        dwr.util.setValue("id2", str2);
        dwr.util.setValue("id3", str3);
    }
    jq('#searchBtn').click(function() {
        execute();
    })

    jq('#inputDate').DatePicker({
        date:String,
        format:'Y-m-d',
        mode:'range',
        calendars:1,
        date: jq('#inputDate').val(),
        //current: jq('#inputDate').val(),
        starts: 1,
        position: 'r',
        //        onBeforeShow: function(){
        //            jq('#inputDate').DatePickerSetDate(jq('#inputDate').val(), true);
        //        },
        onChange: function(formated, dates){
            jq('#inputDate').val(formated);
        //$('#inputDate').DatePickerHide();
        }
    });
    jq('#searchBtn').click(function() {
        var x = jq('#inputDate').DatePickerGetDate(true);
        jq('#inputDate').val(x);
        var dt = x.toString().split(",");
        AjaxAdminService.getReport(dt[0],dt[1], reply1 );
    });
    
    getTodaysHistory();
    function getTodaysHistory() {
        var now = new Date();
        var date = now.getFullYear() + "-" + (now.getMonth()+1) + "-" + now.getDate();
        jq('#inputDate').val(date+","+date);
        AjaxAdminService.getReport(date,date, reply1 );
    }    
    function execute() {
        dwr.util.useLoadingMessage("Please Wait!");
        var x = jq('#inputDate').DatePickerGetDate(true);
        jq('#inputDate').val(x);
        var dt = x.toString().split(",");
        AjaxAdminService.getReport(dt[0],dt[1], reply1 );
    }
   
})