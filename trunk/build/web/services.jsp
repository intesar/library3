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
        
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxAdminService.js'></script>
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
                AjaxAdminService.getAllServices(function(people) {
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
                        dwr.util.setValue("name1" + id, person.name);
                        dwr.util.setValue("unitPrice1" + id, person.unitPrice);                        
                        $("pattern" + id).style.display = "";
                        peopleCache[id] = person;
                    }
                });
                document.getElementById("name").disabled=true;                
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
                    person = { id:viewed, name:null, unitPrice:null };
                }   else {
                    person = peopleCache[viewed];
                }
                
                dwr.util.getValues(person);
                if ( person.name != null && person.name != '' ) {
                    if ( person.unitPrice != null && person.unitPrice != "") {
                        AjaxAdminService.saveService(person, reply1);
                        AjaxWorkService.createMembershipDiscount(person.name);
                    } 
                    else {
                        alert ( " Unit Price Cannot be Empty! ");
                    }
                } else {
                    alert ( " Name Cannot be Empty! ");
                }
            }
            //dwr.engine.endBatch();
            
            
            var reply1 = function (data) {
                clearMessages();
                if ( data == " Service Saved Successful! ") {
                    writeMessage ("successReply", data + " at "  + new Date().toLocaleString());
                    fillTable();
                } else {
                    writeMessage ("failureReply", data );
                }
            }
               
            
        
            function clearPerson() {
                viewed = null;
                dwr.util.setValues({ id:null, name:null, unitPrice:null });
                document.getElementById("name").disabled=false;        
                       
            }
            function deletePerson() {
                AjaxAdminService.deleteService(viewed, function(data) {
                    clearMessages();
                    if ( data == " Service Deleted Successful! ") {
                        writeMessage ("successReply", data + " at " + new Date().toLocaleString());
                        fillTable();
                    } else {
                        writeMessage ("failureReply", " You cannot delete this service!");
                    }  
                    fillTable();
                });
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
                                <th> Service/Product </th>
                                <th> Unit Price </th>                                
                                <th></th>
                            </tr>
                        </thead>
                        <form>
                            <tbody id="peoplebody">
                                <tr id="pattern" style="display:none;">
                                    <td><span id="name1"></span></td>
                                    <td><span id="unitPrice1"></span></td>
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
                                    Add / Udpate Services
                                </th>
                            </tr>
                        </thead>
                        <tr>
                            <td> Service/Product:* </td>
                            <td><input id="name" type="text" disabled size="25"/>
                            <br> (eg: printer, dvd burn, potato chips..)
                            </td>
                        </tr> 
                        <tr>
                            <td> Unit Price:* </td>
                            <td><input id="unitPrice" type="text" size="5" onKeyup="isInteger(this.value);"/>
                            <br> (eg: 2, 5.5 etc)
                            </td>
                        </tr> 
                        <tr>
                            <td> 
                            </td>
                            <td>
                                <button value="New" onclick="clearPerson()" >New</button>
                                <button value="Save" id="save" onclick="writePerson()" >Save</button>  
                                <button value="Delete" onclick="deletePerson()" >Delete</button>                                                                
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
