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

            <div style="width:760px" align="center" >
                <div style="font-size:12px" align="right">
                    <a href="#addMemberDiv"  rel="facebox" id="addNewMember">Add new member</a>
                </div>
                <table align="center"  cellspacing="1" class="tablesorter">

                    <thead>
                        <tr>
                            <th> User  </th>
                            <th> Membership </th>
                            <th>Active</th>
                            <th>Expires On</th>
                            
                        </tr>
                    </thead>

                    <tbody id="peoplebody">
                        <tr id="pattern" style="display:none;">
                            <td><span id="email1"></span></td>
                            <td><span id="membershipType1"></span></td>
                            <td><span id="isActive1"></span></td>
                            <td><span id="expirationDateString1"></span></td>
                            <!--
                            <td>
                                <a  href="#addMemberDiv"  rel="facebox"   id="edit" class="editMember" >edit</a>
                            </td>
                            -->
                        </tr>
                    </tbody>

                </table>
                <div style="display:none" id="addMemberDiv">                    
                        <table>
                            <tr>
                                <td> <label>Username:*</label> </td>
                                <td><input id="email" class="email" type="text" size="25"/></td>
                            </tr>
                            <tr>
                                <td> <label>Membership:*</label> </td>
                                <td><select id="membershipTypeString" class="membershipTypeString">
                                        <option></option>
                                    </select>
                                </td>
                            </tr>
                            <!--
                            <tr>
                                <td> <label>Active:* </label></td>
                                <td><select id="isActive" disabled>
                                        <option value="true" selected>yes</option>
                                        <option value="false">no</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td> <label>Start Date:* </label> </td>
                                <td><input id="startDateString" type="text" value="Today" disabled size="25"/></td>
                            </tr>
                            <tr>
                                <td> <label>Expiration:* </label></td>
                                <td><input id="expirationDateString" type="text" value="According to policy" disabled size="25"/></td>
                            </tr>
                            -->
                            <tr>
                                <td>
                                </td>
                                <td>                                    
                                    <input type="button" value="Save" class="saveBtn"/>
                                </td>
                            </tr>
                        </table>                    
                </div>
                <div style="display:none">
                    <a href="#successMessageDiv"  rel="facebox" class="showSuccessMessageDiv"></a>
                </div>
                <div style="display:none" id="successMessageDiv">
                    Membership added successfully!
                </div>
            </div>
        </div>
        <jsp:include page="emails_help.jsp" />
        <jsp:include page="copyright.jsp" />
    </body>

</html>

