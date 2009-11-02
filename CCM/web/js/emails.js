var peopleCache = { };
var viewed = null;

jq(document).ready(function() {
    fillTable();



    function fillTable() {
        //dwr.util.useLoadingMessage("Please Wait, Loading");
        AjaxAdminService.getAllEmailPreference(function(people) {
            // Delete all the rows except for the "pattern" row
            dwr.util.removeAllRows("peoplebody", {
                filter:function(tr) {
                    return (tr.id != "pattern");
                }
            });
            // Create a new set cloned from the pattern row
            var person, id;
            people.sort(function(p1, p2) {
                return p1.emailOrPhone.localeCompare(p2.emailOrPhone);
            });
            for (var i = 0; i < people.length; i++) {
                person = people[i];
                id = person.id;
                dwr.util.cloneNode("pattern", {
                    idSuffix:id
                });
                dwr.util.setValue("username1" + id, person.username);
                dwr.util.setValue("email_or_phone" + id, person.emailOrPhone);
                $("pattern" + id).style.display = "";
                peopleCache[id] = person;
            }
            jq(".editEmail").click(function () {
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
    }



    function writePerson() {
        var person;

        if ( viewed == null ) {
            person = {
                id:viewed,
                username:null,
                emailOrPhone:null,
                serviceProvider:null
            };
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
        dwr.util.setValues({
            id:null,
            username:null,
            emailOrPhone:null,
            serviceProvider:null
        });
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
});