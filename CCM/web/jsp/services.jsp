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
            .leftCol {
                width:70px;
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
            <div style="width:760px" >
                <div style="font-size:12px" align="right">
                    <a href="#addServiceDiv"  rel="facebox" id="addService" class="white">Add service</a>
                </div>
                <table  id="services" cellspacing="1" class="tablesorter">
                    <thead>
                        <tr>
                            <th> Service </th>
                            <th> Unit Price </th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="peoplebody">
                        <tr id="pattern" style="display:none;">
                            <td><span id="name1"></span></td>
                            <td><span id="unitPrice1"></span></td>
                            <td>
                                <a href="#editServiceDiv"  rel="facebox"  class="editService" id="edit"> Edit </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div style="display:none" id="addServiceDiv"  align="center">
                    <div><h2>Add Service</h2></div>
                    <table>
                        <tr>
                            <td class="leftCol"> <label>Name</label> </td>
                            <td><input type="text" class="name" size="25" />  &nbsp; <label>( eg Color Print )</label></td>
                        </tr>
                        <tr><td></td><td>&nbsp;</td></tr>
                        <tr>
                            <td class="leftCol"> <label>Price</label> </td>
                            <td><input type="text" size="10" class="price"/>&nbsp; <label>/ 1 unit</label>  &nbsp; <label>( eg 5.00 )</label></td>
                        </tr>
                        <tr><td></td><td>&nbsp;</td></tr>
                        <tr><td colspan="2"><label>Optional (eg 10 or more prints will cost 4.50 / print)</label></td></tr>
                        <tr>
                            <td class=""> <label>Min. Units</label> </td>
                            <td><input class="saleTwoUnits" type="text" size="10" class=""/>&nbsp; <label>Price</label> &nbsp;<input class="saleTwoPrice" type="text" size="10" class=""/>&nbsp;<label>/ 1 unit</label></td>
                        </tr>
                        <tr><td></td><td>&nbsp;</td></tr>
                        <tr>
                            <td></td><td><input type="button" value="Create" class="saveBtn" /></td>
                        </tr>
                    </table>
                </div>

                <div style="display:none" id="editServiceDiv" align="center">
                    <div><h2>Update Service</h2></div>
                    <table>
                        <tr>
                            <td class="leftCol"> <label>Name</label> </td>
                            <td><input type="text" class="name_ ischanged" size="25" />
                            </td>
                        </tr>
                        <tr><td></td><td>&nbsp;</td></tr>
                        <tr>
                            <td class="leftCol"> <label>Price</label> </td>
                            <td><input type="text" class="price_ ischanged"  size="10" />&nbsp; <label>/ 1 unit</label> </td>
                        </tr>
                        <tr><td></td><td>&nbsp;</td></tr>
                        <tr><td colspan="2"><label>Optional (eg 10 or more prints will cost 4.50 / print)</label></td></tr>
                        <tr>
                            <td class=""> <label>Min. Units</label> </td>
                            <td><input class="saleTwoUnits_ ischanged" type="text" size="10" class=""/>&nbsp; <label>Price</label> &nbsp;<input class="saleTwoPrice_ ischanged" type="text" size="10" class=""/>&nbsp;<label>/ 1 unit</label></td>
                        </tr>
                        <tr><td></td><td>&nbsp;</td></tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <input type="button" value="Save" class="saveBtn" />
                                <input type="button" value="Delete" id="deleteBtn"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <div style="display:none"> <a href="#editComputerDiv"  rel="facebox"  class="" id="editComputer"> computer </a> </div>
                <div style="display:none" id="editComputerDiv" align="center">
                    <div><h2>Computer Billing Rate</h2></div>
                    <table>                        
                        <tr><td></td><td>&nbsp;</td><td></td><td></td></tr>
                        <tr><td></td><td colspan="3"><label>(eg Rs 10 for 30 mins)</label></td></tr>
                        <tr>
                            <td class="leftCol"> <label>Rate</label> </td>
                            <td><input type="text" class="compFirstPrice ischanged"  size="10" />
                            <td><label> for first </label> </td>
                            <td><input type="text" class="compFirstMins ischanged"  size="10" /> <label>&nbsp; Mins</label> </td>
                        </tr>
                        <tr><td></td><td>&nbsp;</td><td></td><td></td></tr>
                        <tr><td></td><td colspan="3"><label>Optional (eg Rs 15 for 60 mins)</label></td></tr>
                        <tr>
                            <td class=""> <label>Rate</label> </td>
                            <td><input type="text" class="compSecondPrice ischanged"  size="10" />
                            <td><label> for Next </label> </td>
                            <td><input type="text" class="compSecondMins ischanged"  size="10" /> <label>&nbsp; Mins</label> </td>
                        </tr>
                        <tr><td></td><td>&nbsp;</td><td></td><td></td></tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <input type="button" value="Save" class="saveCompBtn" />
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <script src="../js/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/interface/AjaxWorkService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/services.js"></script>

    </body>

</html>
