var peopleCache = { };
var viewed = null;
jq(document).ready(function() {
    fillTable();
    function fillTable() {
        //dwr.util.useLoadingMessage();
        AjaxAdminService.getAllServices(function(prodcuts) {
            // Delete all the rows except for the "pattern" row
            dwr.util.removeAllRows("peoplebody", {
                filter:function(tr) {
                    return (tr.id != "pattern");
                }
            });
            // Create a new set cloned from the pattern row
            var person, id;
            //people.sort(function(p1, p2) { return p1.macAddress.localeCompare(p2.macAddress); });
            for (var i = 0; i < prodcuts.length; i++) {
                person = prodcuts[i];
                id = person.id;
                dwr.util.cloneNode("pattern", {
                    idSuffix:id
                });
                dwr.util.setValue("name1" + id, person.name);
                dwr.util.setValue("unitPrice1" + id, person.unitPrice);
                $("pattern" + id).style.display = "";
                peopleCache[id] = person;
            }
            jq('a[rel*=facebox]').facebox({
                loading_image : 'loading.gif',
                close_image   : 'closelabel.gif'
            })
            jq(".editService").click(function () {
                jq(".saveBtn").attr('disabled', 'disabled')
                var x = jq(this).attr("id");
                editClicked(x);
            })
        });
        
    }
    jq(".ischanged").live("change", function() {
        jq(".saveBtn").removeAttr('disabled');
    })
    function editClicked(eleid) {
        // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
        var id = eleid.substring(4);
        var person = peopleCache[id];
        viewed = person.id;
        if ( id == -5 ) {
            jq("#editComputer").click();
            jq(".compFirstPrice")[1].value = person.unitPrice;
            jq(".compFirstMins")[1].value = person.units;
            jq(".compSecondPrice")[1].value = person.saleTwoPrice;
            jq(".compSecondMins")[1].value = person.saleTwoUnits;
        } else {
            jq(".name_")[1].value = person.name;
            jq(".price_")[1].value = person.unitPrice;
            jq(".saleTwoUnits_")[1].value = person.saleTwoUnits;
            jq(".saleTwoPrice_")[1].value = person.saleTwoPrice;
        }
    }
    jq(".saveCompBtn").live("click", function() {
        var product = peopleCache[viewed];
        product.units = jq(".compFirstMins")[1].value;
        product.unitPrice  = jq(".compFirstPrice")[1].value;
        product.saleTwoUnits = jq(".compSecondMins")[1].value;
        product.saleTwoPrice = jq(".compSecondPrice")[1].value;
        AjaxAdminService.saveService(product, reply1);
    })
    jq(".saveBtn").live("click", function() {
        var product = {
            id:viewed,
            name:null,
            units:1,
            unitPrice:null,
            saleTwoUnits:null,
            saleTwoPrice:null
        };
        if ( viewed != null ) {
            product = peopleCache[viewed];
            product.name = jq(".name_")[1].value;
            product.unitPrice  = jq(".price_")[1].value;
            product.saleTwoUnits = jq(".saleTwoUnits_")[1].value;
            product.saleTwoPrice = jq(".saleTwoPrice_")[1].value;
        } else {
            product.name = jq(".name")[1].value;
            product.unitPrice  = jq(".price")[1].value;
            product.saleTwoUnits = jq(".saleTwoUnits")[1].value;
            product.saleTwoPrice = jq(".saleTwoPrice")[1].value;
        }
        
        if ( product.name != null && product.name != '' ) {
            if ( product.unitPrice != null && isInteger(product.unitPrice) && product.unitPrice != "" ) {
                AjaxAdminService.saveService(product, reply1);
            //                AjaxWorkService.createMembershipDiscount(person.name);
            }
            else {
                alert ( " Price should be a number");
            }
        } else {
            alert ( " Name cannot be empty ");
        }
    }   //dwr.engine.endBatch();
    );
    var reply1 = function (data) {
        jq.facebox("<div align='center'><h2>" + data + "</h2></div>")
        fillTable();
    }
    jq("#addService").click(function() {
        viewed = null;
    })
    jq("#deleteBtn").live("click", function() {
        AjaxAdminService.deleteService(viewed, function(data) {
            jq.facebox("<div align='center'><h2>" + data + "</h2></div>")
            fillTable();
        });
    })
    function isInteger(s)
    {
        var i;
        s = s.toString();
        for (i = 0; i < s.length; i++)
        {
            var c = s.charAt(i);
            if (isNaN(c) && c != '.')
            {
                //                alert("This field Should contain Only number");
                return false;
            }
        }
        return true;
    }
    
});