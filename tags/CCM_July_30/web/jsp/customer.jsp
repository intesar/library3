<%-- 
    Document   : Customer
    Created on : Aug 4, 2008, 9:28:52 AM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Customer</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type='text/javascript' src='../dwr/interface/AjaxWorkService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <script type="text/javascript" src="../js/email_validation.js"></script>
        <script type="text/javascript" src="../js/name_valadation.js"></script>
        <script type="text/javascript" src="../js/datepickercontrol.js"></script>
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/facebox.js" ></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>        

        <link type="text/css" rel="stylesheet" href="../css/datepickercontrol.css">
        <link rel="stylesheet" type="text/css" href="../css/facebox/facebox.css" media="screen" />
        <script type="text/javascript" src="../js/customer.js"> </script>

    </head>
    <body style="background-image:url('../images/body_background.png'); font-family:arial">
        <div align="center">
            <jsp:include page="include.jsp" />
        </div>
        <div align="center">
                <table>
                    <tr><td>
                            <table align="center" width="300px">
                                <thead>
                                    <tr>
                                        <th>
                                            Customer Look Up
                                        </th>
                                        <th></th><th></th>
                                    </tr>
                                </thead>
                                <tr>
                                    <td>
                                        <input type="text" name="key" id="key" value="" size="30"/>
                                    </td>
                                    <td><input type="button" value="Search" id="searchBtn"/></td>
                                    <td><a href="javascript:void(0)" id="newBtn" >New user</a></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><img id="image" src="javascript:void(0);"/>  </td>
                                </tr>
                            </table>
                            <br>
                            <table align="center">
                                <thead>
                                    <tr>
                                        <td>Create / Update Customer Profile</td>
                                    </tr>
                                </thead>
                                <tr>
                                    <td>
                                        <table>
                                            <tr>
                                                <td>Image:(jpg, bmp, png)</td>
                                                <td><input type="file" id="img" /></td>
                                                <!-- <td id="image.container">&nbsp;</td>-->
                                            </tr>
                                            <tr>
                                                <td>Name:&nbsp;&nbsp;*</td>
                                                <td><input id="name" type="text" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>Email:&nbsp;&nbsp;*</td>
                                                <td><input id="email" type="text" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>Street:&nbsp;&nbsp;*</td>
                                                <td><input id="street" type="text" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>City:&nbsp;&nbsp;*</td>
                                                <td><input id="city" type="text" size="20" value="hyderabad"/></td>
                                            </tr>
                                            <tr>
                                                <td>State:</td>
                                                <td><input id="state" type="text" size="20" value="ap"/></td>
                                            </tr>
                                            <tr>
                                                <td>Zipcode:&nbsp;&nbsp;*</td>
                                                <td><input id="zipcode" type="text" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>Country:&nbsp;&nbsp;*</td>
                                                <td><input type="text" id="country"  value="India" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>Date Of Birth</td>
                                                <td> <input type="hidden" id="DPC_TODAY_TEXT" value="today">
                                                    <input type="hidden" id="DPC_BUTTON_TITLE" value="Open calendar...">
                                                    <input type="hidden" id="DPC_MONTH_NAMES" value="['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']">
                                                    <input type="hidden" id="DPC_DAY_NAMES" value="['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']">
                                                    <input type="text" name="startDate" id="DPC_startDate_YYYY-MM-DD">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Gender</td>
                                                <td>
                                                    <select name="Gender">
                                                        <option>Male</option>
                                                        <option>Female</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Home Phone:</td>
                                                <td><input id="homePhone" type="text" size="20"/></td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td>
                                        <table>
                                            <tr>
                                                <td>Verified</td>
                                                <td>
                                                    <select name="isVerified" disabled>
                                                        <option value="0">NO</option>
                                                        <option value="1">YES</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Government Id</td>
                                                <td><input id="ssn" type="text" size="20"/></td>
                                            </tr>

                                            <tr>
                                                <td>Passport No</td>
                                                <td><input id="passportNo" type="text" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>Voter Id</td>
                                                <td><input id="voterId" type="text" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>College Name</td>
                                                <td><input id="collegeName" type="text" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>Ration Card No</td>
                                                <td><input id="rationCardNo" type="text" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>Pan Card No</td>
                                                <td><input id="panCardNo" type="text" size="20"/></td>
                                            </tr>
                                            <tr>
                                                <td>Comments</td>
                                                <td><textarea name="comments" rows="6" cols="25">
                                                    </textarea></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="hidden" name="id" value="" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" align="right">
                                                    <input type="button" value="Save" id="saveBtn"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                </table>
            
        </div>
    </body>

    <jsp:include page="customer_help.jsp" />
</html>
