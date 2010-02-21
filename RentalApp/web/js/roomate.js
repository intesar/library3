dojo.require("dojox.grid.DataGrid");
dojo.require("dojo.data.ItemFileWriteStore");
//dojo.require("dijit.Tooltip")
dojo.require("dijit.Dialog");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.ValidationTextBox");
//dojo.require("dijit.form.CurrencyTextBox");
dojo.require("dijit.form.SimpleTextarea");
dojo.require("dijit.form.ComboBox");
dojo.require("dijit.form.NumberTextBox");
dojo.require("dijit.form.Form");
dojo.require("dijit.form.CheckBox");
dojo.require("dojox.analytics.Urchin");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.layout.BorderContainer");

var widget;
var geoip_country_code;
var geoip_country_name;
var geoip_city;
var geoip_region;
var geoip_region_name;
var geoip_postal_code;
var current_row;
var post_id;
var START = 0
var MAX = 10
var SIZE = 10
var TOTAL
var gridInstantiated = false
var mode = 'normal';
var email_1 =''
var postId_1 =''
var itemFileWriteStore = null;

dojo.addOnLoad(
    function(){
        //        geoip_country_code = geoip_country_code();
        //        geoip_country_name = geoip_country_name();
        //        geoip_city = geoip_city();
        //        geoip_region = geoip_region();
        //        geoip_region_name = geoip_region_name();
        //        geoip_postal_code = geoip_postal_code();
        //        // setting default city and zipcode
        //        if (geoip_postal_code != null && geoip_postal_code.length > 0 ) {
        //            document.getElementById('keyword').value = geoip_postal_code.substring(0,3)+"**";
        //            search();
        //        }
        // event binding

        bindObjects ()
        urlCheck()
    }
    );

