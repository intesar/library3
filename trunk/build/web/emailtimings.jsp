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
                AjaxAdminService.getAllEmailTimePreference(function(people) {
                    // Delete all the rows except for the "pattern" row
                    dwr.util.removeAllRows("peoplebody", { filter:function(tr) {
                            return (tr.id != "pattern");
                        }});
                    // Create a new set cloned from the pattern row
                    var person, id;
                    people.sort(function(p1, p2) {
                        return p1.reportTime - p2.reportTime;
                    });
                    for (var i = 0; i < people.length; i++) {
                        person = people[i];
                        id = person.id;
                        dwr.util.cloneNode("pattern", { idSuffix:id });
                        dwr.util.setValue("reporttime" + id, person.reportTime);     
                        $("pattern" + id).style.display = "";
                        peopleCache[id] = person;
                    }
                });
            }
        
            function deleteClicked(eleid) {               
                
                var emailTime = peopleCache[eleid.substring(6)];
                //alert ( emailTime.id );
                AjaxAdminService.deleteEmailTimePreference(emailTime, function(data) {
                    if ( data != "Deleted Successfully") {
                        writeMessage("successReply", "Operation Successful!");
                        fillTable();
                    }
                    else {
                        writeMessage ( "failureReply", "Operation Failed, Please try again!" );
                        
                    }
                });
            }
            function writePerson() {
                var person = { id:null, reportTime:null, organization:null};
                dwr.util.getValues(person);        
                AjaxAdminService.saveEmailTimePreference(person, function(data) {
                    if ( data == "Added Successfully!") {
                        writeMessage("successReply", "Operation Successful!");
                        fillTable();
                    }
                    else {
                        writeMessage ( "failureReply", "Operation Failed, Please try again!" );
                    }
                });
            }
        </script>
        
        <jsp:include page="table_style.jsp" ></jsp:include>
    </head>
    <body>
        
        <jsp:include page="include.jsp" />
        
        
        <table align="center" >
            <thead>
                <tr>
                    <th>Daily Report</th>
                    <th>Times</th>
                    <th></th>
                </tr>
            </thead>
            <tr>
                <td>
                    Report Time
                </td>
                <td>
                    <select name="reportTime">
                        <option value="0"> midnight</option>
                        <option value="100">01:00 am</option>
                        <option value="200">02:00 am</option>
                        <option value="300">03:00 am</option>
                        <option value="400">04:00 am</option>
                        <option value="500">05:00 am</option>
                        <option value="600">06:00 am</option>
                        <option value="700">07:00 am</option>
                        <option value="800">08:00 am</option>
                        <option value="900">09:00 am</option>
                        <option value="1000">10:00 am</option>
                        <option value="1100">11:00 am</option>
                        <option value="1200">noon</option>
                        <option value="1300">01:00 pm</option>
                        <option value="1400">02:00 pm</option>
                        <option value="1500">03:00 pm</option>
                        <option value="1600">04:00 pm</option>
                        <option value="1700">05:00 pm</option>
                        <option value="1800">06:00 pm</option>
                        <option value="1900">07:00 pm</option>
                        <option value="2000">08:00 pm</option>
                        <option value="2100">09:00 pm</option>
                        <option value="2200">10:00 pm</option>
                        <option value="2300">11:00 pm</option>
                    </select>
                </td>
                
                <td>
                    <input type="button" value="Add" onclick="writePerson()"/>  
                </td>
                
            </tr>
        </table>
        <br>
        
        <table align="center">
            
            <tbody id="peoplebody">
                <tr id="pattern" style="display:none;">
                    <td><span id="reporttime">reportTime</span></td>
                    <td>
                        <input id="remove" type="button" value="Delete" onclick="deleteClicked(this.id)"/>                        
                    </td>
                </tr>
            </tbody>
        </table>
        
        <script type="text/javascript">
            window.onload = fillTable;
        </script>
        <br>
        <br>
        <br> <br>
        <br>
        <br> <br>
        <br>
        <br> <br>
        <br>
        
        <jsp:include page="emailtimings_help.jsp" />
        <jsp:include page="copyright.jsp" />
        
    </body>
    
</html>
