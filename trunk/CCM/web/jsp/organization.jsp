<%-- 
    Document   : organization
    Created on : Jul 7, 3008, 5:44:10 AM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Company Details</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />

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
    </head>

    <body style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include-manage.jsp" />
        </div>
        <div align="center">
            <div style="width:760px; background:white" id="viewDiv" >
                <div>
                    <table>
                        <tr>
                            <td class="leftRow">

                            </td>
                            <td class="rightRow">
                                <table style="width:100%">
                                    <tr><td class="leftCol"><label>Name</label></td><td><span id="name"></span></td>
                                        <td align="right"><a href="#editDiv"  rel="facebox" class="editBtn">Edit this information</a></td></tr>
                                </table>

                            </td>
                        </tr>
                    </table>
                </div>
                <hr/>
                <div>
                    <table>
                        <tr>
                            <td class="leftRow">
                                Cyber Cafe Address
                            </td>
                            <td class="rightRow">
                                <table>
                                    <tr><td class="leftCol"><label>Street </label> </td><td><span  id="street" /> </td></tr>
                                    <tr><td class="leftCol"><label>City </label> </td><td><span  id="city" /></td></tr>
                                    <tr><td class="leftCol"><label>State</label></td><td><span  id="state" /></td></tr>
                                    <tr><td class="leftCol"><label>Zipcode</label></td><td><span id="zipcode" /></td></tr>
                                    <tr><td class="leftCol"><label>Country</label> </td><td><span id="country" /></td></tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
                <hr/>
                <div>
                    <table>
                        <tr>
                            <td class="leftRow">
                                <p>Cyber Cafe Contact Information</p>   
                            </td>
                            <td class="rightRow">
                                <table>
                                    <tr><td class="leftCol"><label>Contact Person</label></td><td><span id="contactName" /></td></tr>
                                    <tr><td class="leftCol"> <label>Phone</label></td><td><span id="phone" /></td></tr>
                                    <tr><td class="leftCol"><label>Email</label></td><td><span id="contactEmail" /></td></tr>
                                    <tr><td class="leftCol"><label>Printer Email</label></td><td><span id="printEmail" /></td></tr>
                                    <tr><td class="leftCol"><label>Daily Timings</label></td><td><span id="timings" /></td></tr>
                                    <tr><td class="leftCol"></td><td></td></tr>
                                </table>
                            </td>
                        </tr>

                    </table>
                </div>
                <hr/>

            </div>

            <div style="width:560px; background:white;display:none"  id="editDiv" align="center">
                <div>
                    <div><h2>Company Profile</h2></div>
                    <table>
                        <tr>
                            <td class="rightRow">
                                <table cellspacing="0" cellpadding="5" border="0">
                                    <tbody>
                                        <tr><td class="leftCol"><label>Street </label> </td><td><input type="text" class="street1 ischanged" size="30"/> </td></tr>
                                        <tr><td class="leftCol"><label>City </label> </td><td><input type="text" class="city1 ischanged" size="30"/></td></tr>
                                        <tr><td class="leftCol"><label>State</label></td><td><input type="text" class="state1 ischanged" size="30"/></td></tr>
                                        <tr><td class="leftCol"><label>Zipcode</label></td><td><input type="text" class="zipcode1 ischanged" size="30"/></td></tr>
                                        <tr><td class="leftCol"><label>Country</label> </td><td><input type="text" class="country1 ischanged" size="30"/></td></tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
                <hr/>
                <div>
                    <table>
                        <tr>
                            <td class="rightRow" >
                                <table  cellspacing="0" cellpadding="0">
                                    <tr><td class="leftCol"><label>Contact Person</label></td><td><input type="text" class="contactName1 ischanged" size="30"/></td></tr>
                                    <tr><td class="leftCol"> <label>Phone</label></td><td><input type="text" class="phone1 ischanged" size="30"/></td></tr>
                                    <tr><td class="leftCol"><label>Email</label></td><td><input type="text" class="contactEmail1 ischanged" size="30"/></td></tr>
                                    <tr><td class="leftCol"><label>Printer Email</label></td><td><input type="text" class="printEmail1 ischanged" size="30"/></td></tr>
                                    <tr><td class="leftCol"><label>Timings</label></td><td><textarea class="timings1 ischanged" cols="25" rows="2"></textarea></td></tr>
                                </table>
                            </td>
                        </tr>

                    </table>
                </div>
                <div>
                    <table>
                        <tr><td class="leftRow"></td><td class="rightRow">
                                <table>
                                    <tr><td class="leftCol"></td><td><input type="button" value="Save" class="saveBtn" /></td></tr>
                                </table></td></tr>
                    </table>
                </div>

            </div>
        </div>

        

        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>        
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/facebox.js" ></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type="text/javascript" src="../js/organization.js"></script>
    </body>
</html>
