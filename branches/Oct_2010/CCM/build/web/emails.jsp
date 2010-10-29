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
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>        
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        
        <script type="text/javascript">
            function init() {
                fillTable();
            }
        
            var peopleCache = { };
            var viewed = null;
        
            function fillTable() {
                dwr.util.useLoadingMessage("Please Wait, Loading");
                AjaxAdminService.getAllEmailPreference(function(people) {
                    // Delete all the rows except for the "pattern" row
                    dwr.util.removeAllRows("peoplebody", { filter:function(tr) {
                            return (tr.id != "pattern");
                        }});
                    // Create a new set cloned from the pattern row
                    var person, id;
                    people.sort(function(p1, p2) { return p1.emailOrPhone.localeCompare(p2.emailOrPhone); });
                    for (var i = 0; i < people.length; i++) {
                        person = people[i];
                        id = person.id;
                        dwr.util.cloneNode("pattern", { idSuffix:id });
                        dwr.util.setValue("username1" + id, person.username);
                        dwr.util.setValue("email_or_phone" + id, person.emailOrPhone);                        
                        $("pattern" + id).style.display = "";
                        peopleCache[id] = person;
                    }
                });
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
                    person = { id:viewed, username:null, emailOrPhone:null, serviceProvider:null };
                }   else {
                    person = peopleCache[viewed];
                }
                
                dwr.util.getValues(person);
        
                if ( validateEmail(person.emailOrPhone, false, false) || person.emailOrPhone.length >= 10 ) {
                          
                    AjaxAdminService.saveEmailPreference(person, function(data) { 
                        clearMessages();
                        if ( data == "Operation succesful!") {
                            writeMessage ("successReply", "Created/Updated Email for report"  + " at " + new Date().toLocaleString() );
                            fillTable();
                        } else {
                            writeMessage ("failureReply", "Operation Failed, Please try again");
                        }
                    });
                } else {
                    alert ( " not a valid phone no.")
                }
            }
        
            function clearPerson() {
                viewed = null;
                dwr.util.setValues({ id:null, username:null, emailOrPhone:null, serviceProvider:null });
            }
            function deletePerson() {
                AjaxAdminService.deleteEmail(viewed, function(data) {
                    clearMessages();
                    if ( data == "Deleted Email Successful!") {
                        writeMessage ("successReply", data  + " at " + new Date().toLocaleString());
                        fillTable();
                        clearPerson();
                    } else {
                        writeMessage ("failureReply", " Operation Failed, Please try again!");
                    }
                });
            }
        </script>
        
        <jsp:include page="table_style.jsp" ></jsp:include>
    </head>
    <body>
        
        <jsp:include page="include.jsp" />
        <!-- <h2 align="center"> Email/Phone For Daily Reports </h2>  -->
        <table align="center">
            <tr>
                <td valign="top">
                    
                    <table>
                        <thead>
                            <tr>
                                <th> Name  </th>
                                <th> Email/Phone </th>
                                <th></th>
                            </tr>
                        </thead>
                        <form>
                            <tbody id="peoplebody">
                                <tr id="pattern" style="display:none;">
                                    <td><span id="username1">Username</span></td>
                                    <td><span id="email_or_phone">emailOrPhone</span></td>
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
                                <th>Add / Update </th>
                                <th>
                                    Email/Mobile For Daily Reports
                                </th>
                            </tr>
                        </thead>
                        
                        <tr>
                            <td> Service: </td>
                            <td><select name="serviceProvider">
                                    <option value="email">email</option>
                                    <option value="@airtelap.com">Andhra Pradesh Airtel</option>
                                    <option value="@ideacellular.net">Andhra Pradesh Idea</option>
                                    <option value="@airtelchennai.com">Chennai Skycell/Airtel</option>
                                    <option value="@rpgmail.net">Chennai RPG Cellular</option>                        
                                    <option value="@airtelmail.com">Delhi Airtel</option>  
                                    <option value="@delhi.hutch.co.in">Delhi Hutch</option>
                                    <option value="@ideacellular.net">Gujarat Idea Cellular</option>
                                    <option value="@airtelmail.com">Gujarat Airtel</option>
                                    <option value="@celforce.com">Gujarat Celforce / Fascel</option>
                                    <option value="@airtelmail.com">Goa Airtel </option>
                                    <option value="@bplmobile.com">Goa BPL Mobile</option>
                                    <option value="@ideacellular.net">Goa Idea Cellular</option>  
                                    <option value="@airtelmail.com">Haryana Airtel</option>
                                    <option value="@escotelmobile.com">Haryana Escotel</option>
                                    <option value="@airtelmail.com">Himachal Pradesh Airtel</option>
                                    <option value="@airtelkk.com">Karnataka Airtel</option>
                                    <option value="@airtelkerala.com">Kerala Airtel</option>
                                    <option value="@escotelmobile.com">Kerala Escotel</option>
                                    <option value="@bplmobile.com">Kerala BPL Mobile</option>  
                                    <option value="@airtelkol.com">Kolkata Airtel</option>
                                    <option value="@airtelmail.com">Madhya Pradesh Airtel</option>
                                    <option value="@airtelmail.com">Maharashtra Airtel </option>
                                    <option value="@bplmobile.com">Maharashtra BPL Mobile</option>
                                    <option value="@ideacellular.net">Maharashtra Idea Cellular</option>
                                    <option value="@airtelmail.com">Mumbai Airtel</option>
                                    <option value="@bplmobile.com">Mumbai BPL Mobile</option>  
                                    <option value="@airtelmail.com">Punjab Airtel</option>
                                    <option value="@bplmobile.com">Pondicherry BPL Mobile</option>
                                    <option value="@airtelmail.com">Tamil Nadu Airtel</option>
                                    <option value="@bplmobile.com">Tamil BPL Mobile</option>
                                    <option value="@airsms.com">Tamil Nadu Aircel</option>
                                    <option value="@escotelmobile.com">Uttar Pradesh(West) Escotel</option>                                    
                            </select></td>
                        </tr>
                        
                        <tr>
                            <td> Email/Phone:* </td>
                            
                            <td><input id="emailOrPhone" type="text" size="30"/>
                            <br> (abc@yahoo.com, 9849098490)
                            </td>
                        </tr> 
                        <tr>
                            <td> Name: </td>
                            <td><input id="username" type="text" size="30"/></td>
                        </tr> 
                        <tr>
                            <td> 
                            </td>
                            <td>
                                <input type="button" value="Clear" onclick="clearPerson()"/>
                                <input type="button" value="Save" onclick="writePerson()"/>  
                                <input type="button" value="Delete" onclick="deletePerson()"/>                                                                  
                            </td>
                        </tr>             
                        
                    </table>
                    
                </td>
            </tr>
        </table>
        
        <script type="text/javascript">
            window.onload = fillTable;
        </script>
        <br>
        <br>
        <br>
        <jsp:include page="emails_help.jsp" />
        <jsp:include page="copyright.jsp" />
    </body>
    
</html>
