<%-- 
    Document   : registration
    Created on : Jul 7, 2008, 1:36:50 PM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxUserService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>
        <script type="text/javascript" src="email_validation.js"></script>
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            var email = "";
            function createAccount() {
                var c = dwr.util.getValue("companyName");
                var u = dwr.util.getValue("email");
                var p = dwr.util.getValue("password");
                var cp = dwr.util.getValue("confirmPassword");
                var minutes = dwr.util.getValue("minutes");
                var rate = dwr.util.getValue("rate");
                var maxSystems = dwr.util.getValue("maxSystems");
                //alert ( c + u + p + cp);
                if ( p == cp) {
                    if ( validateEmail(u, true, true) ) {
                        email = u;
                        AjaxUserService.registerNewOrganization ( c, "hyd", u, p, minutes, rate, maxSystems, reply1);
                    }
                } else {
                    alert ( " Password & ConfirmPassword donot match");
                }
        
                
                //dwr.engine.endBatch();
                 
               
                    
            } 
                          
            
            
            var reply1 = function ( data ) {
                alert ( data );
                if ( data == 'Please login with your email and password') {                        
                    location.href="/login.jsp?j_username="+ email;
                } else {                    
                    
                }
                
            }
            
        </script>
        
        <!-- table style -->
        <style type="text/css">
            body
            {
                font: .8em "Lucida Grande", Tahoma, Arial, Helvetica, sans-serif;
            }
            
            ol
            {
                margin:0;
                padding: 0 1.5em;
            }
            
            table
            {
                color:#FFF;
                background:#C00;
                border-collapse:collapse;
                width:647px;
                border:5px solid #900;
            }
            
            thead
            {
                
            }
            
            thead th
            {
                padding:1em 1em .5em;
                border-bottom:1px dotted #FFF;
                font-size:120%;
                text-align:left;
            }
            
            
            
            thead tr
            {
                
            }
            
            td
            {
                padding:.5em 1em;
            }
            
            
            
            tbody tr.odd td
            {
                background:transparent url(tr_bg.png) repeat top left;
            }
            
            tfoot
            {
                
            }
            
            tfoot td
            {
                
                padding-bottom:1.5em;
            }
            
            tfoot tr
            {
                
            }
            
            
            * html tr.odd td
            {
                background:#C00;
                filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='tr_bg.png', sizingMethod='scale');
            }
            
            
            #middle
            {
                background-color:#900;
            }
            
            
            
        </style>
    </head>
    
    
    
    
    
    
    
    
    
    
    
    <body>
        
        
        <table align="center" bordercolor="#EDDA74" border="2" rules="none" width="80%">
            <tr>
                <td>
                    <h2>
                        <p align="center">CyberCafeManager</p>
                    </h2>
                </td>    
            </tr>
        </table> 
        
        
        <br>
        
        <p align="left"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Register Your CyberCafe</font></p> 
        <font size="6"><a href="login.jsp"> Sign in</a></font>
        <table border="2" width="80%" align="center" rules="none" bordercolor="#EDDA74">
            
            
            <tr>
                <td> <table border="0" width="50%" >
                        
                        <tr>
                            <td>Company Name</td>
                            <td><input type="text" name="companyName" value="" size="30"/></td>
                        </tr>
                        <tr>
                            <td>Username/Email</td>
                            <td><input type="text" name="email" value="" size="30" /></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name="password" value="" size="30" /></td>
                        </tr>
                        <tr>
                            <td>Confirm Password</td>
                            <td><input type="password" name="confirmPassword" value="" size="30" /></td>
                        </tr>
                        <tr>
                            <td>Minimum Minute Rate</td>
                            <td><select name="minutes">
                                    <option value="15">Minimum 15 Minutes</option>
                                    <option value="30">Minimum 30 Minutes</option>
                                    <option value="60">Minimum 60 Minutes</option>
                                </select>
                                <select name="rate">
                                    <option value="1">Rs 1</option>
                                    <option value="2">Rs 2</option>
                                    <option value="3">Rs 3</option>
                                    <option value="4">Rs 4</option>
                                    <option value="5">Rs 5</option>
                                    <option value="6">Rs 6</option>
                                    <option value="7">Rs 7</option>
                                    <option value="8">Rs 8</option>
                                    <option value="9">Rs 9</option>
                                    <option value="10">Rs 10</option>
                                    <option value="11">Rs 11</option>
                                    <option value="12">Rs 12</option>
                                    <option value="13">Rs 13</option>
                                    <option value="14">Rs 14</option>
                                    <option value="15">Rs 15</option>
                                    <option value="16">Rs 16</option>
                                    <option value="17">Rs 17</option>
                                    <option value="18">Rs 18</option>
                                    <option value="19">Rs 19</option>
                                    <option value="20">Rs 20</option>
                                    <option value="21">Rs 21</option>
                                    <option value="22">Rs 22</option>
                                    <option value="23">Rs 23</option>
                                    <option value="24">Rs 24</option>
                                    <option value="25">Rs 25</option>
                                    <option value="26">Rs 26</option>
                                    <option value="27">Rs 27</option>
                                    <option value="28">Rs 28</option>
                                    <option value="29">Rs 29</option>
                                    <option value="30">Rs 30</option>
                            </select></td>
                        </tr>
                        <tr>
                        <td>Max Computers:</td>
                        <td><input type="text" id="maxSystems" /></td>
                        <tr>
                            <td></td>
                            <td><input type="reset" value="Clear" />
                            <input type="submit" value="Create" onclick="createAccount();"/></td>
                        </tr>
                        
                </table></td>
                <td width="50%" >
                    
                    <font size="2" face="verdena">
                        <br>
                        BizIntelApps product, Online CyberCafeManager provides a new kind of security to internet cafes, built on the idea that CyberCafe owners can increase their Revenue, manage their CyberCafes from any where in India, become stress free of the transactions in their CyberCafe. <br><br>
                        
                        BizIntelApps product, Online CyberCafeManager provides the features that makes you feel that you are in your CyberCafe.<br><br>
                        
                        BizIntelApps product, Online CyberCafeManager sends you Email and SMS off all the transactions that are happenning in your CyberCafe.<br><br>       
                        
                    </font>
                </td>
            </tr>
            <tr>
                <td> </td>
                
            </tr>
            
        </table>
        <br>
        <br>
        <br> <br>
        <br>
        <br> <br>
        <br>
        
        <p align="center">
            <font size="2"> &copy; Copyrights BizIntelApps 2008 All Rights Reserved. <a href="http://www.bizintelapps.com/"><font color="blue">BizIntelApps</font></a> </font>
        </p>
        
    </body>
</html>
