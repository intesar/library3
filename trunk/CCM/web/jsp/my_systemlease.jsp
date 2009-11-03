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
        <title>My History</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type='text/javascript' src='../dwr/interface/AjaxCustomerService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <script src="../js/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type="text/javascript" src="../js/datepickercontrol.js"></script>
        <link type="text/css" rel="stylesheet" href="../css/datepickercontrol.css">
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />
        <script type="text/javascript" src="../js/my_systemlease.js"></script>


    </head>
    <body style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include.jsp" />
        </div>
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
            </tr>
            <tr>
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
        <div align="center">

            <div align="center" style="width:760px">
                <table  cellspacing="1" class="tablesorter">
                    <thead>
                        <tr>
                            <th> User </th>
                            <th>System</th>
                            <th>Time</th>
                            <th>End Time</th>
                            <th>Units</th>
                            <th>Payable</th>
                            <th>Paid</th>
                            <th>Issue By  </th>
                            <th>Returned To </th>
                        </tr>
                    </thead>

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

                </table>
            </div>
        </div>
        <jsp:include page="systemlease_help.jsp" />
        <jsp:include page="copyright.jsp" />

    </body>
</html>
