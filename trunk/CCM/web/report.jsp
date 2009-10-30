<%-- 
    Document   : report
    Created on : Jul 7, 2008, 1:30:33 PM
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
                dwr.util.useLoadingMessage("Please Wait!");
                 var startDate = document.getElementById("DPC_startDate_YYYY-MM-DD").value;
                 var endDate = document.getElementById("DPC_endDate_YYYY-MM-DD").value;
                if ( startDate != null && startDate.length == 10 && endDate != null && endDate.length == 10 ) {
                    AjaxAdminService.getReport(startDate,endDate, reply1 );
                } else {
                    alert ( " invalid dates ");
                }
            }
            
            var reply1 = function(data) {
                var str1= "Total Minutes & Sale Units : " + data[0][0] ;
                var str2 = "Total Payable Amount : " + data[0][1];
                var str3 = "Total Amount Received : " + data[0][2];
                dwr.util.setValue("id1", str1);
                dwr.util.setValue("id2", str2);
                dwr.util.setValue("id3", str3);
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
                    
                    Start-Date    
                </td>
                <td>
                    End-Date
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="startDate" id="DPC_startDate_YYYY-MM-DD" readonly> 
                </td>
                <td>
                    <input type="text" name="endDate" id="DPC_endDate_YYYY-MM-DD" readonly> 
                </td>
                <td>
                    <input type="submit" value="Search" onclick="execute();"/>
                </td>
            </tr>
        </table>
        
        <br>
        
        <br>
        
        <div align="center">
            <table>
                <tr>
                    <td><span id="id1"></span><br></td>
                    <td><span id="id2"></span><br></td>
                    <td><span id="id3"></span><br></td>
                </tr>
            </table>
        </div>
        
        
        
        
        <jsp:include page="report_help.jsp" />      
        <jsp:include page="copyright.jsp" />
    </body>
</html>
