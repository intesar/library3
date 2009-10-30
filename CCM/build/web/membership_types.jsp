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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script type="text/javascript" src="email_validation.js"></script>
        <script type="text/javascript" src="name_valadation.js"></script>
        
        <style>
            a:link    {color:black; text-decoration:none; font-size:11pt}
            a:hover   {color:black; text-decoration:none; 
                font-size:11pt}
            a:active  {color:black; text-decoration:none; font-size:11pt}
            a:visited {color:black; text-decoration:none; font-size:11pt}
        </style>
        <style>
            a:link    {color:black; text-decoration:none; font-size:11pt}
            a:hover   {color:black; text-decoration:none; 
                font-size:11pt}
            a:active  {color:black; text-decoration:none; font-size:11pt}
            a:visited {color:black; text-decoration:none; font-size:11pt}
        </style>
        
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxWorkService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>        
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        
        <script type="text/javascript">
            function init() {
                fillTable();
            }
        
            var peopleCache = { };
            var viewed = null;
        
            function fillTable() {
                dwr.util.useLoadingMessage();
                AjaxWorkService.getAllMembershipTypes(function(people) {
                    // Delete all the rows except for the "pattern" row
                    dwr.util.removeAllRows("peoplebody", { filter:function(tr) {
                            return (tr.id != "pattern");
                        }});
                    // Create a new set cloned from the pattern row
                    var person, id;
                    people.sort(function(p1, p2) { return p1.name.localeCompare(p2.name); });
                    for (var i = 0; i < people.length; i++) {
                        person = people[i];
                        id = person.id;
                        dwr.util.cloneNode("pattern", { idSuffix:id });
                        dwr.util.setValue("name1" + id, person.name);
                        dwr.util.setValue("fee1" + id, person.fee);                        
                        $("pattern" + id).style.display = "";
                        peopleCache[id] = person;
                    }
                });
                document.getElementById("name").disabled=true;                
            }
        
            function fillDiscounts(id) {
                //alert ( id );
                dwr.util.useLoadingMessage();
                AjaxWorkService.getMembershipDiscountses(id, function(people) {
                    // Delete all the rows except for the "pattern" row
                    dwr.util.removeAllRows("discountBody", { filter:function(tr) {
                            return (tr.id != "discountRow");
                        }});
                    // Create a new set cloned from the pattern row
                    var person, id;
                    people.sort(function(p1, p2) { return p1.service.localeCompare(p2.service); });
                    for (var i = 0; i < people.length; i++) {
                        person = people[i];
                        id = person.id;
                        dwr.util.cloneNode("discountRow", { idSuffix:id });
                        dwr.util.setValue("service" + id, person.service);
                        dwr.util.setValue("discountPercentage" + id, person.discountPercentage);                        
                        $("discountRow" + id).style.display = "";
                        //peopleCache[id] = person;
                    }
                });
                
            }
            
            function editClicked(eleid) {
                // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"               
                var person = peopleCache[eleid.substring(4)];               
                viewed = person.id;
                dwr.util.setValues(person);               
                fillDiscounts(viewed);
            }
        
            
        
            function writePerson() {
                var person;
                
                if ( viewed == null ) {
                    person = { id:viewed, name:null, fee:null, daysValidFor:null };
                }   else {
                    person = peopleCache[viewed];
                }
                
                dwr.util.getValues(person);
                if ( person.name != null && person.name != '' ) {
                    if ( person.fee != null && person.fee != "" && person.daysValidFor != null 
                        && person.daysValidFor != "") {
                        AjaxWorkService.saveMembershipType(person, reply1);
                    } 
                    else {
                        alert ( " Unit Price Cannot be Empty! ");
                    }
                } else {
                    alert ( " Name Cannot be Empty! ");
                }
            }
            
            function saveDiscount(eleid) {
                var membershipDiscountId = eleid.substring(4);
                var discount = dwr.util.getValue ( "discountPercentage" + membershipDiscountId);
                AjaxWorkService.saveMembershipDiscount(membershipDiscountId, discount, function (data) {
                    clearMessages();
                    if ( data == "Saved Successfully!") {
                        writeMessage ("successReply", data + " at "  + new Date().toLocaleString());
                        fillTable();
                    } else {
                        writeMessage ("failureReply", data );
                    }
                });
                
            }
            var reply1 = function (data) {
                clearMessages();
                if ( data == "Created Successfully!") {
                    writeMessage("successReply", data + " at "  + new Date().toLocaleString());
                    fillTable();
                } else {
                    writeMessage ("failureReply", "Operation Failed, Please try again!" );
                }
            }
               
            function clearPerson() {
                viewed = null;
                dwr.util.setValues({ id:null, name:null, fee:null, daysValidFor:null });
                document.getElementById("name").disabled=false;        
                       
            }
            
            function isInteger(s)
            {
                var i;
                s = s.toString();
                for (i = 0; i < s.length; i++)
                {
                    var c = s.charAt(i);
                    if (isNaN(c) && c != '.') 
                    {
                        alert("This field Should contain Only number");
                        return false;
                    }
                }
                return true;
            }
        </script>
        
        <jsp:include page="table_style.jsp" ></jsp:include>
    </head>
    <body>
        
        <jsp:include page="include.jsp" />
        <!-- <h2 align="center"> Extra Services </h2> -->
        <table align="center">
            <tr>
                <td valign="top">
                    
                    <table>
                        <thead>
                            <tr>
                                <th> Membership Name  </th>
                                <th> Fee </th>                                
                                <th></th>
                            </tr>
                        </thead>
                        <form>
                            <tbody id="peoplebody">
                                <tr id="pattern" style="display:none;">
                                    <td><span id="name1"></span></td>
                                    <td><span id="fee1"></span></td>
                                    <td>
                                        <input id="edit" type="button" value="Edit" onclick="editClicked(this.id)"/>                        
                                    </td>
                                </tr>
                            </tbody>
                        </form>
                    </table>
                    
                    
                </td>
                <td valign="top">
                    
                    <table>
                        <thead>
                            <tr>
                                <th></th>
                                <th>
                                    Add / Udpate Membership Types
                                </th>
                            </tr>
                        </thead>
                        <tr>
                            <td> Membership Name:* </td>
                            <td><input id="name" type="text" disabled size="25"/></td>
                        </tr> 
                        <tr>
                            <td> Fee:* </td>
                            <td><input id="fee" type="text" size="5" onKeyup="isInteger(this.value);"/></td>
                        </tr> 
                        <tr>
                            <td>Days Valid For:*</td>
                            <td><input id="daysValidFor" type="text" size="3" onkeyup="IsInteger(this.value);"> </td>
                        </tr>
                        <tr>
                            <td> 
                            </td>
                            <td>
                                <button value="New" onclick="clearPerson()" >New</button>
                                <button value="Save" id="save" onclick="writePerson()" >Save</button>                                                                                                
                            </td>
                        </tr>             
                        
                    </table>
                    
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <table>
                        <thead>
                            <tr>
                                <th>Service</th>
                                <th>
                                    Discount %
                                </th>
                            </tr>
                        </thead>
                        <tbody id="discountBody">
                            <tr id="discountRow" style="display:none;">
                                <td><span id="service"></span></td>
                                <td><input type="text" id="discountPercentage" value="" size="3" onkeyup="IsInteger(this.value);"/></td>
                                <td>
                                    <input id="save" type="button" value="Save" onclick="saveDiscount(this.id)"/>                        
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
        
        <script type="text/javascript">
            window.onload = fillTable();
        </script>
        <br>
        <br>
        <br>
        <jsp:include page="emails_help.jsp" />
        <jsp:include page="copyright.jsp" />
    </body>
    
</html>
