<%-- 
    Document   : organization
    Created on : Jul 7, 2008, 5:44:10 AM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Company Details</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>        
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/facebox.js" ></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <script type="text/javascript" src="../js/organization.js"></script>
    </head>

    <body style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include-manage.jsp" />
        </div>
        <table align="center">
            <thead>
                <tr>
                    <th>
                        Company Details
                    </th>
                    <th></th>
                </tr>
            </thead>
            <tr>
                <td>
                    <table align="center">

                        <tr>
                            <td>Name:</td>
                            <td><input id="name" type="text" size="20" disabled="disabled" /></td>
                        </tr>
                        <tr>
                            <td>Street:</td>
                            <td><input type="text" id="street" size="20"/></td>
                        </tr>
                        <tr>
                            <td>City:</td>
                            <td><input type="text" id="city" size="20"/></td>
                        </tr>
                        <tr>
                            <td>State:</td>
                            <td><input type="text" id="state" size="20"/></td>
                        </tr>
                        <tr>
                            <td>Zipcode:</td>
                            <td><input type="text" id="zipcode" size="20"/></td>
                        </tr>
                        <tr>
                            <td>Country:</td>
                            <td><input type="text" id="country" size="20"/></td>
                        </tr>
                    </table>

                </td>
                <td>
                    <table>
                        <tr>
                            <td>CEO</td>
                            <td><input type="text" id="contactName" size="20"/></td>
                        </tr>
                        <tr>
                            <td>Office Phone</td>
                            <td><input type="text" id="phone" size="20"/></td>
                        </tr>
                        <tr>
                            <td>Company Email</td>
                            <td><input type="text" id="contactEmail" size="20"/></td>
                        </tr>
                        <tr>
                            <td>Print Email</td>
                            <td>(Used for customer printouts) <br>
                                <input type="text" id="printEmail" size="20"/></td>
                        </tr>
                        <tr>
                            <td>Timings</td>
                            <td>(eg: mon- sun 9am to 1am) <br>
                                <textarea id="timings" cols="30" rows="4"></textarea></td>
                        </tr>
                    </table>
                </td>
            </tr>

            <tr>
                <td >    </td>
                <td>
                    <input type="button" value="Save" id="saveBtn"/>
                </td>
            </tr>
        </table>
        <jsp:include page="organization_help.jsp" />
        <jsp:include page="copyright.jsp" />

    </body>
</html>
