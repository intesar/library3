var _report;
jq(document).ready(function() {
    jq('#addServiceDiv').live("click", function() {
        populateSystemNos();
    })
    jq('a[rel*=facebox]').facebox({
        loading_image : 'loading.gif',
        close_image   : 'closelabel.gif'
    })
    fillTable();
    function search() {
        if ( validateEmail(dwr.util.getValue("key"), true, true) )  {
            clearMessages();
            AjaxWorkService.getUserWithPic(dwr.util.getValue("key"), reply2);
        }
    }
    var reply2 = function(customer) {
        clearMessages();
        dwr.util.setValue("image", null);
        if ( customer.id == null ) {
            writeMessage ("failureReply",  "No Customer found with the Email, Please create Customer Profile at Users menu ");
        } else {
            if ( customer.image == null ) {
                writeMessage ("successReply", "No Image Available for " + customer.email + " please upload an image!");
            } else {
                dwr.util.setValues(customer);
            }
            writeMessage("successReply", " Customer Name : " + customer.name);
        }
    }

    var peopleCache = { };
    var viewed = null;
    var systems1 = new Array();
    var systemLength = null;
    var services = { };
    var usedSystemList = { };
    function fillTable() {
        //dwr.util.useLoadingMessage("Please Wait, Loading...");
        AjaxWorkService.getActiveSystems(function(systems) {
            usedSystemList = { };
            // Delete all the rows except for the "pattern" row
            dwr.util.removeAllRows("peoplebody", {
                filter:function(tr) {
                    return (tr.id != "pattern");
                }
            });
            // Create a new set cloned from the pattern row
            var person, id;

            systems.sort(function(p1, p2) {
                return p1.name - p2.name;
            });
            systemLength = systems.length;
            for (var i = 0; i < systems.length; i++) {
                person = systems[i];
                if ( person.enabled == true ) {
                    id = person.id;
                    dwr.util.cloneNode("pattern", {
                        idSuffix:id
                    });
                    dwr.util.setValue("name" + id, person.name);
                    var email = person.currentUserEmail;
                    if ( email != null && email.length > 0 ) {
                        var maxLength = email.toString().indexOf("@");
                        maxLength = maxLength <= 14 ? maxLength : 14;
                        dwr.util.setValue("currentUserEmail" + id, email.toString().substring(0,maxLength));
                        dwr.util.setValue("startTimeString1" + id, person.startTimeString);
                        jq("#detailsDiv" + id).show();
                        jq("#assignDiv" + id).hide();
                    }
                    $("pattern" + id).style.display = "";
                    peopleCache[id] = person;
                    systems1[i] = person;
                    if(person.isAvailable == true){
                        document.getElementById('deta'+id).disabled=true;
                    } else {
                        document.getElementById('deta'+id).disabled=false;
                        usedSystemList[i] = person;
                    }
                }
            }
        });

        AjaxWorkService.getAllServices(function(data) {
            DWRUtil.removeAllOptions("services");
            DWRUtil.addOptions("services", data, "name", "name" );
            services = data;
        });
    }

    //    jq('.systemRow').live("click", function() {
    //        var id = jq(this).parentNode.id;
    //        assignSystem(id);
    //    })

    jq('.assign').live('click', function() {
        //assignSystem(this.id);
        // set all values in assignUserDiv
        //load div in facebox
        var id = this.id.substring(4);
        var system = peopleCache[id];
        jq('.computerNo').text(system.id);
        jq('.computerName').text(system.name);
        jq('.name_').value='';
        jq.facebox(jq("#assignUserDiv").html());
    })
    jq('.assignToUserBtn').live('click', function() {
        assignSystem(jq('.computerNo')[1].innerHTML, jq('.name_')[1].value);
    })
    function assignSystem(computerNo, leaseHolder) {
        //var system = peopleCache[computerNo];
        //var leaseHolder = user;//"intesar@ymail.com";//dwr.util.getValue("key");
        if ( validateEmail(leaseHolder, true, true) ) {
            AjaxWorkService.leaseSystem(computerNo, leaseHolder, function(data) {
                if ( data == 'Assigned Successfully!' || true) {
                    jq('.close').click();
                    fillTable();
                } else {
                    alert('error')
                }
            } );
        }
    }
    var paidId = null;
    jq(".detail").live("click", function() {
        fetchDetail(jq(this).attr("id"))
    })
    function fetchDetail(eleid) {
        var system = null;
        var systemName = null;
        if (!isNaN(eleid)   ) {
            systemName = parseInt(eleid);
            for ( var i = 0; i <= systems1.length; i++ ) {
                var s = systems1[i];
                if ( s != null && s.name == systemName ) {
                    system = s;
                }
            }
        } else {
            system = peopleCache[eleid.substring(4)];
        }
        paidId = system.id;
        AjaxWorkService.getSystemLease(system.id, function(lease){
            dwr.util.removeAllRows("detailbody", {
                filter:function(tr) {
                    return (tr.id != "detail");
                }
            });
            var total = 0;
            for (var i = 0; i < lease.length; i++) {
                systemLease = lease[i];
                id = systemLease.id;
                dwr.util.cloneNode("detail", {
                    idSuffix:id
                });
                dwr.util.setValue("service" + id, systemLease.service);
                dwr.util.setValue("startTimeString" + id, systemLease.startTimeString);
                dwr.util.setValue("totalMinutesUsed" + id, systemLease.totalMinutesUsed);
                dwr.util.setValue("payableAmount" + id, systemLease.payableAmount);
                $("detail" + id).style.display = "";
                total += systemLease.payableAmount;
            }
            document.getElementById("paidButton").disabled = false;
            populateSystemNos ();
            dwr.util.setValue("systemNos", system.name);
            jq.facebox(jq("#detailDiv").html());
            jq(".totalPayableAmount")[1].value = total;
            jq(".paidAmount")[1].value = total;
            jq(".username_")[1].value = system.currentUserEmail;
            jq(".computerNo_")[1].value= system.id;
        });
        
    }
    function addService() {
        var s = dwr.util.getValue ("services");
        var u = dwr.util.getValue ("units");
        var e = dwr.util.getValue ("systemNos");
        var p = dwr.util.getValue ("payableAmount1");
        var a = p;
        if ( u != null && u > 0 && p != null && p > 0) {
            AjaxWorkService.addService(s,u,e,p,'',a, replyService);
        } else {
            alert(  ' units or payable amount cannot be null!');
        }
    }
    var replyService = function (data) {
        clearMessages();
        if (  data == 'Service Added Successfully') {
            writeMessage("successReply", data + " to : " + dwr.util.getValue("systemNos") + " at " + new Date().toLocaleString());
            if ( dwr.util.getValue("systemNos") != 'Walk-in Customer') {
                fetchDetail (  dwr.util.getValue("systemNos"));
            }
            clearPerson();
        } else {
            writeMessage("failureReply", data);
        }
    }
    jq('.paidButton').live("click", function() {
        paid();
    })
    function paid () {
        var system = peopleCache[paidId];
        //        document.getElementById("paidButton").disabled = false;
        //        clearMessages();
        AjaxWorkService.chargePayment(system.id, function(data) {
            if ( data == 'Payment Successful!' ) {                
                fillTable();
                jq.facebox(data);

            //                dwr.util.removeAllRows("detailbody", {
            //                    filter:function(tr) {
            //                        return (tr.id != "detail");
            //                    }
            //                });
            //                document.getElementById("paidButton").disabled = true;
            } else {
        //                writeMessage("failureReply", data);
        }
        } );
    }
    function clearPerson() {
        dwr.util.setValues({
            payableAmount1:"",
            units:""
        });
    }

    function isInteger(s)
    {
        var i;
        s = s.toString();
        for (i = 0; i < s.length; i++)
        {
            var c = s.charAt(i);
            if (isNaN(c))
            {
                alert("This field Should contain Only number");
                return false;
            }
        }
        return true;
    }

    function updatePrice() {
        var s = dwr.util.getValue("services");
        var u = dwr.util.getValue("units");
        for ( var i = 0; i < services.length; i++ ) {
            if ( services[i].name == s ) {
                dwr.util.setValue("payableAmount1", services[i].unitPrice * u );
            }
        }
    }

    function populateSystemNos () {
        DWRUtil.removeAllOptions("systemNos");
        var dummySystem = {
            name:'Walk-in Customer',
            id:'Walk-in Customer'
        };
        usedSystemList[usedSystemList.lenght+1] = dummySystem;
        DWRUtil.addOptions("systemNos", usedSystemList, "name", "name" );
    }

    getReport();
    function getReport() {
        var now = new Date();
        var date = now.getFullYear() + "-" + (now.getMonth()+1) + "-" + now.getDate();
        AjaxAdminService.getReport(date,date, function( data) {
            reportReply ( data)
        } );
    }
    function reportReply (report) {
        var _data = new Array();
        var _labels = new Array();
        var totalUnits = 0;
        var totalPayable = 0;
        var totalPaid = 0;
        var html = '';
        for ( var i = 0; i < report.length; i++) {
            _data[i] = report[i][3];
            _labels[i] = report[i][0];
            html += '<tr><td>' + report[i][0] + '</td><td>' + report[i][1]+ '</td><td>' + report[i][2]+ '</td><td>' + report[i][3] + '</td></tr>';
            totalUnits += report[i][1];
            totalPayable += report[i][2];
            totalPaid += report[i][3];
        }
        jq('#serviceBody').html(html);
        foot = '<tr><td>' + '' + '</td><td>' + totalUnits + '</td><td>' + totalPayable + '</td><td>' + totalPaid + '</td></tr>';
        jq('#serviceFoot').html(foot);
        displayChart(_data, _labels);        
    }
    
    function displayChart(_data, _labels) {
        var api = new jGCharts.Api();
        jQuery('<img>')
        .attr('src', api.make({
            data : _data,
            type : 'p',
            size:'260x200',
            title       : 'Today Sale', //default false
            title_color : 'a98147',
            title_size  : 20,
            axis_labels : _labels
        }))
        .appendTo("#graphDiv");
    }
})
