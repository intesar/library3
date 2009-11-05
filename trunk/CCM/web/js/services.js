var peopleCache = { };
var viewed = null;

jq(document).ready(function() {
    fillTable();



    function fillTable() {
        //dwr.util.useLoadingMessage();
        AjaxAdminService.getAllServices(function(people) {
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
                dwr.util.setValue("name1" + id, person.name);
                dwr.util.setValue("unitPrice1" + id, person.unitPrice);
                $("pattern" + id).style.display = "";
                peopleCache[id] = person;
            }

            jq(".editService").click(function () {
                var x = jq(this).attr("id");
                editClicked(x);
            })
            jq('a[rel*=facebox]').facebox({
                loading_image : 'loading.gif',
                close_image   : 'closelabel.gif'
            })
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
            person = {
                id:viewed,
                name:null,
                unitPrice:null
            };
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
        dwr.util.setValues({
            id:null,
            name:null,
            unitPrice:null
        });
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

    jq("form.jqtransform").jqTransform();


});