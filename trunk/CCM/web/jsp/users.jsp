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
        <title>Managers</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/blue/style.css" type="text/css" id="" media="print, projection, screen" />
        <script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        <link href="../css/facebox/facebox.css" media="screen" rel="stylesheet" type="text/css"/>
        <script src="../js/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            var jq = jQuery.noConflict();
        </script>

        <script type="text/javascript" src="../js/email_validation.js"></script>
        <script type='text/javascript' src='../dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>

        <script type="text/javascript">
            //jQuery.noConflict();
            var peopleCache = { };
            var viewed = null;
            var user = null;
            jq(document).ready(function() {

                fillTable();
                
        
                function fillTable() {
                    //dwr.util.useLoadingMessage();
                    AjaxAdminService.getAllUsers(function(people) {
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
                            dwr.util.setValue("username1" + id, person.name);
                            if ( person.enabled == true  ) {
                                dwr.util.setValue("enabled1" + id, 'YES');
                            } else {
                                dwr.util.setValue("enabled1" + id, 'NO');
                            }
                            dwr.util.setValue("role1" + id, person.role);
                        
                        
                            $("pattern" + id).style.display = "";
                            peopleCache[id] = person;
                            user = person;
                        }

                        jq(".editManager").click(function () {
                            var x = jq(this).attr("id");
                            editClicked(x);
                        })
                        jq('a[rel*=facebox]').facebox({
                            loading_image : 'loading.gif',
                            close_image   : 'closelabel.gif'
                        })
                        
                    });
                    
                }
        
                function editClicked(eleid) {
                    // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
                    var person = peopleCache[eleid.substring(4)];
                    viewed = person.id;
                    dwr.util.setValues(person);
                    jq("#username").attr("disabled","disabled");
                }
        
                function writePerson() {
                    var person;
                    if ( viewed == null ) {
                        person = user;
                        person.id = null;
                        person.img = null;
                        //person.email = null;
                        person.homePhone = null;
                        person.mobilePhone = null;
                        person.otherPhone = null;
                        person.street = null;
                        person.city = null;
                        person.zipcode = null;
                        person.passportNo = null;
                        person.voterId = null;
                        person.panCardNo = null;
                        person.comments = null;
                        person.gender = null;
                    } else {
                        person = peopleCache[viewed];
                    }
                    //dwr.util.getValues(person);
                    person.name = jq(".name")[1].value;
                    person.username = jq(".username")[1].value;
                    person.password = jq(".password")[1].value;
                    person.role = jq(".role")[1].value;
                    if ( jq('.enabledString')[1].value == "yes") {
                        person.enabled = true;
                    } else {
                        person.enabled = false;
                    }
        
                    if ( validateEmail(person.username, true, true) ) {
                        if ( person.password != "" ) {
                            AjaxAdminService.saveUsers(person, reply1);
                        }
                        else {
                            alert ( " Password is required field! ");
                        }
                    }
                
                }
            
                var reply1 = function (data) {
                    clearMessages();
                    if ( data == 'Operation succesful!') {
                        writeMessage ("successReply", " Saved/Updated User at " + new Date().toLocaleString() );
                        fillTable();
                    } else {
                        writeMessage ("failureReply", data );
                    }
                }
        
                function clearPerson() {
                    viewed = null;
                    dwr.util.setValues({ id:null, username:null, name:null, password:null, enabled:null, role:null });
                    jq("#username").removeAttr("disabled");
                }
            
                jq("#managers").tablesorter();

                jq("#saveManager").live("click", function() {
                    writePerson();
                })

                jq("#createNewManager").click(function() {
                    clearPerson();
                })
            })
        </script>


    </head>
    <body style="background-image:url('../images/body_background.png'); font-family:arial">

        <div align="center">
            <jsp:include page="include.jsp" />
            <!-- <h2 align="center">Cyber Cafe Users  </h2>                         -->
            <div align="center" style="width:700px">
                <div style="font-size:12px" align="right">
                    <a href="#managerProfile"  rel="facebox" id="createNewManager">Create a new manager</a>
                </div>
                
                <table id="managers" cellspacing="1" class="tablesorter">

                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Active</th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody id="peoplebody">
                        <tr id="pattern" style="display:none;">
                            <td><span id="username1">name</span></td>
                            <td><span id="role1">role</span></td>
                            <td><span id="enabled1">enabled</span></td>
                            <td>
                                <a href="#managerProfile"  rel="facebox"  class="editManager" id="edit"> edit </a>
                            </td>
                        </tr>
                    </tbody>

                </table>
            </div>

            <div style="display:none" id="managerProfile">
                <table>
                    <thead>
                        <tr>
                            <th></th>
                            <th>

                            </th>
                        </tr>
                    </thead>
                    <tr>
                        <td>  Name:*  </td>
                        <td><input id="name" class="name" type="text" size="30"/></td>
                    </tr>
                    <tr>
                        <td> Email:* </td>
                        <td><input id="username" class="username" type="text" size="30"/></td>
                    </tr>
                    <tr>
                        <td> Password:* </td>
                        <td><input id="password" class="password" type="password" size="30"/></td>
                    </tr>
                    <tr>
                        <td> Active </td>
                        <td><select name="enabledString" class="enabledString">
                                <option>yes</option>
                                <option>no</option>
                            </select>
                            (To Cancel Account set Active:No)</td>
                    </tr>
                    <tr>
                        <td>  Role </td>
                        <td><select name="role" class="role">
                                <option value="admin">Administrator</option>
                                <option value="employee">Cashier</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="button" value="Save" id="saveManager" />
                        </td>
                    </tr>
                </table>
            </div>

        </div>
    </body>

    <jsp:include page="users_help.jsp" />
    <jsp:include page="copyright.jsp" />
</html>
