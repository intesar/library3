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
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />
        <style type="">
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
            .leftCol1 {
                width:200px;
            }
            .leftCol2 {
                width:100px;
            }
            .leftCol {
                width:90px;
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
    <body  style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include-manage.jsp" />
            <table style="width:760px;background-color:white" align="center">
                <tr>
                    <td>
                        <div>
                            <a href="#addEmailDiv"  rel="facebox" id="addEmail">Add email</a> &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="#addPhoneDiv"  rel="facebox" id="addPhone">Add phone</a>
                        </div>
                    </td>
                    <td>
                        <div>
                            <a href="#addTimeDiv" rel="facebox"> Add Time </a>
                        </div>
                    </td>
                </tr>
                <tr style="vertical-align: top">
                    <td>
                        <div>
                            <div>

                                <br/>
                                <table id="emails" cellspacing="1" class="">
                                    <tbody id="peoplebody">
                                        <tr id="pattern" style="display:none;">
                                            <td class="leftCol1"><span id="email_or_phone">emailOrPhone</span></td>
                                            <td>
                                                <a href="javascript:void(0);" class="deleteEmail" id="deleteEmail"> X </a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div style="display:none" id="addPhoneDiv">
                                <table>
                                    <tr>
                                        <td  class="leftCol"> <label>Phone</label> </td>
                                        <td><input id="phone" class="phone" type="text" size="30"/></td>
                                    </tr>
                                    <tr><td></td><td>&nbsp;</td></tr>
                                    <tr>
                                        <td  class="leftCol"> <label>Network</label> </td>
                                        <td><select name="serviceProvider" class="serviceProvider">
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
                                    <tr><td></td><td>&nbsp;</td></tr>
                                    <tr>
                                        <td>
                                        </td>
                                        <td>
                                            <input type="button" value="Save" id="savePhoneBtn"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div style="display:none" id="addEmailDiv">
                                <table>
                                    <tr>
                                        <td  class="leftCol"> <label>Email</label> </td>
                                        <td><input id="email" class="email" type="text" size="30"/> </td>
                                    </tr>
                                    <tr><td></td><td>&nbsp;</td></tr>
                                    <tr>
                                        <td>
                                        </td>
                                        <td>
                                            <input type="button" value="Save" id="saveEmailBtn"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </td>   
                    <td>
                        <div>                                                
                            <table id="emailtimings" cellspacing="1" class="">
                                <tbody id="timeBody">
                                    <tr id="tbRow" style="display:none;">
                                        <td class="leftCol2"><span id="reporttime">reportTime</span></td>
                                        <td>
                                            <a href="javascript:void(0)" class="deleteLink" id="delete"> X </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div style="display:none" id="addTimeDiv">
                            <table align="center">
                                <tr>                                    
                                    <td>
                                        <select name="reportTime" class="reportTime">
                                            <option value="-1"> ----- Select Time ----</option>
                                            <option value="0"> Midnight </option>
                                            <option value="100">01:00 AM</option>
                                            <option value="200">02:00 AM</option>
                                            <option value="300">03:00 AM</option>
                                            <option value="400">04:00 AM</option>
                                            <option value="500">05:00 AM</option>
                                            <option value="600">06:00 AM</option>
                                            <option value="700">07:00 AM</option>
                                            <option value="800">08:00 AM</option>
                                            <option value="900">09:00 AM</option>
                                            <option value="1000">10:00 AM</option>
                                            <option value="1100">11:00 AM</option>
                                            <option value="1200">Noon</option>
                                            <option value="1300">01:00 PM</option>
                                            <option value="1400">02:00 PM</option>
                                            <option value="1500">03:00 PM</option>
                                            <option value="1600">04:00 PM</option>
                                            <option value="1700">05:00 PM</option>
                                            <option value="1800">06:00 PM</option>
                                            <option value="1900">07:00 PM</option>
                                            <option value="2000">08:00 PM</option>
                                            <option value="2100">09:00 PM</option>
                                            <option value="2200">10:00 PM</option>
                                            <option value="2300">11:00 PM</option>
                                        </select>
                                    </td>
                                    <td>&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <input type="button" value="Add" id="addBtn"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
       
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <script type="text/javascript" src="../js/facebox.js" ></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type="text/javascript" src="../js/email_validation.js"></script>
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/emails.js"></script>
        <script type="text/javascript" src="../js/emailtimings.js"></script>
    </body>
</html>
