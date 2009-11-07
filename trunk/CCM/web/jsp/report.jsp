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
        <title>Report</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script src="../js/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/datepickercontrol.js"></script>
        <link type="text/css" rel="stylesheet" href="../css/datepickercontrol.css">
        <script type="text/javascript" src="../js/report.js"></script>
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

                    Start-Date    
                </td>
                <td>
                    End-Date
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="startDate" id="DPC_startDate_YYYY-MM-DD">
                </td>
                <td>
                    <input type="text" name="endDate" id="DPC_endDate_YYYY-MM-DD">
                </td>
                <td>
                    <input type="button" value="Search" id="reportBtn"/>
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
