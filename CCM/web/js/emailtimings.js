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

            jq(".deleteLink").click(function () {
                var x = jq(this).attr("id");
                deleteClicked(x);
            })
            jq('a[rel*=facebox]').facebox({
                loading_image : 'loading.gif',
                close_image   : 'closelabel.gif'
            })
        });
    }

    function deleteClicked(eleid) {

        var emailTime = peopleCache[eleid.substring(6)];
        AjaxAdminService.deleteEmailTimePreference(emailTime, function(data) {
            fillTable();
            jq.facebox("<h2>" + data + "</h2>");
        });
    }
    jq("#addBtn").live("click", function() {
        var person = {
            id:null,
            reportTime:null,
            organization:null
        };
        person.reportTime = jq('.reportTime')[1].value;
        AjaxAdminService.saveEmailTimePreference(person, function(data) {
            fillTable();
            jq.facebox("<h2>" + data + "</h2>");
        });
    })

});