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
        <link rel="stylesheet" href="../css/datepicker.css" type="text/css" />
        <script type="text/javascript" src="../js/datepicker.js"></script>
        <script type="text/javascript" src="../js/report.js"></script>
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
