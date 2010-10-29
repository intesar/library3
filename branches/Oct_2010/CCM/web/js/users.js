var peopleCache = { };
var viewed = null;
var user = null;
jq(document).ready(function() {
    fillTable();
    function fillTable() {
        //dwr.util.useLoadingMessage();
        AjaxAdminService.getAllUsers(function(people) {
            // Delete all the rows except for the "pattern" row
            dwr.util.removeAllRows("peoplebody", {
                filter:function(tr) {
                    return (tr.id != "pattern");
                }
            });
            // Create a new set cloned from the pattern row
            var person, id;
            //people.sort(function(p1, p2) { return p1.macAddress.localeCompare(p2.macAddress); });
            for (var i = 0; i < people.length; i++) {
                person = people[i];
                id = person.id;
                dwr.util.cloneNode("pattern", {
                    idSuffix:id
                });
                dwr.util.setValue("username1" + id, person.name);
                if ( person.enabled == true  ) {
                    dwr.util.setValue("enabled1" + id, 'Y');
                } else {
                    dwr.util.setValue("enabled1" + id, 'N');
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
        if ( person.role == "admin" ) {
            jq(".role")[0].checked = true;
        } else {
            jq(".role")[0].checked = false;
        }
        if ( person.enabled == true ) {
            jq('.enabledString')[0].checked = true;
        } else {
            jq('.enabledString')[0].checked = false;
        }
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
        if ( jq(".role")[1].checked ) {
            person.role = "admin";
        } else {
            person.role = "employee";
        }
        if ( jq('.enabledString')[1].checked) {
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
        jq.facebox("<h2>"+data+"</h2>")
        fillTable();
    }
    function clearPerson() {
        viewed = null;
        dwr.util.setValues({
            id:null,
            username:null,
            name:null,
            password:null,
            enabledString:null,
            role:null
        });
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