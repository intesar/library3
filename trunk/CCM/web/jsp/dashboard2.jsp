<%-- 
    Document   : dashboard1
    Created on : Aug 26, 2008, 9:52:43 AM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title> Lease Systems</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            .leftCol {
                width:600px;
                color:#000000;
                font-family:Arial,Helvetica,sans-serif;
                font-size:1.2em;
            }
            .rightCol {
                width:160px;
                color:#000000;
                font-family:Arial,Helvetica,sans-serif;
                font-size:1.2em;
            }
            .links a{
                cursor:pointer;
                text-decoration:none;
                color: green;
            }
            .links a:hover {
                border-bottom:1px dotted #FFFFFF;
                cursor:pointer;
            }
            .links1 a{
                cursor:pointer;
                text-decoration:none;
                color: red;
            }
            .links1 a:hover {
                border-bottom:1px dotted #FFFFFF;
                cursor:pointer;
            }
            
            label {
                width:150px;
                font-family:arial,sans-serif;
                font-weight:bold;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />

    </head>
    <body  style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include.jsp" />
            <div style="width:760px" >

                <div style="display:none">
                    <table>
                        <tr>
                            <td>
                                <input type="text"  id="key" value="Email" size="30" class="cleardefault" />
                                <input type="submit" value="Search Customer" onclick="search();"/>
                                <img id="image" src="javascript:void(0);"/>
                                <br>
                            </td>
                        </tr>
                    </table>
                </div>
                <table style="background-color:white">
                    <tr>
                        <td class="leftCol">
                            <div style="font-size:12px" align="right">
                                <a href="#addServiceDiv"  rel="facebox">Add Service</a>
                            </div>
                            <div>
                                <table  cellspacing="1" class="tablesorter">
                                    <thead>
                                        <tr>
                                            <th>
                                                Sys No
                                            </th>
                                            <th>
                                                Customer
                                            </th>
                                            <th>
                                                Start Time
                                            </th>
                                            <th>
                                                Payable
                                            </th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody  id="peoplebody" >
                                        <tr id="pattern" style="display:none;">
                                            <td class="systemRow" ><span id="name"></span></td>
                                            <td class="systemRow" > <span id="currentUserEmail"></span></td>
                                            <td class="systemRow" ><span id="startTimeString1"></span></td>
                                            <td class="systemRow" ><span id="payable1"></span></td>
                                            <td>
                                                <div class="links" id="assignDiv"><a href="javascript:void(0);" id="aeta" class="assign">Assign</a></div>
                                                <div class="links1" style="display:none" id="detailsDiv"><a href="javascript:void(0);" id="deta" class="detail">Details</a></div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                        <td class="rightCol">
                            <div id="graphDiv"></div>
                            <div id="graphDetailDiv">
                                <table  style='width:95%' class="tablesorter">
                                    <thead>
                                        <tr><th>Service</th><th>Units</th><th>Payable</th><th>Paid</th></tr>
                                    </thead>
                                    <tbody id="serviceBody">                                        
                                    </tbody>
                                    <tfoot id="serviceFoot">
                                    </tfoot>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <div id="detailDiv" style="display:none">
            <table align="center">
                <tr><th>User</th><th><span class="username_" /></th></tr>
                <tr><th>Computer</th><th> # <span class="computerNo_" /></th></tr>
            </table>
            <br/>
            <table style='width:95%' class="tablesorter">
                <thead>
                    <tr><th>Service</th><th>Time</th><th>Units</th><th>Payable</th></tr>
                </thead>
                <tbody  id="detailbody" >
                    <tr id="detail" >
                        <td><span id="service"></span></td>
                        <td><span id="startTimeString"></span></td>
                        <td><span id="totalMinutesUsed"></span></td>
                        <td><span id="payableAmount">
                            </span>
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td></td><td></td><td>Total</td><td><input type="text" disabled class="totalPayableAmount" size="8"/></td>
                    </tr>                    
                </tfoot>
            </table>
            <table>
                <tr>
                    <td>Paid Amount</td><td><input type="text" id="paidAmount" class="paidAmount"/></td>
                </tr>
                <tr>
                    <td>Comments</td><td><textarea id="paymentComment" class="paymentComment" rows="2" cols="30"></textarea> </td>
                </tr>
                <tr>
                    <td></td><td><button value="Paid" id="paidButton" class="paidButton" >Paid</button></td>
                </tr>
            </table>

        </div>

        <div id="assignUserDiv" style="display:none" align="center">
            <div align="center" style="width:100%">
                <table  style="width:100%">
                    <tr><td colspan="2">&nbsp;</td</tr>
                    <tr><td><label>Computer <span style="display:none" class="computerNo" /></label></td><td><label> # <span class="computerName"/> </label></td></tr>
                    <tr><td colspan="2">&nbsp;</td</tr>
                    <tr><td><label>Customer Email</label></td><td><input type="text" class="name_" /></td></tr>                    
                    <tr><td colspan="2">&nbsp;</td</tr>
                    <tr><td></td><td><input type="submit" value="Assign" class="assignToUserBtn" /></td></tr>
                </table>
            </div>
        </div>
        <div id="addServiceDiv" style="display:none" align="center">
            <h2>Extras</h2>
            <table title="Extra Sale" cellspacing="1">                
                <tr>
                    <td>Service</td>
                    <td><select name="services" id="services" onfocus="populateSystemNos();"><option></option>
                        </select></td>
                    <td>Units*</td>
                    <td>
                        <input type=text name="units" value="" size="4" class="cleardefault" onchange="updatePrice();" onKeyup="isInteger(this.value);updatePrice();">
                    </td>
                </tr>
                <tr>
                    <td>Add To</td>
                    <td><select name="systemNos" id="systemNos"><option></option>
                        </select></td>
                    <td>Payable Amount*</td>
                    <td><input type=text name="payableAmount1" value="" size="4" class="cleardefault" onKeyup="isInteger(this.value)" disabled></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><input type="submit" value="Paid" onclick="addService();" /></td>
                </tr>
            </table>
        </div>

        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <script type="text/javascript" src="../js/facebox.js" ></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type='text/javascript' src='../dwr/interface/AjaxWorkService.js'></script>
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/email_validation.js"></script>
        <script type="text/javascript" src="../js/dashboard.js"></script>
        <script type="text/javascript" src="../js/jgcharts.pack.js"></script>        
    </body>
</html>
