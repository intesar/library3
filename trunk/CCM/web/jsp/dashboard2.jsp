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
        </style>
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../css/blue/style.css" media="print, projection, screen" />

    </head>
    <body  style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include.jsp" />
            <div style="width:760px" >
                <div style="font-size:12px" align="right">
                    <a href="#addServiceDiv"  rel="facebox">Add Service</a>
                </div>

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
                                            <td><span id="name"></span></td>
                                            <td><span id="currentUserEmail"></span></td>
                                            <td><span id="startTimeString1"></span></td>
                                            <td><span id="payable1"></span></td>
                                            <td>
                                                <a href="javascript:void(0);" id="edit">Assign</a> &nbsp; &nbsp; <a href="javascript:void(0);" onclick="fetchDetail(this.id);"  id="deta" class="detail">Details</a>
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
            <table   cellspacing="1" class="tablesorter">
                <thead>
                    <tr>
                        <th>
                            Service
                        </th>
                        <th>
                            Time
                        </th>

                        <th>
                            Units
                        </th>
                        <th>
                            Payable
                        </th>
                    </tr>
                </thead>
                <tbody  id="detailbody" >
                    <tr id="detail" >
                        <td><span id="service"></span></td>
                        <td><span id="startTimeString"></span></td>
                        <td><span id="totalMinutesUsed">Total</span></td>
                        <td><span id="payableAmount">
                                <input type="text" id="paidAmount" value="" class="paidAmount" disabled="disabled" size="4" />
                                <button value="Paid" onclick="paid();"  id="paidButton" disabled="disabled">Paid</button> </span> </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div id="addServiceDiv" style="display:none">
            <table title="Extra Sale" cellspacing="1">
                <thead>
                    <tr>
                        <th></th>
                        <th>Extra Sales /</th>
                        <th>Services</th>
                        <th></th>
                    </tr>
                </thead>
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
                    <td><input type="submit" value="Add/Charge" onclick="addService();" /></td>
                </tr>
            </table>
        </div>
        <jsp:include page="copyright.jsp" />

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
