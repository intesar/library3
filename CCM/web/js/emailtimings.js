var peopleCache = { };
var viewed = null;
jq(document).ready(function() {
    fillTable();


    function fillTable() {
        AjaxAdminService.getAllEmailTimePreference(function(people) {
            // Delete all the rows except for the "pattern" row
            dwr.util.removeAllRows("peoplebody", {
                filter:function(tr) {
                    return (tr.id != "pattern");
                }
            });
            // Create a new set cloned from the pattern row
            var person, id;
            people.sort(function(p1, p2) {
                return p1.reportTime - p2.reportTime;
            });
            for (var i = 0; i < people.length; i++) {
                person = people[i];
                id = person.id;
                dwr.util.cloneNode("pattern", {
                    idSuffix:id
                });
                dwr.util.setValue("reporttime" + id, person.reportTime);
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
        var person = {
            id:null,
            reportTime:null,
            organization:null
        };
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

    jq("form.jqtransform").jqTransform();
});