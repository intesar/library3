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
        <title>Create Update Mebership policies</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="email_validation.js"></script>
        <script type="text/javascript" src="name_valadation.js"></script>
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <script src="../js/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxWorkService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>        
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        <script type="text/javascript" src="../js/membership_types.js"></script>
    </head>
    <body  style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include-manage.jsp" />


            <div style="width:760px">
                <div style="font-size:12px" align="center">
                    <a href="#addMembershipDiv"  rel="facebox" id="addMembership">Add new membership</a>
                </div>

                <div align="center" >
                    <table  id="memberships" cellspacing="1" class="tablesorter">
                        <thead>
                            <tr>
                                <th> Membership Name  </th>
                                <th> Fee </th>
                                <th> Days </th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody id="peoplebody">
                            <tr id="pattern" style="display:none;">
                                <td><span id="name1"></span></td>
                                <td><span id="fee1"></span></td>
                                <td><span id="days1"> </span></td>
                                <td>
                                    <a href="#addMembershipDiv"  rel="facebox"  class="editMembership" id="edit"> Edit </a>
                                </td>
                            </tr>
                        </tbody>

                    </table>
                    <table>
                        <thead>
                            <tr>
                                <th>Service</th>
                                <th>
                                    Discount %
                                </th>
                            </tr>
                        </thead>
                        <tbody id="discountBody">
                            <tr id="discountRow" style="display:none;">
                                <td><span id="service"></span></td>
                                <td><input type="text" id="discountPercentage" value="" size="3" onkeyup="IsInteger(this.value);"/></td>
                                <td>
                                    <input id="save" type="button" value="Save" onclick="saveDiscount(this.id)"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
                <div style="display:none" id="addMembershipDiv">
                    <table>
                        <tr>
                            <td> Name:* </td>
                            <td><input id="name" class="name" type="text" disabled size="25"/></td>
                        </tr>
                        <tr>
                            <td> Fee:* </td>
                            <td><input id="fee" class="fee" type="text" size="5" onKeyup="isInteger(this.value);"/></td>
                        </tr>
                        <tr>
                            <td>Days:*</td>
                            <td><input id="daysValidFor" class="daysValidFor" type="text" size="3" onkeyup="IsInteger(this.value);"> </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <input type="button" value="Save" id="saveBtn" />
                            </td>
                        </tr>
                    </table>

                </div>
            </div>
        </div>
        <jsp:include page="emails_help.jsp" />
        <jsp:include page="copyright.jsp" />
    </body>

</html>
