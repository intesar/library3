<%-- 
    Document   : systemlease
    Created on : Jul 7, 2008, 5:44:17 AM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <style>
            a:link    {color:black; text-decoration:none; font-size:11pt}
            a:hover   {color:black; text-decoration:none; 
                font-size:11pt}
            a:active  {color:black; text-decoration:none; font-size:11pt}
            a:visited {color:black; text-decoration:none; font-size:11pt}
        </style>
        <style>
            a:link    {color:black; text-decoration:none; font-size:11pt}
            a:hover   {color:black; text-decoration:none; 
                font-size:11pt}
            a:active  {color:black; text-decoration:none; font-size:11pt}
            a:visited {color:black; text-decoration:none; font-size:11pt}
        </style>
        
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>        
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        
        <script type="text/javascript" src="/CCM/datepickercontrol.js"></script>
        <link type="text/css" rel="stylesheet" href="/CCM/datepickercontrol.css"> 
        
        <script type="text/javascript">
            function execute() {
                dwr.util.useLoadingMessage("Please wait, Loading");
                var startDate = document.getElementById("DPC_startDate_YYYY-MM-DD").value;
                var endDate = document.getElementById("DPC_endDate_YYYY-MM-DD").value;
                if ( startDate != null && startDate.length == 10 && endDate != null && endDate.length == 10 ) {
                    AjaxAdminService.getSystemLease(startDate,endDate, reply1 );
                } else {
                    alert ( " invalid dates ");
                }
            }
            
            
            var peopleCache = { };
            var viewed = null;
        
            var reply1 = function(people) {
                // Delete all the rows except for the "pattern" row
                dwr.util.removeAllRows("peoplebody", { filter:function(tr) {
                        return (tr.id != "pattern");
                    }});
                // Create a new set cloned from the pattern row
                var person, id;
                if ( people.lenght == 0 ) {
                    writeMessage ("failureReply", "No Records found!")   ;
                } else {
                    //people.sort(function(p1, p2) { return p1.macAddress.localeCompare(p2.macAddress); });
                    for (var i = 0; i < people.length; i++) {
                        person = people[i];
                        id = person.id;
                        dwr.util.cloneNode("pattern", { idSuffix:id });
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
        </script>
        
        
        <jsp:include page="table_style.jsp" ></jsp:include>
    </head>
    <body>
        <jsp:include page="include.jsp" />
        
      
        
        <table align="center">
            
            <tr>
                <td>
                    
                    <input type="hidden" id="DPC_TODAY_TEXT" value="today">
                    <input type="hidden" id="DPC_BUTTON_TITLE" value="Open calendar...">
                    <input type="hidden" id="DPC_MONTH_NAMES" value="['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']">
                    <input type="hidden" id="DPC_DAY_NAMES" value="['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']">
                    
                    Start Date    
                </td>
                <td>
                    End Date
                </td>
            </tr><tr>
                <td>
                    <input type="text" name="startDate" id="DPC_startDate_YYYY-MM-DD" readonly> 
                </td>
                <td>
                    <input type="text" name="endDate" id="DPC_endDate_YYYY-MM-DD" readonly > 
                </td>
                <td>
                    <input type="submit" value="Search" onclick="execute();"/>
                </td>
            </tr>
        </table>
        
        <br>
        
        <div align="center" >
            <table align="center">
                <thead>
                    <tr>
                        <th> Customer </th>
                        <th> System  </th>
                        <th>  Start Time </th>
                        <th> End Time </th>
                        <th>  Mins/Units</th>
                        <th> Payable Amount </th>
                        <th> Paid Amount </th>
                        <th>  Issue By  </th>
                        <th> Returned To </th>                    
                    </tr>
                </thead>
                <form>
                    <tbody id="peoplebody">
                        <tr id="pattern" style="display:none;">
                            <td><span id="leaseHolderName">Username</span></td>
                            <td><span id="system">System</span></td>
                            <td><span id="startTimeString">Start Time</span></td>
                            <td><span id="endTimeString">End Time</span></td>
                            <td><span id="totalMinutesUsed">Total Minutes</span></td>
                            <td><span id="payableAmount">Payable Amount</span></td>
                            <td><span id="amountPaid">Paid Amount</span></td>
                            <td><span id="issueAgent">Issue By</span></td>
                            <td><span id="returnAgent">Returned To</span></td>
                            
                        </tr>
                    </tbody>
                </form>
            </table>
        </div>
        
        <jsp:include page="systemlease_help.jsp" />
        <jsp:include page="copyright.jsp" />
        
    </body>
</html>
