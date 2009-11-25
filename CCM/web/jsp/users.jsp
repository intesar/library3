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
        <title>Managers</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body {
                color:#333333;
                font-family:"Lucida Grande","Lucida Sans Unicode",Arial,Verdana,sans-serif;
                font-size:12px;
                font-style:normal;
                font-variant:normal;
                font-weight:normal;
                line-height:18px;
            }
            .leftRow {
                width:300px;
            }
            .rightRow {
                width:400px;
            }
            .leftCol {
                width:130px;
                color:#000000;
                font-family:Arial,Helvetica,sans-serif;
                font-size:1.2em;
            }
            label {
                font-family:arial,sans-serif;
                font-size:smaller;
                font-weight:bold;
            }
        </style>

        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />

    </head>
    <body style="background-image:url('../images/body_background.png'); font-family:arial">

        <div align="center">
            <jsp:include page="include-manage.jsp" />

            <div style="width:760px" >
                <div style="font-size:12px" align="right">
                    <a href="#managerProfile"  rel="facebox" id="createNewManager">Add manager</a>
                </div>

                <table id="managers" cellspacing="1" class="tablesorter">

                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Active</th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody id="peoplebody">
                        <tr id="pattern" style="display:none;">
                            <td><span id="username1">name</span></td>
                            <td><span id="role1">role</span></td>
                            <td><span id="enabled1">enabled</span></td>
                            <td>
                                <a href="#managerProfile"  rel="facebox"  class="editManager" id="edit"> Edit </a>
                            </td>
                        </tr>
                    </tbody>

                </table>
            </div>

            <div style="display:none" id="managerProfile">
                <table>
                    <thead>
                        <tr>
                            <th></th>
                            <th>

                            </th>
                        </tr>
                    </thead>
                    <tr>
                        <td>  Name:*  </td>
                        <td><input id="name" class="name" type="text" size="30"/></td>
                    </tr>
                    <tr>
                        <td> Email:* </td>
                        <td><input id="username" class="username" type="text" size="30"/></td>
                    </tr>
                    <tr>
                        <td> Password:* </td>
                        <td><input id="password" class="password" type="password" size="30"/></td>
                    </tr>
                    <tr>
                        <td> Active </td>
                        <td><select name="enabledString" class="enabledString">
                                <option>yes</option>
                                <option>no</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>  Role </td>
                        <td><select name="role" class="role">
                                <option value="admin">Administrator</option>
                                <option value="employee" selected>Cashier</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="button" value="Save" id="saveManager" />
                        </td>
                    </tr>
                </table>
            </div>
        </div>

    </body>

    <jsp:include page="users_help.jsp" />
    <jsp:include page="copyright.jsp" />

            <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <script src="../js/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type="text/javascript" src="../js/email_validation.js"></script>
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/users.js"></script>
   
</html>
