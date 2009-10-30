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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <script type='text/javascript' src='/CCM/dwr/interface/AjaxWorkService.js'></script>
    <script type='text/javascript' src='/CCM/dwr/engine.js'></script>        
    <script type='text/javascript' src='/CCM/dwr/util.js'></script>
    <script type="text/javascript" src="email_validation.js"></script>
    <script type="text/javascript" src="name_valadation.js"></script>
    <script type="text/javascript" src="/CCM/datepickercontrol.js"></script>
    <link type="text/css" rel="stylesheet" href="/CCM/datepickercontrol.css"> 
    
    <script type="text/javascript">
        var cust = null;
        function writePerson() {
            dwr.util.useLoadingMessage("Please Wait Loading....");
            var customer = null;
            if ( cust == null ) {
                customer = { id:null, name:null, img:null, email:null, 
                    homePhone:null, mobilePhone:null, otherPhone:null,
                    street:null,city:null, zipcode:null, state:null, 
                    country:null, passportNo:null, voterId:null,
                    collegeName:null, rationCardNo:null, panCardNo:null,
                    dob:null, gender:null, comments:null };
                
            } else {
                customer = cust;
            }
            
            dwr.util.getValues(customer);
            
            var flag1 = customer.img.value.toString().toLowerCase().search ('jpg') ;
            var flag2 = customer.img.value.toString().toLowerCase().search ('bmp') ;
            var flag3 = customer.img.value.toString().toLowerCase().search ('png') ;
            
            if ( flag1 > 0 || flag2 > 0 || flag3 > 0 || customer.img.value == null || customer.img.value == "") {
              
                if ( validateEmail(customer.email, true, true) ) {
                   
                    if ( /*customer.img !="" && */ customer.name != "" && customer.street !=""  && customer.city !="" && customer.zipcode !="" &&  customer.state != "" && customer.country!="")
                    {
                        AjaxWorkService.createCustomer(customer, reply1);
                    } 
                    else {
                        alert ( " * Marked Fields are required ! ");
                    }
                    
                }
                
            } else {
                alert ( ' Image type can only be jpg, bmp, png');
            }
            //dwr.engine.endBatch();
        }
            
        var reply1 = function (data) {                
            clearMessages();
            if ( data == "Customer Created Successfully!" ) {
                writeMessage ("successReply", "Created/Updated Profile at " + new Date().toLocaleString());                
            } else {
                writeMessage ("failureReply", "Please try again with different values");
            }
        }
        
         
        function execute() {
            dwr.util.useLoadingMessage("Please Wait Loading....");
            AjaxAdminService.getSystemLease(document.getElementById("DPC_startDate_YYYY-MM-DD").value,
            reply1 );
        }
            
            
        
        function clearPerson() {
            dwr.util.setValues({ id:null, img:null, name:null, email:null, 
                homePhone:null, mobilePhone:null, otherPhone:null,
                street:null,city:null, zipcode:null, state:null, 
                country:null, passportNo:null, voterIdCardNo:null,
                collegeName:null, rationCardNo:null, panCardNo:null,
                dob:null, gender:null, comments:null });
            cust = null;
        }
            
        function search() {
            dwr.util.useLoadingMessage("Please Wait Loading....");
            AjaxWorkService.getUserWithPic(dwr.util.getValue("key"), reply2);            
        }
            
        var reply2 = function(customer) {
            cust = customer;
            dwr.util.setValue("image", null);
            clearMessages();
            if ( customer.id == null ) {
                writeMessage ("failureReply", "No Match for the Given Email, Please create User Profile " );                
            } else {
                writeMessage ("successReply", "Found " + customer.name );   
                dwr.util.setValues(customer);
            }
        }
    </script>
    <jsp:include page="table_style.jsp" ></jsp:include>
</head>
<body>
    
    <jsp:include page="include.jsp" />
    
   
    <table align="center" width="300px">
        <thead>
            <tr>
                <th>
                    Customer Look Up 
                </th>
                <th> <a href="memberships.jsp" style="color:white"> Memberships</a> </th>
                <th><a href="membership_types.jsp" style="color:white">Rules</a></th>
            </tr>
        </thead>
        <tr>
            <td>
                <input type="text" name="key" id="key" value="" size="40"/>                
            </td>
            <td><input type="submit" value="Search" onclick="search();"/></td>            
        </tr>
        <tr>
            <td><img id="image" src="javascript:void(0);"/>  </td>
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
                <td><input id="name" type="text" size="30"/></td>
            </tr>
            <tr>
                <td>Email:&nbsp;&nbsp;*</td>
                <td><input id="email" type="text" size="30"/></td>
            </tr>
            <tr>
                <td>Street:&nbsp;&nbsp;*</td>
                <td><input id="street" type="text" size="30"/></td>
            </tr>
            <tr>
                <td>City:&nbsp;&nbsp;*</td>
                <td><input id="city" type="text" size="30" value="hyderabad"/></td>
            </tr>
            
            <tr>
                <td>State:</td>
                <td><input id="state" type="text" size="30" value="ap"/></td>
            </tr>
            <tr>
                <td>Zipcode:&nbsp;&nbsp;*</td>
                <td><input id="zipcode" type="text" size="30"/></td>
            </tr>
            <tr>
                <td>Country:&nbsp;&nbsp;*</td>
                <td><input type="text" id="country"  value="India" size="30"/></td> 
            </tr>
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
            <td><input id="homePhone" type="text" size="30"/></td>
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
                <td><input id="ssn" type="text" size="30"/></td>
            </tr>
            
            <tr>
                <td>Passport No</td>
                <td><input id="passportNo" type="text" size="30"/></td>
            </tr>
            <tr>
                <td>Voter Id</td>
                <td><input id="voterId" type="text" size="30"/></td>
            </tr>
            <tr>
                <td>College Name</td>
                <td><input id="collegeName" type="text" size="30"/></td>
            </tr>
            <tr>
                <td>Ration Card No</td>
                <td><input id="rationCardNo" type="text" size="30"/></td>
            </tr>
            <tr>
                <td>Pan Card No</td>
                <td><input id="panCardNo" type="text" size="30"/></td>
            </tr>
            <tr>
            
            
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
                    <input type="button" value="New" onclick="clearPerson()"/> 
                    <input type="button" value="Save & Verified" onclick="writePerson()"/>  
                    
                </td>
            </tr>
        </table>
    </td>
    </tr>
    </table>
</body>

<jsp:include page="customer_help.jsp" />
<jsp:include page="copyright.jsp" />
</html>
