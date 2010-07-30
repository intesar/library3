var peopleCache = { };
var viewed = null;
jq(document).ready(function() {
    fillMembershipTypes();
    function fillMembershipTypes() {
        dwr.util.useLoadingMessage();
        AjaxWorkService.getAllMembershipTypes(function(data) {
            DWRUtil.removeAllOptions("membershipTypeString");
            DWRUtil.addOptions("membershipTypeString", data, "name", "name" );
        });
    }
    getMembers();
    function getMembers() {
        AjaxWorkService.getMembers(function(people) {
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
                if ( person.isActive == 1 )
                    dwr.util.setValue("isActive1" + id, "Yes");
                else
                    dwr.util.setValue("isActive1" + id, "No");
                dwr.util.setValue("expirationDateString1" + id, person.expirationDateString);
                $("pattern" + id).style.display = "";
                peopleCache[id] = person;
            }
            jq(".editMember").click(function () {
                var x = jq(this).attr("id");
                editClicked(x);
            })
            jq("#addNewMember").click(function() {
                clearPerson();
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
    jq(".saveBtn").live("click", function() {
        writePerson();
    })
    function writePerson() {
        var person = {
            id:viewed,
            email:null,
            membershipTypeString:null,
            isActive:1
        }
        person.email = jq('.email')[1].value;
        person.membershipTypeString = jq('.membershipTypeString')[1].value;
        if (  validateEmail(person.email, true, true) ) {
            AjaxWorkService.saveMembership(person, reply1);
        }
    }
    var reply1 = function (data) {
        clearMessages();
        if ( data == "Saved Successfully!") {
            jq('.showSuccessMessageDiv').click();
            getMembers();
        } else {
            alert(data);
        }
    }
    function clearPerson() {    
    }
    jq("form.jqtransform").jqTransform();
})

