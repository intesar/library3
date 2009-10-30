<%-- 
    Document   : forgotpassword
    Created on : Jul 7, 2008, 1:37:24 PM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="table_style.jsp" ></jsp:include>

<html>
    <head>
        <title>FaceGuard</title>
        <style>
            a:link    {color:black; text-decoration:none; font-size:8.5pt}
            a:hover   {color:black; text-decoration:none;
                font-size:8.5pt}
            a:active  {color:black; text-decoration:none; font-size:8.5pt}
            a:visited {color:black; text-decoration:none; font-size:8.5pt}
        </style>
        <style>
            a:link    {color:black; text-decoration:none; font-size:8.5pt}
            a:hover   {color:black; text-decoration:none;
                font-size:8.5pt}
            a:active  {color:black; text-decoration:none; font-size:8.5pt}
            a:visited {color:black; text-decoration:none; font-size:8.5pt}
            .style16 {color: #FFFFFF}
        </style>
        
        
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxUserService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>
        <script type="text/javascript" src="email_validation.js"></script>
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        
        <script type="text/javascript"> 
            
            function recoverPassword() {
                dwr.util.useLoadingMessage("Please Wait, Loading...");
                var email = null;
                email = dwr.util.getValue("email");
                if ( validateEmail(email, true, true) ) {
                    AjaxUserService.forgotPassword(email, function(data) {
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
        <table align="center" border="0" width="50%" bgcolor="#C00" style="color:#FFF;
        background:#C00;
        border-collapse:collapse;
        width:370px;        
        border:5px solid #900;" >
            <thead>
                <tr>
                    <th></th>
                    <th>Help me with Password Reset</th>
                </tr>
            </thead>
            <tr>
                <td>Username/Email:</td>
                <td><input type="text" name="email" value="" /></td>                
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Send Activation Code to My Email" onclick="recoverPassword();"/></td>
            </tr>
            <tr>
                <td>
                    <a href="login.jsp">Login Page</a>
                </td>
            </tr>
        </table>
        <br>
        <table align="center" border="0" width="50%" bgcolor="#C00" style="color:#FFF;
        background:#C00;
        border-collapse:collapse;
        width:370px;        
        border:5px solid #900;" >
            <thead>
                <tr>
                    <th></th>
                    <th>Have Activation Code</th>
                </tr>
            </thead>
            <tr>
                <td>Username/Email:*</td>
                <td><input type="text" name="email1" value="" /></td>                
            </tr>
            <tr>
                <td>Activation Code:*</td>
                <td><input type="text" name="activationCode" value="" /></td>                
            </tr>
            <tr>
                <td>Password:*</td>
                <td><input type="password" name="password" value="" /></td>                
            </tr>
            <tr>
                <td>Confirm Password:*</td>
                <td><input type="password" name="confirmPassword" value="" /></td>                
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Reset My Password" onclick="resetPassword();"/></td>               
            </tr>
        </table>
    </body>
</html>
