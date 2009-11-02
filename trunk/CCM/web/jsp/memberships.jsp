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
            
            function fillMembershipTypes() {
                dwr.util.useLoadingMessage();     
                AjaxWorkService.getAllMembershipTypes(function(data) {                    
                    DWRUtil.removeAllOptions("membershipTypeString");
                    DWRUtil.addOptions("membershipTypeString", data, "name", "name" );
                    //services = data;
                });
            }
        
            var peopleCache = { };
            var viewed = null;
            
            function search() {          
                dwr.util.useLoadingMessage();                
                clearMessages();
                AjaxWorkService.getMemberships(dwr.util.getValue("key"), function(people) {
                    // Delete all the rows except for the "pattern" row
                    dwr.util.removeAllRows("peoplebody", { filter:function(tr) {
                            return (tr.id != "pattern");
                        }});
                    // Create a new set cloned from the pattern row
                    var person, id;
                    people.sort(function(p1, p2) { return p1.membershipTypeString.localeCompare(p2.membershipTypeString); });
                    for (var i = 0; i < people.length; i++) {
                        person = people[i];
                        id = person.id;
                        dwr.util.cloneNode("pattern", { idSuffix:id });
                        dwr.util.setValue("email1" + id, person.email);
                        dwr.util.setValue("membershipType1" + id, person.membershipTypeString);                        
                        $("pattern" + id).style.display = "";
                        peopleCache[id] = person;
                    }
                });
                document.getElementById("email").disabled=true;            
                document.getElementById("membershipTypeString").disabled=true;     
                document.getElementById("isActive").disabled=true;            
            } 
            
            function editClicked(eleid) {
                // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
                var person = peopleCache[eleid.substring(4)];
                viewed = person.id;
                dwr.util.setValues(person);
            }
        
            function writePerson() {
                var person;
                
                if ( viewed == null ) {
                    person = { id:viewed, email:null, membershipTypeString:null, isActive:null };
                }   else {
                    person = peopleCache[viewed];
                }
                
                dwr.util.getValues(person);
                if (  validateEmail(dwr.util.getValue("email"), true, true) ) {                    
                        AjaxWorkService.saveMembership(person, reply1);  
                }                        
            }
           
            var reply1 = function (data) {
                clearMessages();
                if ( data == "Saved Successfully!") {
                    writeMessage ("successReply", data + " at "  + new Date().toLocaleString());
                    dwr.util.setValue("key", "");
                    search();
                    clearPerson();
                } else {
                    writeMessage ("failureReply", "Please try again!" );
                }
            }
            
            function clearPerson() {
                viewed = null;
                dwr.util.setValues({ id:null, email:null, startDate:null, endDate:null });
                document.getElementById("email").disabled=false;            
                document.getElementById("membershipTypeString").disabled=false;     
                document.getElementById("isActive").disabled=false;     
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
                    
                    <tr>
                        <td>
                            <input type="text"  id="key" value="Email" size="30" />
                            <input type="submit" value="Search" onclick="search();"/>                                                                          
                            <br>
                        </td>
                    </tr>
                </table>
            </td>
            
            <tr>
                <td valign="top">
                    <table>
                        <thead>
                            <tr>
                                <th> User  </th>
                                <th> Membership </th>                                
                                <th></th>
                            </tr>
                        </thead>
                        <form>
                            <tbody id="peoplebody">
                                <tr id="pattern" style="display:none;">
                                    <td><span id="email1"></span></td>
                                    <td><span id="membershipType1"></span></td>
                                    <td>
                                        <input id="edit" type="button" value="Edit" onclick="editClicked(this.id)"/>                        
                                    </td>
                                </tr>
                            </tbody>
                        </form>
                    </table>
                </td>     
                <td>
                    <table>
                        <thead>
                            <tr>
                                <th></th>
                                <th>
                                    Add / Udpate Memberships
                                </th>
                            </tr>
                        </thead>
                        <tr>
                            <td> Username:* </td>
                            <td><input id="email" type="text" disabled size="25"/></td>
                        </tr> 
                        <tr>
                            <td> Membership:* </td>
                            <td><select id="membershipTypeString" disabled >
                            </select></td>
                        </tr> 
                        <tr>
                            <td> Active:* </td>
                            <td><select id="isActive" disabled>
                                    <option value="true">yes</option>
                                    <option value="false">no</option>
                            </select></td>
                        </tr>
                        <tr>
                            <td> Start Date:* </td>
                            <td><input id="startDateString" type="text" disabled size="25"/></td>
                        </tr> 
                        <tr>
                            <td> Expiration:* </td>
                            <td><input id="expirationDateString" type="text" disabled size="25"/></td>
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
        </table>
        
       <script type="text/javascript">
            window.onload = fillMembershipTypes();
        </script>
        <br>
        <br>
        <br>
        <jsp:include page="emails_help.jsp" />
        <jsp:include page="copyright.jsp" />
    </body>
    
</html>

