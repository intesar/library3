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
        <title>Dashboard</title>
        
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxWorkService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>        
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        <script type="text/javascript">
            function search() {
                AjaxWorkService.getCustomer(dwr.util.getValue("key"), reply2);
            }
            
            var reply2 = function(customer) {
                dwr.util.setValues(customer);
            }
            function addToMemberList() {
                
            }
            var reply3 = function (data) {
                alert ( data );
                // write to span if successful else alert if error
                //if ( data.toString().startsWidth)
            }
            
            var peopleCache = { };
            var viewed = null;
        
            function fillTable() {
                dwr.util.useLoadingMessage();
                AjaxWorkService.getActiveSystems(function(people) {
                   
                    // Delete all the rows except for the "pattern" row
                    dwr.util.removeAllRows("peoplebody", { filter:function(tr) {
                            return (tr.id != "pattern");
                        }});
                    // Create a new set cloned from the pattern row
                    var person, id;
                    //people.sort(function(p1, p2) { return p1.macAddress.localeCompare(p2.macAddress); });
                    for (var i = 0; i < people.length; i++) {
                        person = people[i];
                        id = person.id;
                        dwr.util.cloneNode("pattern", { idSuffix:id });
                        dwr.util.setValue("name" + id, person.name);
                        dwr.util.setValue("currentUserEmail" + id, person.currentUserEmail);     
                        dwr.util.setValue("startTimeString" + id, person.startTimeString);
                        $("pattern" + id).style.display = "";
                        peopleCache[id] = person;
                    }
                });
            }
            
            function assignSystem(eleid) {                
                var system = peopleCache[eleid.substring(4)];                
                var leaseHolder = dwr.util.getValue("key");
                if ( leaseHolder == "" ) {
                    leaseHolder = "unknown";
                }
                AjaxWorkService.leaseSystem(system.id, leaseHolder, function(data) {
                    alert ( data );
                } );
            }
            
            function fetchDetail(eleid) {
                var system = peopleCache[eleid.substring(4)];
                //alert ( system.id );
                AjaxWorkService.getSystemLease(system.id, function(lease){
                    document.getElementById("paidAmount").disabled = false;
                    // Delete all the rows except for the "pattern" row
                    dwr.util.removeAllRows("detailbody", { filter:function(tr) {
                            return (tr.id != "detail");
                        }});
                    //alert ( 'l');
                    // Create a new set cloned from the pattern row
                    //var person, id;
                    //people.sort(function(p1, p2) { return p1.macAddress.localeCompare(p2.macAddress); });
                    var total = 0;
                    for (var i = 0; i < lease.length; i++) {
                        systemLease = lease[i];
                        //person = people[i];
                        //id = person.id;
                        //alert ( lease.id);
                        id = systemLease.id;
                        dwr.util.cloneNode("detail", { idSuffix:id });
                        dwr.util.setValue("service" + id, systemLease.service);
                        dwr.util.setValue("startTimeString" + id, systemLease.startTimeString);                             
                        dwr.util.setValue("totalMinutesUsed" + id, systemLease.totalMinutesUsed);
                        dwr.util.setValue("payableAmount" + id, systemLease.payableAmount);
                        dwr.util.setValue("paidAmount"+id, systemLease.payableAmount);
                        $("detail" + id).style.display = "";
                        total += systemLease.payableAmount;
                        //peopleCache[id] = person;
                    }
                    dwr.util.setValue("paidAmount", total);
                    document.getElementById("paidAmount").disabled = true;
                });
            }
            function addService() {
                var s = dwr.util.getValue ("services");
                var u = dwr.util.getValue ("units");
                var e = dwr.util.getValue ("email");
                var p = dwr.util.getValue ("payableAmount1");
                var a = dwr.util.getValue ("paid");
                AjaxWorkService.addService(s,u,e,p,'',a, replyService);
            }
            var replyService = function (data) {
                alert ( data );
            }
            
            function paid (eleid) {
                var system = peopleCache[eleid.substring(4)];
                AjaxWorkService.chargePayment(system.id, function(data) {
                    alert ( data );
                } );
            }
        </script>
        <jsp:include page="table_style.jsp" ></jsp:include>
        
    </head>
    <body>
        <jsp:include page="include.jsp" />
        
       
        <table>
            <thead>
                <tr>
                    <th>
                        Customer Look Up
                    </th>
                    <th></th>
                </tr>
            </thead>
            <tr>
                <td>
                    <input type="text"  id="key" value="" />
                    <input type="submit" value="Search" onclick="search();"/>          
                    <input type="submit" value="Add To Member List" onclick="addToMemberList();"/>
                    <img id="image" src="javascript:void(0);"/>
                    <br>
                </td>
            </tr>
        </table>
        
        <table>
            <thead>
                <tr>
                    <th>
                        No
                    </th>
                    <th>
                        Customer
                    </th>
                    <th>
                        Start Time
                    </th>
                    <th>
                        
                    </th>
                    <th></th>
                </tr>
            </thead>
            <tbody  id="peoplebody" >
                <tr id="pattern" style="display:none;">
                    <td><span id="name"></span></td>
                    <td><span id="currentUserEmail"></span></td>
                    <td><span id="startTimeString"></span></td>
                    <td>
                        <input type="button" id="edit" value="Assign" onclick="assignSystem(this.id);" />
                        <input type="button" id="deta" value="Detail" onclick="fetchDetail(this.id);" />
                        <input type="button" id="geta" value="Paid" onclick="paid(this.id);" />
                    </td>
                </tr>
            </tbody>
        </table>
        
        
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
                    <th>
                        Paid
                    </th>
                    <th></th>
                </tr>
            </thead>
            <tbody  id="detailbody" >
                <tr id="detail" >
                    <td><span id="service"></span></td>
                    <td><span id="startTimeString"></span></td>                    
                    <td><span id="totalMinutesUsed"></span></td>
                    <td><span id="payableAmount">Total</span></td>
                    <td><input type="text" id="paidAmount" value=""  size="4" />
                </tr>
            </tbody>
        </table>
        
        <table>
            <tr>
                <td>Service</td>
                <td><select name="services">
                        <option>B/W Print</option>
                        <option>Color Print</option>
                        <option>Scan</option>
                        <option>DVD Burn</option>
                        <option>CD Burn</option>
                        <option>B/W Copy</option>
                        <option>Color Copy</option>
                        <option>other</option>
                </select></td>
                <td>Units</td>
                <td><input type="text" name="units" value="" size="4" /></td>
            </tr>
            <tr>
                <td>Add To System/Email</td>
                <td><input type="text" name="email" value="" /></td>
                <td>Payable Amount</td>
                <td><input type="text" name="payableAmount1" value=""  size="4" /></td>
            </tr>
            <tr>
                <td>Comments</td>
                <td><textarea name="comments" rows="4" cols="20">
                </textarea></td>
                <td>Paid</td>
                <td><input type="text" name="paid" value=""  size="4"/></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td><input type="submit" value="Save" onclick="addService();" /></td>
                <td><input type="submit" value="Clear" /></td>
            </tr>
        </table>
        <script type="text/javascript">
            onload = fillTable();
        </script>
        <br>
        <br>
        <br>
        
        <jsp:include page="copyright.jsp" />
    </body>
</html>
