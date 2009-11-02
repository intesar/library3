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
        <title>Services</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <script src="../js/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type="text/javascript" src="../js/email_validation.js"></script>
        <script type="text/javascript" src="../js/name_valadation.js"></script>
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/interface/AjaxWorkService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/services.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />
    </head>
    <body  style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include.jsp" />
            <div style="width:700px" >
                <div style="font-size:12px" align="right">
                    <a href="#managerProfile"  rel="facebox" id="createNewManager">Add new service</a>
                </div>
                <table  id="services" cellspacing="1" class="tablesorter">
                    <thead>
                        <tr>
                            <th> Service/Product </th>
                            <th> Unit Price </th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="peoplebody">
                        <tr id="pattern" style="display:none;">
                            <td><span id="name1"></span></td>
                            <td><span id="unitPrice1"></span></td>
                            <td>
                                <a href="#addServiceDiv"  rel="facebox"  class="editService" id="edit"> edit </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div style="display:none" id="addServiceDiv">
                    <table>
                        <thead>
                            <tr>
                                <th></th>
                                <th>
                                    Add / Udpate Services
                                </th>
                            </tr>
                        </thead>
                        <tr>
                            <td> Service/Product:* </td>
                            <td><input id="name" type="text" disabled size="25"/>
                                <br> (eg: printer, dvd burn, potato chips..)
                            </td>
                        </tr>
                        <tr>
                            <td> Unit Price:* </td>
                            <td><input id="unitPrice" type="text" size="5" onKeyup="isInteger(this.value);"/>
                                <br> (eg: 2, 5.5 etc)
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <button value="New" onclick="clearPerson()" >New</button>
                                <button value="Save" id="save" onclick="writePerson()" >Save</button>
                                <button value="Delete" onclick="deletePerson()" >Delete</button>
                            </td>
                        </tr>

                    </table>
                </div>
            </div>

            <br>
            <br>
            <br>
            <jsp:include page="emails_help.jsp" />
            <jsp:include page="copyright.jsp" />

        </div>
    </body>

</html>