function urlCheck() {
    var url = location.href;
    if ( url.match('operation=update')) {
        var _email = url.split("&")[1].split("=")[1]
        email_1 = _email
        var _id = url.split("&")[2].split("=")[1]
        RoomateAjaxService.searchByEmailAndId(_email, _id, function(data) {
            if ( data[0] == null ) {
                alert("No records found!")
                return;
            }
            dwr.util.setValues(data[0]);
            dijit.byId('post').show();
            mode = 'update'
        })
    } else if (url.match('operation=delete')) {
        var _email1 = url.split("&")[1].split("=")[1]
        var _id1 = url.split("&")[2].split("=")[1]
        email_1 = _email1
        postId_1 = _id1
        RoomateAjaxService.searchByEmailAndId(_email1, _id1, function(data) {
            if ( data.list[0] == null ) {
                alert("No records found!")
                return;
            }
            displayResults(data)
            mode = 'delete'
            displayDetailGrid(0)

        // hide save & clear button and show delete button
        })

    } else if ( url.match('id=') ) {
        var id = url.split("=")[1]
        RoomateAjaxService.searchById(id, function(data) {
            if ( data.list[0] == null ) {
                return;
            }
            displayResults(data)
            displayDetailGrid(0)
        });
    } else if ( url.match("searchKey=")) {
        dojo.byId('keyword').value = url.split("=")[1]
        search()
    } else if ( url.match("newPost")) {
        dijit.byId('post').show()
    } else if (url.match("contactUs")) {
        dijit.byId('contactUsDiv').show()
    }else if ( url.match("advancedSearch")) {
        dijit.byId("advancedSearchDiv").show()
    }
}
function bindObjects () {
    dojo.byId('loadingDiv').style.display='none'
    dojo.byId('bodyDiv').style.display='block'
    dojo.byId('topDiv').style.display='block'
    dojo.byId('copyright').style.display='block'
    new dojox.analytics.Urchin({
        acct:"UA-6815540-4"
    });
    dojo.connect(dijit.byId("keyword"), 'onKeyUp', function(evt) {
        if ( evt.keyCode == dojo.keys.ENTER)  {
            search();
        }
    });
    dojo.connect(dijit.byId("keywordNoResult"), 'onKeyUp', function(evt) {
        if ( evt.keyCode == dojo.keys.ENTER)  {
            searchAgain();
        }
    });
    dojo.connect(dijit.byId("searchButton"), 'onClick', searchDelegate);
    dojo.connect(dojo.byId("prevPagination"), 'onclick', searchPrev);
    dojo.connect(dojo.byId("nextPagination"), 'onclick', searchNext);
    dojo.connect(dojo.byId("contactUsLink"), 'onclick', function() {
        dijit.byId('contactUsDiv').show()
        parent.location.hash = 'contactUs'
    })
    dojo.connect(dijit.byId("submitContactUs"), 'onClick', submitContactUs)
    dojo.connect(dojo.byId("newPostLink"), 'onclick', function() {
        dijit.byId('post').show()
        parent.location.hash = 'newPost'
    })
    dojo.connect(dojo.byId("reportAbuseLink"), 'onclick', function() {
        dijit.byId('reportAbuseDiv').show()
    })
    dojo.connect(dijit.byId("newPostClearButton"), 'onClick', clear);
    dojo.connect(dijit.byId("newPostSaveButton"), 'onClick', post);
    dojo.connect(dijit.byId("searchAgainButton"), 'onClick', searchAgain);
    dojo.connect(dojo.byId("prev"), 'onclick', prevPost);
    dojo.connect(dojo.byId("next"), 'onclick', nextPost);
    dojo.connect(dijit.byId("postSuccessMsgButton"), 'onClick', function() {
        dijit.byId('postSuccessMsgDiv').hide();
    });
    dojo.connect(dijit.byId("postReplyButton"), 'onClick', sendMessage)
    // dwr message
    dwr.util.useLoadingMessage();
    dojo.connect(dijit.byId("deleteButton"), 'onClick', function() {
        RoomateAjaxService.deletePost(postId_1, email_1, function(data) {
            if ( data == 1) {
                dijit.byId('postDetails').hide()
            } else if ( data == -1) {
                alert('Error, please try again!')
            }
            
        })
    })
    dojo.connect(dijit.byId("subscribeButton"), 'onClick', function() {
        var subscribeEmail = dijit.byId("subscribeEmail").value
        var subscribeKeywords = dijit.byId("subscribeKeyword").value
        RoomateAjaxService.subscribeUser(subscribeEmail, subscribeKeywords, function(data) {
            if ( data == 1) {
                dijit.byId('noResultDiv').hide()
            } else if ( data == -1) {
                alert('Error, please try again!')
            }
        })
    })
    //dojo.connect(dojo.byId("searchAdvLink"), 'onclick', searchAdvShow)
    dojo.connect(dijit.byId("rentalType"), 'onChange', rentalTypeChange)
    // tool tips
    new dijit.Tooltip({
        connectId: ["whyEmail"],
        label: "Email has links for activating, updating & removing this post"
    })
    new dijit.Tooltip({
        connectId: ["whyPhone"],
        label: "Optional, visible to everyone!"
    })
    new dijit.Tooltip({
        connectId: ["whyRent"],
        label: "Approximate, Inclusive of all utilities!"
    })
    new dijit.Tooltip({
        connectId:["whyStreet"],
        label: "Street or Nearest intersection or Landmark"
    })
    new dijit.Tooltip({
        connectId:["whyEmailAlert"],
        label: "You can cancel email alerts at anytime."
    })
    //report abuse
    dojo.connect(dijit.byId("reportAbuseButton"), 'onClick', reportAbuse)
    dojo.connect(dijit.byId("searchAdvButton"), 'onClick', searchAdvFtn)

}
function searchDelegate() {
    START = 0
    MAX = SIZE
    search()
}
function search() {
    var keyword = document.getElementById('keyword').value;
    if ( keyword == null || keyword.length <= 0 ) return
    dwr.util.useLoadingMessage('searching ...')
    RoomateAjaxService.search(keyword, START, MAX, displayResults);
    parent.location.hash = ''
}
function searchAgain() {
    dojo.byId('keyword').value = dojo.byId('keywordNoResult').value;
    START = 0
    MAX = SIZE
    search()
    dijit.byId('noResultDiv').hide();
}
function displayGrid() {
    var layout =  [
    {
        field:"addressLine",
        name: "Address",
        width: "20%"
    },
    {
        field: "city",
        name: "City",
        width: "20%"
    },
    {
        field: "zipcode",
        name: "Zipcode",
        width: "10%"
    },
    {
        field: "rentalType",
        name: "Type",
        width: "10%"
    },
    {
        field: "rent",
        name: "Rent",
        width: "10%"
    },
    {
        field: "phone",
        name: "Phone",
        width: "10%"
    },
    {
        field: "date",
        name: "Date",
        width: "10%"
    }
    ];
    var cfg = {
        query: {
            city: '*'
        },
        id: "mytable",
        jsId: "mytable",
        widgetId: "mytable",
        clientSort: true,
        rowsPerPage: 15,
        rowSelector: '40px',
        structure: layout,
        noDataMessage:'<H2> No Results to display </H2><H3> Add more cities and zipcodes (e.g san jose san francisco 95050 95040) </H3>',
        style: "height:85%; font-size: 14px;"
    };
    widget = new dojox.grid.DataGrid(cfg, dojo.byId("container"));
    widget.startup();
}
function registerDetailGrid() {
    dojo.connect(widget, "onClick", function(e) {
        //alert(e.rowIndex)
        var row = e.rowIndex        
        displayDetailGrid(row)
    });
}
function displayDetailGrid(row) {
    var post = widget.store._arrayOfAllItems[row];
    if ( post == null) return;
    current_row = row;
    post_id = post.id;
    dijit.byId("postDetails").hide();
    dijit.byId("postDetails").setAttribute("title", "ID # " + post.id)
    dojo.byId("postedByDetail").innerHTML = post.postedBy
    dojo.byId("dateDetail").innerHTML = '' + post.date + ''
    dojo.byId("phoneDetail").innerHTML = '' + post.phone + ''
    dojo.byId("rentalTypeDetail").innerHTML = '' + post.rentalType + ''
    var x = post.rentalType == 'Commercial' ? post.area + 'Sq Ft' : post.beds > '0' ? post.beds + '+' + ' Bed' : post.beds == 0 ? 'Studio+' : ''
    dojo.byId("typeDetailMore").innerHTML = x
    dojo.byId("addressLineDetail").innerHTML = '' + post.addressLine + ''
    dojo.byId("cityDetail").innerHTML = '' + post.city + ''
    dojo.byId("zipcodeDetail").innerHTML = '' + post.zipcode + ''
    dojo.byId("rentDetail").innerHTML = '' + post.rent + ''
    dojo.byId("currencyDetail").innerHTML = post.currency
    
    dojo.byId("commentDetail").innerHTML = post.comment
    if ( mode == 'delete') {
        dojo.byId("deleteButtonDiv").style.display = 'block'
        dojo.byId("commentDiv").style.display = 'none'
        dojo.byId("SendMessageDiv").style.display = 'none'
        dojo.byId("prevNextDiv").style.display = 'none'
        mode = 'postDelete'
    } else if ( mode == 'postDelete') {
        dojo.byId("deleteButtonDiv").style.display = 'none'
        dojo.byId("commentDiv").style.display = 'block'
        dojo.byId("SendMessageDiv").style.display = 'block'
        dojo.byId("prevNextDiv").style.display = 'block'
        mode = ''
    }
    dijit.byId("postDetails").show();
    parent.location.hash = "id=" + post.id;
}
function displayResults(posts) {
    if ( posts.total == 0 ) {
        dojo.byId('myFilter').style.display='none'
        dojo.byId('priceFilter').style.display='none'
        dojo.byId('typeFilter').style.display='none'
        dojo.byId('distanceFilter').style.display='none'
    }
    else {
        dojo.byId('myFilter').style.display='block'
        dojo.byId('priceFilter').style.display='block'
        dojo.byId('typeFilter').style.display='block'
        dojo.byId('distanceFilter').style.display='block'
    }
    if ( !gridInstantiated ) {
        displayGrid()
        registerDetailGrid()
        gridInstantiated = true;
    }
    var gridData = {
        identifier: "id",
        label: "ID",
        items: posts.list
    }
    itemFileWriteStore = new dojo.data.ItemFileWriteStore({
        data: gridData
    });
    widget.setStore(itemFileWriteStore);
    paginationFtn(posts.start, posts.list.length, posts.total)
    parent.location.hash = "searchKey" + "=" + dojo.byId('keyword').value
}
function paginationFtn(start, max, total) {
    TOTAL = total
    dojo.byId('paginationDiv').style.display = 'block'
    //    dojo.byId('prevPagination').style.display = 'none'
    //    dojo.byId('nextPagination').disabled = 'false'
    dojo.byId('startPagination').innerHTML=start + 1
    dojo.byId('maxPagination').innerHTML=max + start
    dojo.byId('totalPagination').innerHTML=total
//    if ( max < total ) dojo.byId('nextPagination').style.display = 'block'
//    if ( start > 0 ) dojo.byId('prevPagination').disabled = 'disabled'
}
function searchPrev() {
    if ( START > 0 ) {
        START = START > SIZE ? START - SIZE : 0
        MAX = SIZE //MAX - SIZE > 0 ? MAX - SIZE : SIZE
        search()
    }
}
function searchNext() {
    if ( START + SIZE < TOTAL ) {
        START = START + SIZE
        MAX = SIZE //MAX + SIZE < TOTAL ? MAX + SIZE : TOTAL
        search()
    }
}
function clear() {
    var post = {
        id:null,
        postedBy:null,
        emailTransient:null,
        phone:null,
        addressLine:null,
        city:null,
        zipcode:null,
        rent:null,
        rentalType:'All',
        beds:'Studio+',
        area:null,
        comment:null,
        youtubeLink:null
    };
    dwr.util.setValues(post);
}
function post() {
    var post = {
        id:null,
        postedBy:null,
        emailTransient:null,
        phone:null,
        addressLine:null,
        city:null,
        zipcode:null,
        sex:'NA',
        rent:null,
        currency:'USD',
        rentalType:null,
        beds:null,
        area:null,
        comment:null,
        youtubeLink:null
    };
    dwr.util.getValues(post);
    //post.rent = dijit.byId('rent').value;
    var obj1 = dijit.byId("rentalType").attr('item')
    post.rentalType = obj1 != null ? obj1.value : 0
    var obj2 = dijit.byId("beds").attr('item')
    post.beds = obj2 != null ? obj2.value : 0
    dwr.util.useLoadingMessage('saving ...');
    if ( mode == 'update') {
        mode = '';
        RoomateAjaxService(post, email_1, reply)
    } else {
        RoomateAjaxService.addPost(post, reply);
    }
}
function reply (data) {
    if ( data == 1 ) {
        dijit.byId('post').hide();
        dijit.byId('postSuccessMsgDiv').show();
    //clear();
    }
    else if ( data == -1) {
        alert ('Error, please try again')
    }
}
function nextPost() {
    displayDetailGrid(current_row + 1)
}
function prevPost() {
    displayDetailGrid(current_row - 1)
}
function sendMessage() {
    var msg = dijit.byId("reply").value
    if ( msg == null || msg.length < 1 ) return;
    var postId = post_id[0]
    dwr.util.useLoadingMessage('Sending your message');
    RoomateAjaxService.sendMessage(postId, msg, function(data) {
        if ( data == -1 ) {
            alert ( 'Error sending message, please try again!')
        } else if ( data == 1 )
            dijit.byId("reply").setAttribute('value', '')
    })
}
function reportAbuse() {
    if ( !dijit.byId('abuseType1').checked && !dijit.byId('abuseType2').checked) {
        alert('Please select one')
        return;
    }
    var reportType = '';
    if ( dijit.byId('abuseType1').checked ) {
        reportType += dijit.byId('abuseType1').value
        dijit.byId('abuseType1').checked = false
    }
    if (dijit.byId('abuseType2').checked) {
        reportType += dijit.byId('abuseType2').value
        dijit.byId('abuseType2').checked = false
    }
    dijit.byId('reportAbuseDiv').hide()
    RoomateAjaxService.reportAbuse(post_id[0], reportType, function(data) {        
        })
}
function rentalTypeChange() {
//    var x = dijit.byId("rentalType").value
//    dojo.byId('areaTr').style.display='none'
//    dojo.byId('bedsTr').style.display='block'
//    if ( x == 'Commercial' ) {
//        dojo.byId('areaTr').style.display='block'
//        dojo.byId('bedsTr').style.display='none'
//    }
}
//function searchAdvShow() {
//    dijit.byId("advancedSearchDiv").show()
//    parent.location.hash = "advancedSearch"
//}
function searchAdvFtn() {
    var cityAdv_ = dijit.byId('cityAdv').attr('value')
    var zipcodeAdv_ = dijit.byId('zipcodeAdv').attr('value')
    //var radiusAdv_ = dijit.byId('radiusAdv').attr('item').value
    var rentAdv_ = dijit.byId('rentAdv').attr('item')
    var rentalTypeAdv_ = dijit.byId('rentalTypeAdv').attr('item')
    
    
    dijit.byId("advancedSearchDiv").hide()
    var ql = "";
    // create native Lucene query
    if ( cityAdv_.length > 0 )
        ql = "city:" + cityAdv_;
    else if ( zipcodeAdv_.length >= 5)
        ql = "zipcode:" + zipcodeAdv_;

    if ( rentAdv_ != null && rentAdv_.value > 0 )
        ql += " AND rentCategory:" + rentAdv_.value;

    if (rentalTypeAdv_ != null && rentalTypeAdv_.value.length > 0)
        ql += " AND rentalType:"  + rentalTypeAdv_.value;
    dojo.byId('keyword').value = ql
    START = 0
    search()

//RoomateAjaxService.advancedSearch(dijit.byId("cityAdv").value,dijit.byId("zipcodeAdv").value,dijit.byId("radiusAdv").value,dijit.byId("rentAdv").value,dijit.byId("rentalTypeAdv").value, 0, MAX, displayResults);
}
function submitContactUs() {
    RoomateAjaxService.contactUs( dijit.byId("nameContausUs").value,dijit.byId("emailContactUs").value,dijit.byId("typeContactUs").value,dijit.byId("commentContactUs").value, function(reply) {
        if ( reply == 1 ) {
            dijit.byId('contactUsDiv').hide()
            alert ("Thanks for contacting us!")
        }
        else alert("Error, Please try later")
    })
}