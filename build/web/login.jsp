<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>FaceGuard</title>
        <style type="text/css">
            <!--
            .style2 {font-size: 12px; }
            .style3 {
                font-size: 16px;
                font-weight: bold;
                color: #cc0001;
            }
            .style4 {
                font-size: 10px;
                font-family: Geneva, Arial, Helvetica, sans-serif;
            }
            .style9 {font-size: 12px; color: #023a8a; }
            .style10 {
                font-size: 9px;
                color: #023a8a;
            }
            .style11 {font-family: Arial, Helvetica, sans-serif}
            .style12 {
                color: #b7b7b9;
                font-weight: bold;
            }
            .style13 {
                color: #B7B7B9;
                font-weight: bold;
            }
            .style14 {
                color: #828287;
                font-weight: bold;
            }
            .style25 {font-size: 12px; font-family: Verdana, Arial, Helvetica, sans-serif; }
            .style26 {font-size: 11px; font-family: Verdana, Arial, Helvetica, sans-serif; }
            .style28 {
                font-size: 11px;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-weight: bold;
                color: #FFFFFF;
            }
            .style30 {font-size: 11px; font-family: Verdana, Arial, Helvetica, sans-serif; color: #FFFFFF; }
            .style32 {font-size: 14px; font-family: Verdana, Arial, Helvetica, sans-serif; font-weight: bold; color: #FFFFFF; }
            .style33 {font-size: 14px}
            .style35 {font-size: 12px; color: #ffffff; }
            -->
        </style>
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxUserService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>
        <script type="text/javascript" src="email_validation.js"></script>
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        
        <script type="text/javascript"> 
            var email = "";
            var password = "";
            function createAccount() {
                dwr.util.useLoadingMessage("Please wait while we create your account!");
                var c = dwr.util.getValue("companyName");
                var u = dwr.util.getValue("email");
                var p = dwr.util.getValue("password");
                var cp = dwr.util.getValue("confirmPassword");
                var minutes = dwr.util.getValue("minutes");
                var rate = dwr.util.getValue("rate");
                var maxSystems = dwr.util.getValue("maxSystems");
                //alert ( c + u + p + cp);
                if ( c != null && c != "" && p != null && p != "" && p == cp) {
                    if ( validateEmail(u, true, true) ) {
                        email = u;
                        password = p;
                        AjaxUserService.registerNewOrganization ( c, "hyd", u, p, minutes, rate, maxSystems, reply1);
                    }
                } else {
                    alert ( " Company Name, Password Cannot be Empty Or \n\ " +
                        " Password & ConfirmPassword donot match ");
                }
        
            } 
               
            var reply1 = function ( data ) {            
                if ( data == 'Please login with your email and password') {                        
                    //alert ( 'Congratulation Your Account is Created Successfully!' );
                    location.href="/CCM/j_acegi_security_check?j_garbage=abcdexehrelasdjf232343lkajflskdjfalsdfjasldkfjasldkfjalsdkjf&j_username=" + email + "&j_password=" + password;
                } else {                    
                    alert ( data );
                }                
            }
        </script>
        
        <script type="text/javascript">
            function redirectIfIE() {
                //var browser=navigator.appName;
                //var regex = "faceguard";
                //var isFaceGuard = location.href.toString().search(regex);
                
                //if ( browser == 'Microsoft Internet Explorer'  && isFaceGuard != -1 ) {
                    //alert ( " Your are using IE, Please Enable Popups & Wait while we redirect you!");
                    //var strUrl = "http://biadevbox.homelinux.com:8080/CCM/";
                    //window.open(strUrl);
                    //window.close();
                //}
            }
            function submitForm() {
                //var browser=navigator.appName;   
                //var regex = "faceguard";
                //var isFaceGuard = location.href.toString().search(regex);
                var email = dwr.util.getValue("j_username");
                var password = dwr.util.getValue("j_password");
                //if ( browser == 'Microsoft Internet Explorer' && isFaceGuard != -1 ) {
                //    alert ( " Your are using IE, Please Enable Popups & Wait while we redirect you!");
                //    var strUrl = "http://biadevbox.homelinux.com:8080/CCM/j_acegi_security_check?j_garbage=abcdexehrelasdjf232343lkajflskdjfalsdfjasldkfjasldkfjalsdkjf&j_username=" + email + "&j_password=" + password;
                //    window.open(strUrl);
                //} else {
                    location.href="/CCM/j_acegi_security_check?j_garbage=abcdexehrelasdjf232343lkajflskdjfalsdfjasldkfjasldkfjalsdkjf&j_username=" + email + "&j_password=" + password;
                //}
            }  
        </script>
        
    </head>
    
    <body>
        <table width="830" height="28" border="0" align="center" bordercolor="#cc0001" bgcolor="#cc0001" >
            <tr>
                <td width="824"><span class="style32">FaceGuard Login </span></td>
            </tr>
        </table>
        <table width="800" height="554" border="0" align="center" bordercolor="#cc0001"  bgcolor="#ffffff">
            <tr>
                <td width="100" height="550" valign="top">
                    <% if (request.getParameter("login_error") != null) {%> <b> <font color=RED size="2"> Invalid Credentials! </font></b> <% }%> 
                    <form action="j_acegi_security_check" method="POST" id="login_form" >
                        <table width="188" border="0" rules="none" align="center" bordercolor="#cc0001" bgcolor="#cc0001">
                            
                            <tr>
                                <td width="185" valign="top"><span class="style28">Email:</span></td>
                            </tr>
                            <tr>
                                
                                <td valign="top">
                                    <label>
                                        <span class="style28"><img src="PNG-Others-Edit_Users.ico-32x32.png" alt="login" width="34" height="29" align="middle" /></span>
                                        <input type="text" name='j_username' size="19" />
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top" class="style28">Password:</td>
                                
                            </tr>
                            <tr>
                                <td valign="top">
                                    <label>
                                        <span class="style28"><img src="Locker.png" width="34" height="30" align="middle" /></span>
                                        <input type="password" name='j_password' size="19" />
                                    </label>
                                </td>
                            </tr>
                            <tr align="right">
                                <td valign="top" align="center">
                                    <input type="button" value="Sign In " name="Sign In" onclick="submitForm();"/>
                                    <input type="submit" style="visibility:hidden" />                                    
                                </td>                                
                            </tr>                            
                            <tr>
                                <td valign="top" class="style30"><strong><strong><strong><img src="Tag_32.png" width="32" height="32" align="absmiddle" /></strong></strong><a href="forgotpassword.jsp" style="color:white">Forgot Password ?</a></strong></td>
                            </tr>
                        </table>
                    </form>
                <p>&nbsp;</p></td>
                <td width="700" valign="top"><table width="632" height="119" border="1" rules="none" bordercolor="#587498" bgcolor="#ffffff" >
                        <tr>
                            <td width="192" height="115"><img src="FG_RED.png" width="200" height="105" />
                            </td>
                            <td width="424" valign="top"><p class="style12">_____________________________________________________</p>
                                <p align="center" class="style25"><span class="style14">FaceGuard is Web Based Cyber Cafe Manager Suite</span> </p>
                                <p align="center" class="style25"><span class="style14">Guards you from Cyber Terror</span></p>
                                <p align="center" class="style25"><span class="style14"> Life Time Free if registered before Dec 31 2008 (India)</span></p>                                
                            <p align="center" class="style13">_____________________________________________________</p></td>
                        </tr>
                    </table>
                    <table width="636" height="306" border="1"  bordercolor="#587498"  bgcolor="#ffffff" rules="none">
                        <tr>
                            <td width="232"><table width="215" align="center" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <th width="233"><div align="left">
                                                <p> Product Features....</p>
                                        </div></th>
                                    </tr>
                                    <tr>
                                        <td><div align="left"><img src="right.jpg" width="24" height="23" /><span class="style25">Compliance with Cyber Laws</span></div></td>
                                    </tr>
                                    <tr>
                                        <td><div align="left"><img src="right.jpg" width="24" height="23" /><span class="style25">Save Rs 6,000/month by &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;removing unauthorize usage</span></div></td>
                                    </tr>
                                    <tr>
                                        <td><div align="left"><img src="right.jpg" width="24" height="23" /><span class="style25">Bill Printing, Scanning, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Snacks,and vitually anything.</span></div></td>
                                    </tr>
                                    <tr>
                                        <td><div align="left"><img src="right.jpg" width="24" height="23" /><span class="style25">Free Report via SMS/Emails</span></div></td>
                                    </tr>
                                    <tr>
                                        <td><div align="left"><img src="right.jpg" width="24" height="23" /><span class="style25">Automatic backups every day &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;data.</span></div></td>
                                    </tr>
                                    <tr>
                                        <td><span class="style25"><img src="right.jpg" width="24" height="23" />Tackle Terrorism, Crime, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Missuse etc</span></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <div align="right"><span class="style33">&nbsp;<a href="http://faceguard.bizintelapps.com"><strong>Read more...</strong></a>. </span></div></td>
                                    </tr>
                                </table>
                            <p align="center" class="style3"> <a href="customer_registration.jsp">Customer Self Registration</a> </p></td>
                            <td width="394"><table width="333" border="1" align="center" bordercolor="#000000" bgcolor="#cc0001" rules="none">
                                    <tr>
                                        <td colspan="2"><p style="color:#FFFFFF" align="center"><strong class="style25">Sign up for FaceGuard.</strong><br />
                                            <span class="style25">It's free and anyone can join.</span></p>
                                        <div id="reg_subhead"></div></td>
                                    </tr>
                                    <tr>
                                        <td width="139"><div align="right" class="style26" style="color:#FFFFFF">Cyber Cafe Name: * </div></td>
                                        <td width="184">
                                            <label>
                                                <input type="text" name="companyName" size="19" />
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><div align="right" class="style26" style="color:#FFFFFF">Username/Email: * </div></td>
                                        <td><input type="text" name="email"  size="19" /></td>
                                    </tr>
                                    <tr>
                                        <td><div align="right" class="style26" style="color:#FFFFFF">Password: * </div></td>
                                        <td><input type="password" name="password" size="19" /></td>
                                    </tr>
                                    <tr>
                                        <td><div align="right" class="style26" style="color:#FFFFFF">Confirm Password: * </div></td>
                                        <td><input type="password" name="confirmPassword" size="19" /></td>
                                    </tr>
                                    <tr>
                                        <td><div align="right" class="style26" style="color:#FFFFFF">Rate: * </div></td>
                                        <td>
                                            <label>
                                                <select name="minutes">
                                                    <option value="1"> 1 Minute </option>
                                                    <option value="15">15 Minutes</option>
                                                    <option value="30">30 Minutes</option>
                                                    <option value="60">60 Minutes</option>
                                                </select>
                                            </label>
                                            <label>
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
                                                </select>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><div align="right" class="style26" style="color:#FFFFFF">Max Computers: * </div></td>
                                        <td>
                                            <label>
                                                <select name="maxSystems">
                                                    <option value="10">10</option>
                                                    <option value="15">15</option>
                                                    <option value="20">20</option>
                                                    <option value="30">30</option>
                                                    <option value="40">40</option>
                                                    <option value="50">50</option>
                                                </select>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>
                                            <label></label>
                                            <div align="center"><img src="button1over.png" width="156" height="21" onclick="createAccount();"/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td><span class="style4" style="color:#FFFFFF">By clicking Sign Up, you are indicating that you have read and agree to the </span><span class="style4"><a href="http://faceguard.bizintelapps.com/">Terms of use and Privacy Policy.</a></span><span class="style4" style="color:#FFFFFF"> </span></td>
                                    </tr>
                            </table></td>
                        </tr>
                    </table>      
                    <div align="center">
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Home Page</a> | 
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Features</a> | 
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Customer</a> 
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Testimonials</a> | 
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Client</a> 
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Software</a> | 
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Suggestion</a> | 
                        <a href="http://faceguard.bizintelapps.com/" class="style9"> Benifits</a> |
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Tutorials</a> 
                        <a href="http://faceguard.bizintelapps.com/" class="style9">And Demo</a> | 
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Live</a> 
                        <a href="http://faceguard.bizintelapps.com/" class="style9">Chat</a> 
                        
                    </div>
                    <p align="center" class="style9"> <a href="http://faceguard.bizintelapps.com/index.php?option=com_contact&view=contact&id=1%3Acontact-bizintelapps&catid=12%3Acontacts&Itemid=62" class="style9">Contact Us</a> | USA 733.699.7898 | India 40.65764414</p>
                    <p align="center" class="style10 style2" >
                <span class="style2">&copy;</span> <span class="style2">Copyrights</span> <span class="style2"><a href="http://www.bizintelapps.com">BizIntelApps</a></span> <span class="style2">2008</span> <span class="style2">All</span> <span class="style2">Rights</span> <span class="style2">Reserved</span>. </p>    </td>
            </tr>
        </table>
        
    </body>
</html>
