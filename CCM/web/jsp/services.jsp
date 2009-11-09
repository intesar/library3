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
            <jsp:include page="include-manage.jsp" />
            <div style="width:760px" >
                <div style="font-size:12px" align="right">
                    <a href="#addServiceDiv"  rel="facebox" id="createNewManager">Add new service</a>
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
                                <a href="#addServiceDiv"  rel="facebox"  class="editService" id="edit"> Edit </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div style="display:none" id="addServiceDiv">
                    <table>

                        <tr>
                            <td> <label>Name:*</label> </td>
                            <td><input id="name" type="text" class="name" disabled size="25"/>
                            </td>
                        </tr>
                        <tr>
                            <td> <label>Price:*</label> </td>
                            <td><input id="unitPrice" type="text" size="10" class="price" onKeyup="isInteger(this.value);"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <input type="button" value="Save" id="saveBtn" />
                                <input type="button" value="Delete" id="deleteBtn"/>
                            </td>
                        </tr>

                    </table>

                </div>
            </div>
            
            <jsp:include page="emails_help.jsp" />
            <jsp:include page="copyright.jsp" />

        </div>
    </body>

</html>
