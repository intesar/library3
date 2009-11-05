var peopleCache = { };
var viewed = null;

  
jq(document).ready(function() {
    fillTable();
    
    function fillTable() {
        dwr.util.useLoadingMessage();
        AjaxWorkService.getAllMembershipTypes(function(people) {
            // Delete all the rows except for the "pattern" row
            dwr.util.removeAllRows("peoplebody", {
                filter:function(tr) {
                    return (tr.id != "pattern");
                }
            });
            // Create a new set cloned from the pattern row
            var person, id;
            people.sort(function(p1, p2) {
                return p1.name.localeCompare(p2.name);
            });
            for (var i = 0; i < people.length; i++) {
                person = people[i];
                id = person.id;
                dwr.util.cloneNode("pattern", {
                    idSuffix:id
                });
                dwr.util.setValue("name1" + id, person.name);
                dwr.util.setValue("fee1" + id, person.fee);
                $("pattern" + id).style.display = "";
                peopleCache[id] = person;
            }
            jq('a[rel*=facebox]').facebox({
                loading_image : 'loading.gif',
                close_image   : 'closelabel.gif'
            })
        });
        document.getElementById("name").disabled=true;
    }

    function fillDiscounts(id) {
        //alert ( id );
        dwr.util.useLoadingMessage();
        AjaxWorkService.getMembershipDiscountses(id, function(people) {
            // Delete all the rows except for the "pattern" row
            dwr.util.removeAllRows("discountBody", {
                filter:function(tr) {
                    return (tr.id != "discountRow");
                }
            });
            // Create a new set cloned from the pattern row
            var person, id;
            people.sort(function(p1, p2) {
                return p1.service.localeCompare(p2.service);
            });
            for (var i = 0; i < people.length; i++) {
                person = people[i];
                id = person.id;
                dwr.util.cloneNode("discountRow", {
                    idSuffix:id
                });
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
            person = {
                id:viewed,
                name:null,
                fee:null,
                daysValidFor:null
            };
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
        dwr.util.setValues({
            id:null,
            name:null,
            fee:null,
            daysValidFor:null
        });
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

    jq("form.jqtransform").jqTransform();
})