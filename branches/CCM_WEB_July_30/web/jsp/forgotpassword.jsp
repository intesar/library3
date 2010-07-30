<%-- 
    Document   : forgotpassword
    Created on : Jul 7, 2008, 1:37:24 PM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<html>
    <head>
        <title>FaceGuard</title>
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

            label {
                font-family:arial,sans-serif;                
                font-weight:bold;
            }
        </style>


        <script type='text/javascript' src='../dwr/interface/AjaxUserService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type="text/javascript" src="../js/email_validation.js"></script>
        <script type='text/javascript' src='../dwr/util.js'></script>

        <script type="text/javascript"> 
            
            function recoverPassword() {
                dwr.util.useLoadingMessage("Please Wait, Loading...");
                var email = null;
                email = dwr.util.getValue("email");
                if ( validateEmail(email, true, true) ) {
                    AjaxUserService.forgotPassword(email, function(data) {
                        dwr.util.setValue("email1", dwr.util.getValue("email"));
                        alert ( data ) ;
                    });
                }
            }
            function resetPassword() {
                dwr.util.useLoadingMessage("Please Wait, Loading...");
                var e = dwr.util.getValue("email1");
                var a = dwr.util.getValue("activationCode");
                var p = dwr.util.getValue("password");
                var cp = dwr.util.getValue("confirmPassword");
                if ( p != null && p.length > 0 && cp != null && p == cp && 
                    validateEmail(e, true, true) && a != null & a.length > 0 ) {
                    AjaxUserService.resetPassword(e,a,p, function(data) {
                        if ( data == "successful") {
                            location.href="/CCM/j_acegi_security_check?j_garbage=abcdexehrelasdjf232343lkajflskdjfalsdfjasldkfjasldkfjalsdkjf&j_username=" + e + "&j_password=" + p;
                        } else {
                            alert ( data ) ;
                        }
                    });
                }
            }
        </script>
    </head>
    <body>
        <br/>
        <div id="step1" align="center">
            <h2>Step 1</h2>
            <table>
                <tr>
                    <td><label>Email</label></td>
                    <td><input type="text" name="email" value="" size="40"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Send Activation Code" onclick="recoverPassword();"/></td>
                </tr>               
            </table>
        </div>
        <br>
        <div id="step2" align="center">
            <h2>Step 2</h2>
            <table>
                <tr>
                    <td><label></label></td>
                    <td><input type="hidden" name="email1" value="" size="40" /></td>
                </tr>
                <tr>
                    <td><label>Activation Code</label></td>
                    <td><input type="text" name="activationCode" value="" size="40" /></td>
                </tr>
                <tr>
                    <td><label>Password</label></td>
                    <td><input type="password" name="password" value="" size="40" /></td>
                </tr>
                <tr>
                    <td><label>Confirm Password</label></td>
                    <td><input type="password" name="confirmPassword" value="" size="40" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save & Login" onclick="resetPassword();"/></td>
                </tr>
            </table>
        </div>
    </body>
</html>
