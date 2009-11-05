jq(document).ready(function() {

    fillMembershipTypes();
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

    jq(".editService").click(function () {
        var x = jq(this).attr("id");
        editClicked(x);
    })
    jq('a[rel*=facebox]').facebox({
        loading_image : 'loading.gif',
        close_image   : 'closelabel.gif'
    })
    function search() {
        dwr.util.useLoadingMessage();
        clearMessages();
        AjaxWorkService.getMemberships(dwr.util.getValue("key"), function(people) {
            // Delete all the rows except for the "pattern" row
            dwr.util.removeAllRows("peoplebody", {
                filter:function(tr) {
                    return (tr.id != "pattern");
                }
            });
            // Create a new set cloned from the pattern row
            var person, id;
            people.sort(function(p1, p2) {
                return p1.membershipTypeString.localeCompare(p2.membershipTypeString);
            });
            for (var i = 0; i < people.length; i++) {
                person = people[i];
                id = person.id;
                dwr.util.cloneNode("pattern", {
                    idSuffix:id
                });
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
            person = {
                id:viewed,
                email:null,
                membershipTypeString:null,
                isActive:null
            };
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
        dwr.util.setValues({
            id:null,
            email:null,
            startDate:null,
            endDate:null
        });
        document.getElementById("email").disabled=false;
        document.getElementById("membershipTypeString").disabled=false;
        document.getElementById("isActive").disabled=false;
    }

    jq("form.jqtransform").jqTransform();
})

