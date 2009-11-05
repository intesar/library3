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
        <title>Members</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../js/email_validation.js"></script>
        <script type="text/javascript" src="../js/name_valadation.js"></script>
        <script type='text/javascript' src='../dwr/interface/AjaxWorkService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>        
        <script type="text/javascript" src="../js/facebox.js" ></script>
        <script type="text/javascript" src="../js/jquery.jqtransform.js"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type="text/javascript" src="../js/memberships.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/jqtransform.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />
    </head>
    <body style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include.jsp" />
        </div>
        <div style="font-size:12px" align="center">
            <a href="#addMemberDiv"  rel="facebox" id="createNewManager">Add new member</a>
        </div>
        <table align="center">

            <thead>
                <tr>
                    <th> User  </th>
                    <th> Membership </th>
                    <th></th>
                </tr>
            </thead>

            <tbody id="peoplebody">
                <tr id="pattern" style="display:none;">
                    <td><span id="email1"></span></td>
                    <td><span id="membershipType1"></span></td>
                    <td>
                        <input id="edit" type="button" value="Edit" onclick="editClicked(this.id)"/>
                    </td>
                </tr>
            </tbody>

        </table>
        <div style="display:none" id="addMemberDiv">
            <form  class="jqtransform" >
                <table>
                    <tr>
                        <td> <label>Username:*</label> </td>
                        <td><input id="email" type="text" disabled size="25"/></td>
                    </tr>
                    <tr>
                        <td> <label>Membership:*</label> </td>
                        <td><select id="membershipTypeString" disabled >
                                <option></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> <label>Active:* </label></td>
                        <td><select id="isActive" disabled>
                                <option value="true">yes</option>
                                <option value="false">no</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td> <label>Start Date:* </label> </td>
                        <td><input id="startDateString" type="text" disabled size="25"/></td>
                    </tr>
                    <tr>
                        <td> <label>Expiration:* </label></td>
                        <td><input id="expirationDateString" type="text" disabled size="25"/></td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <input type="button" value="New" onclick="clearPerson()" />
                            <input type="button" value="Save" id="save" onclick="writePerson()" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <jsp:include page="emails_help.jsp" />
        <jsp:include page="copyright.jsp" />
    </body>

</html>

