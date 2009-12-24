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
                //                dwr.util.setValue("username1" + id, person.username);
                dwr.util.setValue("email_or_phone" + id, person.emailOrPhone);
                $("pattern" + id).style.display = "";
                peopleCache[id] = person;
            }
             
            jq('a[rel*=facebox]').facebox({
                loading_image : 'loading.gif',
                close_image   : 'closelabel.gif'
            })
        });
    }


    jq("#saveEmailBtn").live("click", function() {
        var _email = jq('.email')[1].value;
        if ( !validateEmail(_email, true, true) ) {
            
        } else {
            var obj = {
                id:null,
                username:'na',
                emailOrPhone:_email,
                serviceProvider:'email'
            };
            AjaxAdminService.saveEmailPreference(obj, function(data) {
                fillTable();
                jq.facebox("<h2>" + data + "</h2>");
            });
        }
    })

    jq("#savePhoneBtn").live("click", function() {
        var _phone = jq('.phone')[1].value;
        if ( _phone.length < 10 ) {
            alert("Invalid phone no.");
        } else {
            var obj = {
                id:null,
                username:'na',
                emailOrPhone:_phone,
                serviceProvider:jq('.serviceProvider')[1].value
            };
            AjaxAdminService.saveEmailPreference(obj, function(data) {
                fillTable();
//                jq.facebox("<h2>" + data + "</h2>");
            });
        }
    })
   
    jq(".deleteEmail").live("click", function() {
        AjaxAdminService.deleteEmail(jq(this).attr('id').substring(11), function(data) {
//            jq.facebox("<h2>" + data + "</h2>");
            fillTable();
        });
    })
    
});