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
        <title>Lease</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <script src="../js/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/datepickercontrol.js"></script>
        <link type="text/css" rel="stylesheet" href="../css/datepickercontrol.css">
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />
        <link rel="stylesheet" href="../css/datepicker.css" type="text/css" />
        <script type="text/javascript" src="../js/datepicker.js"></script>
        <script type="text/javascript" src="../js/systemlease.js"></script>
    </head>
    <body style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include.jsp" />
        </div>
        <table align="center">
            <tr>
                <td>
                    <input type="text"  id="inputDate" maxlength="40" size="30" readonly>
                </td>                
                <td>
                    <input type="submit" value="Search" id="searchBtn"/>
                </td>
            </tr>
        </table>
        <div align="center">
            <div align="center" style="width:760px" >
                <table cellspacing="1" class="tablesorter">
                    <thead>
                        <tr>
                            <th> Customer </th>
                            <th> System  </th>
                            <th>  Start Time </th>
                            <th> End Time </th>
                            <th>  Mins/Units</th>
                            <th> Payable </th>
                            <th> Paid </th>
                            <th>  Issue By  </th>
                            <th> Returned To </th>
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
