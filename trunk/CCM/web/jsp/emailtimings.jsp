<%-- 
    Document   : systems
    Created on : Jul 7, 2008, 5:43:08 AM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Email timing</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <script src="../js/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />
        <script type="text/javascript" src="../js/emailtimings.js"></script>


    </head>
    <body  style="background-image:url('../images/body_background.png'); font-family:arial">

        <div align="center">
            <jsp:include page="include.jsp" />

            <div style="width:700px" >
                <div style="font-size:12px" align="right">
                    <a href="#emailTiming"  rel="facebox" id="createNewManager">Add email time</a>
                </div>

                <table id="emailtimings" cellspacing="1" class="tablesorter">
                    <thead>
                        <tr>
                            <th>Time</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="peoplebody">
                        <tr id="pattern" style="display:none;">
                            <td><span id="reporttime">reportTime</span></td>
                            <td>
                                <input id="remove" type="button" value="Delete" onclick="deleteClicked(this.id)"/>
                            </td>
                        </tr>
                    </tbody>
                </table>

            </div>
            <div style="display:none" id="emailTiming">
                <table>
                    <thead>
                        <tr>
                            <th>Daily Report</th>
                            <th>Times</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tr>
                        <td>
                            Report Time
                        </td>
                        <td>
                            <select name="reportTime">
                                <option value="0"> midnight</option>
                                <option value="100">01:00 am</option>
                                <option value="200">02:00 am</option>
                                <option value="300">03:00 am</option>
                                <option value="400">04:00 am</option>
                                <option value="500">05:00 am</option>
                                <option value="600">06:00 am</option>
                                <option value="700">07:00 am</option>
                                <option value="800">08:00 am</option>
                                <option value="900">09:00 am</option>
                                <option value="1000">10:00 am</option>
                                <option value="1100">11:00 am</option>
                                <option value="1200">noon</option>
                                <option value="1300">01:00 pm</option>
                                <option value="1400">02:00 pm</option>
                                <option value="1500">03:00 pm</option>
                                <option value="1600">04:00 pm</option>
                                <option value="1700">05:00 pm</option>
                                <option value="1800">06:00 pm</option>
                                <option value="1900">07:00 pm</option>
                                <option value="2000">08:00 pm</option>
                                <option value="2100">09:00 pm</option>
                                <option value="2200">10:00 pm</option>
                                <option value="2300">11:00 pm</option>
                            </select>
                        </td>

                        <td>
                            <input type="button" value="Add" onclick="writePerson()"/>
                        </td>

                    </tr>
                </table>
            </div>
        </div>

        <jsp:include page="emailtimings_help.jsp" />
        <jsp:include page="copyright.jsp" />

    </body>

</html>
