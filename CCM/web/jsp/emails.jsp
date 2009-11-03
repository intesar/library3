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
        <title>Emails </title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />
        <script type="text/javascript" src="../js/emails.js"></script>
    </head>
    <body  style="background-image:url('../images/body_background.png'); font-family:arial">

        <div align="center">
            <jsp:include page="include-manage.jsp" />

            <div style="width:760px" >
                <div style="font-size:12px" align="right">
                    <a href="#addEmailDiv"  rel="facebox" id="createNewManager">Add a new email</a>
                </div>

                <table id="emails" cellspacing="1" class="tablesorter">
                    <thead>
                        <tr>
                            <th> Name  </th>
                            <th> Email/Phone </th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody id="peoplebody">
                        <tr id="pattern" style="display:none;">
                            <td><span id="username1">Username</span></td>
                            <td><span id="email_or_phone">emailOrPhone</span></td>
                            <td>
                                <input id="edit" type="button" value="Edit" onclick="editClicked(this.id)"/>
                            </td>
                        </tr>
                    </tbody>

                </table>
            </div>
            <div style="display:none" id="addEmailDiv">
                <table>
                    <thead>
                        <tr>
                            <th>Add / Update </th>
                            <th>
                                Email/Mobile For Daily Reports
                            </th>
                        </tr>
                    </thead>

                    <tr>
                        <td> Service: </td>
                        <td><select name="serviceProvider">
                                <option value="email">email</option>
                                <option value="@airtelap.com">Andhra Pradesh Airtel</option>
                                <option value="@ideacellular.net">Andhra Pradesh Idea</option>
                                <option value="@airtelchennai.com">Chennai Skycell/Airtel</option>
                                <option value="@rpgmail.net">Chennai RPG Cellular</option>
                                <option value="@airtelmail.com">Delhi Airtel</option>
                                <option value="@delhi.hutch.co.in">Delhi Hutch</option>
                                <option value="@ideacellular.net">Gujarat Idea Cellular</option>
                                <option value="@airtelmail.com">Gujarat Airtel</option>
                                <option value="@celforce.com">Gujarat Celforce / Fascel</option>
                                <option value="@airtelmail.com">Goa Airtel </option>
                                <option value="@bplmobile.com">Goa BPL Mobile</option>
                                <option value="@ideacellular.net">Goa Idea Cellular</option>
                                <option value="@airtelmail.com">Haryana Airtel</option>
                                <option value="@escotelmobile.com">Haryana Escotel</option>
                                <option value="@airtelmail.com">Himachal Pradesh Airtel</option>
                                <option value="@airtelkk.com">Karnataka Airtel</option>
                                <option value="@airtelkerala.com">Kerala Airtel</option>
                                <option value="@escotelmobile.com">Kerala Escotel</option>
                                <option value="@bplmobile.com">Kerala BPL Mobile</option>
                                <option value="@airtelkol.com">Kolkata Airtel</option>
                                <option value="@airtelmail.com">Madhya Pradesh Airtel</option>
                                <option value="@airtelmail.com">Maharashtra Airtel </option>
                                <option value="@bplmobile.com">Maharashtra BPL Mobile</option>
                                <option value="@ideacellular.net">Maharashtra Idea Cellular</option>
                                <option value="@airtelmail.com">Mumbai Airtel</option>
                                <option value="@bplmobile.com">Mumbai BPL Mobile</option>
                                <option value="@airtelmail.com">Punjab Airtel</option>
                                <option value="@bplmobile.com">Pondicherry BPL Mobile</option>
                                <option value="@airtelmail.com">Tamil Nadu Airtel</option>
                                <option value="@bplmobile.com">Tamil BPL Mobile</option>
                                <option value="@airsms.com">Tamil Nadu Aircel</option>
                                <option value="@escotelmobile.com">Uttar Pradesh(West) Escotel</option>
                            </select></td>
                    </tr>

                    <tr>
                        <td> Email/Phone:* </td>

                        <td><input id="emailOrPhone" type="text" size="30"/>
                            <br> (abc@yahoo.com, 9849098490)
                        </td>
                    </tr>
                    <tr>
                        <td> Name: </td>
                        <td><input id="username" type="text" size="30"/></td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <input type="button" value="Clear" onclick="clearPerson()"/>
                            <input type="button" value="Save" onclick="writePerson()"/>
                            <input type="button" value="Delete" onclick="deletePerson()"/>
                        </td>
                    </tr>

                </table>
            </div>
        </div>
        <jsp:include page="emails_help.jsp" />
        <jsp:include page="copyright.jsp" />
    </body>

</html>
