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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxWorkService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>        
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        <script type="text/javascript" src="email_validation.js"></script>
        
        <script type="text/javascript">
            function search() {
                if ( validateEmail(dwr.util.getValue("key"), true, true) )  {                
                    clearMessages();
                    AjaxWorkService.getUserWithPic(dwr.util.getValue("key"), reply2);
                }
            } 
            var reply2 = function(customer) {
                clearMessages();
                dwr.util.setValue("image", null);
                if ( customer.id == null ) {
                     writeMessage ("failureReply",  "No Customer found with the Email, Please create Customer Profile at Users menu ");
                } else {
                    if ( customer.image == null ) {
                        writeMessage ("successReply", "No Image Available for " + customer.email + " please upload an image!");                                        
                    } else {
                        dwr.util.setValues(customer);
                    }
                    writeMessage("successReply", " Customer Name : " + customer.name);
                }
            }
           
            var peopleCache = { };
            var viewed = null;
            var systems1 = new Array();
            var systemLength = null;
            var services = { };
            var usedSystemList = { };
            function fillTable() {
                dwr.util.useLoadingMessage("Please Wait, Loading...");
                AjaxWorkService.getActiveSystems(function(people) {
                    usedSystemList = { };
                    // Delete all the rows except for the "pattern" row
                    dwr.util.removeAllRows("peoplebody", { filter:function(tr) {
                            return (tr.id != "pattern");
                        }});
                    // Create a new set cloned from the pattern row
                    var person, id;
                    
                    people.sort(function(p1, p2) {
                        return p1.name - p2.name;
                    });
                    systemLength = people.length;
                    for (var i = 0; i < people.length; i++) {
                        person = people[i];
                        if ( person.enabled == true ) {
                            id = person.id;
                            dwr.util.cloneNode("pattern", { idSuffix:id });
                            dwr.util.setValue("name" + id, person.name);
                            var email = person.currentUserEmail;
                            if ( email != null && email.length > 0 ) {
                                var maxLength = email.toString().indexOf("@");
                                maxLength = maxLength <= 14 ? maxLength : 14;
                                dwr.util.setValue("currentUserEmail" + id, email.toString().substring(0,maxLength));     
                                dwr.util.setValue("startTimeString1" + id, person.startTimeString);
                            }
                            $("pattern" + id).style.display = "";
                            peopleCache[id] = person;
                            systems1[i] = person;
                            if(person.isAvailable == true){
                                document.getElementById('edit'+id).disabled=false;
                                document.getElementById('deta'+id).disabled=true;
                            } else {
                                document.getElementById('edit'+id).disabled=true;
                                document.getElementById('deta'+id).disabled=false;
                                usedSystemList[i] = person;
                            }
                        }
                    }
                });
                
                AjaxWorkService.getAllServices(function(data) {                    
                    DWRUtil.removeAllOptions("services");
                    DWRUtil.addOptions("services", data, "name", "name" );
                    services = data;
                });
            }
            
            function assignSystem(eleid) {  
                var system = peopleCache[eleid.substring(4)];                
                var leaseHolder = dwr.util.getValue("key");
                if ( validateEmail(leaseHolder, true, true) ) {
                    clearMessages();
                    AjaxWorkService.leaseSystem(system.id, leaseHolder, function(data) {
                        if ( data == 'Assigned Successfully!') {
                            writeMessage("successReply", data +  " at " + new Date().toLocaleString());
                            fillTable();
                            dwr.util.setValue("key", "");
                        } else {                      
                            writeMessage("failureReply", "Please try again!");                            
                        }
                    } ); 
                }
            }
            var paidId = null;
            function fetchDetail(eleid) {
                var system = null;
                var systemName = null;
                if (!isNaN(eleid)   ) {
                    systemName = parseInt(eleid);
                    for ( var i = 0; i <= systems1.length; i++ ) {
                        var s = systems1[i];
                        if ( s != null && s.name == systemName ) {
                            system = s;
                        }
                    }
                } else {
                    system = peopleCache[eleid.substring(4)];
                }
                paidId = system.id;
                AjaxWorkService.getSystemLease(system.id, function(lease){
                    dwr.util.removeAllRows("detailbody", { filter:function(tr) {
                            return (tr.id != "detail");
                        }});
                    var total = 0;
                    for (var i = 0; i < lease.length; i++) {
                        systemLease = lease[i];
                        id = systemLease.id;
                        dwr.util.cloneNode("detail", { idSuffix:id });
                        dwr.util.setValue("service" + id, systemLease.service);
                        dwr.util.setValue("startTimeString" + id, systemLease.startTimeString);                             
                        dwr.util.setValue("totalMinutesUsed" + id, systemLease.totalMinutesUsed);
                        dwr.util.setValue("payableAmount" + id, systemLease.payableAmount);
                        $("detail" + id).style.display = "";
                        total += systemLease.payableAmount;
                    }
                    dwr.util.setValue("paidAmount", total);
                    document.getElementById("paidButton").disabled = false;
                    populateSystemNos ();
                    dwr.util.setValue("systemNos", system.name);
                });
            }
            function addService() {
                var s = dwr.util.getValue ("services");
                var u = dwr.util.getValue ("units");
                var e = dwr.util.getValue ("systemNos");
                var p = dwr.util.getValue ("payableAmount1");
                var a = p;
                if ( u != null && u > 0 && p != null && p > 0) {
                    AjaxWorkService.addService(s,u,e,p,'',a, replyService);
                } else {                    
                    alert(  ' units or payable amount cannot be null!');
                }
            }
            var replyService = function (data) {
                clearMessages();                
                if (  data == 'Service Added Successfully') {
                    writeMessage("successReply", data + " to : " + dwr.util.getValue("systemNos") + " at " + new Date().toLocaleString());
                    if ( dwr.util.getValue("systemNos") != 'Walk-in Customer') {
                        fetchDetail (  dwr.util.getValue("systemNos"));
                    }
                    clearPerson();
                } else {
                    writeMessage("failureReply", data);
                }
            }
            function paid () {
                var system = peopleCache[paidId];
                document.getElementById("paidButton").disabled = false;
                clearMessages();
                AjaxWorkService.chargePayment(system.id, function(data) {
                    if ( data == 'Payment Successful!' ) {
                        writeMessage("successReply", data + ", System : " + system.name + " at " + new Date().toLocaleString());
                        fillTable();
                        dwr.util.removeAllRows("detailbody", { filter:function(tr) {
                                return (tr.id != "detail");
                            }});
                        document.getElementById("paidButton").disabled = true;
                    } else {
                        writeMessage("failureReply", data);
                    }
                } );
            }
            function clearPerson() {
                dwr.util.setValues({  payableAmount1:"", units:""  });
            }
 
            function isInteger(s)
            {
                var i;
                s = s.toString();
                for (i = 0; i < s.length; i++)
                {
                    var c = s.charAt(i);
                    if (isNaN(c)) 
                    {
                        alert("This field Should contain Only number");
                        return false;
                    }
                }
                return true;
            }
            
            function updatePrice() {
                var s = dwr.util.getValue("services");
                var u = dwr.util.getValue("units");
                for ( var i = 0; i < services.length; i++ ) {
                    if ( services[i].name == s ) {
                        dwr.util.setValue("payableAmount1", services[i].unitPrice * u );
                    }
                }
            }
            
            function populateSystemNos () {
                DWRUtil.removeAllOptions("systemNos");
                var dummySystem = {name:'Walk-in Customer', id:'Walk-in Customer'};
                usedSystemList[usedSystemList.lenght+1] = dummySystem;                
                DWRUtil.addOptions("systemNos", usedSystemList, "name", "name" );                
            }
        </script>
        <jsp:include page="table_style.jsp" ></jsp:include>
        
    </head>
    <body>
        <jsp:include page="include.jsp" />
        
        <table align="center" width="800px" >
            
            <tbody>
                <tr>
                    <td>
                        
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
                        <br>
                        
                        <table >
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
                                        Action 
                                    </th>
                                    
                                </tr>
                            </thead>
                        </table>
                        <div style="height:370px; width:420px; overflow:auto;">
                            <table>
                                <tbody  id="peoplebody" >
                                    <tr id="pattern" style="display:none;">
                                        <td><span id="name"></span></td>
                                        <td><span id="currentUserEmail"></span></td>
                                        <td><span id="startTimeString1"></span></td>
                                        <td>                                            
                                            <button value="New" onclick="assignSystem(this.id);"  id="edit"  >Assign</button><button value="New" onclick="fetchDetail(this.id);"  id="deta">>></button>                                                                                    
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </td>
                    <td valign="center">
                        <table>
                            <thead>
                                <tr>
                                    <th>
                                        Resource
                                    </th>
                                    <th>
                                        Start Time
                                    </th>
                                    
                                    <th>
                                        Mins/Units
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
                                            <input type="text" id="paidAmount" value="" disabled="disabled" size="4" />
                                    <button value="Paid" onclick="paid();"  id="paidButton" disabled="disabled">Paid</button> </span> </td>
                                </tr>
                            </tbody>
                        </table>
                        <br>
                        <table title="Extra Sale">
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
                                <td><select name="services" id="services" onfocus="populateSystemNos();">
                                        
                                </select></td>
                                <td>Units*</td>
                                <td>
                                    <input type=text name="units" value="" size="4" class="cleardefault" onchange="updatePrice();" onKeyup="isInteger(this.value);updatePrice();">
                                </td>
                            </tr>
                            <tr>
                                <td>Add To</td>
                                <td><select name="systemNos" id="systemNos">                                        
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
                    </td>
                </tr>
            </tbody>
        </table>
        <script type="text/javascript">
            window.onload = fillTable;
        </script>
        <br>
        <br>
        <br>
        <jsp:include page="copyright.jsp" />
    </body>
</html>
