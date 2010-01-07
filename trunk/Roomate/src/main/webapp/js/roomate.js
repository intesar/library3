/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

dojo.require("dojox.grid.DataGrid");
dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dijit.Tooltip");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.CurrencyTextBox");
dojo.require("dijit.form.Textarea");
dojo.require("dijit.form.ComboBox");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.layout.BorderContainer");
    
dojo.addOnLoad(
    function(){
        var posts = [];
        var gridData = {
            identifier: "id",
            label: "ID",
            items: posts
        };

        var fileWriteStore = new dojo.data.ItemFileWriteStore({
            data: gridData
        }); //^^
        var layout =  [{
            cells:[[

            {
                field: "addressLine",
                name: "Address",
                width: "300px"
            },

            {
                field: "city",
                name: "City",
                width: "150px"
            },
            {
                field: "rent",
                name: "Appx Rent",
                width: "100px"
            },
            {
                field: "posterContact",
                name: "Contact Info",
                width: "250px"
            },
            {
                field: "date",
                name: "Date",
                width: "auto"
            }
            
            ]]
        }];
        var cfg = {
            id: "mytable",
            jsId: "mytable",
            widgetId: "mytable",
            store: fileWriteStore,
            clientSort: true,
            rowsPerPage: 10,
            rowSelector: '40px',
            structure: layout,
            style: "width: 80%; height: 70%; font-size: 14px;"
        };
        widget = new dojox.grid.DataGrid(cfg, dojo.byId("container"));
        widget.startup();

        geoip_country_code = geoip_country_code();
        geoip_country_name = geoip_country_name();
        geoip_city = geoip_city();
        geoip_region = geoip_region();
        geoip_region_name = geoip_region_name();
        geoip_postal_code = geoip_postal_code();
        // setting default city and zipcode
        if (geoip_postal_code != null && geoip_postal_code.length > 0 ) {
            document.getElementById('keyword').value = geoip_postal_code.substring(0,3)+"**";
            search();
        }
        // event binding
        var keywordWidget = dijit.byId("keyword");
        dojo.connect(keywordWidget, 'onKeyUp', function(evt) {
            if ( evt.keyCode == dojo.keys.ENTER)  {
                search();
            }
        });
        var searchButtonWidget = dijit.byId("searchButton");
        dojo.connect(searchButtonWidget, 'onClick', search);
        var newPostLinkWidget = dojo.byId("newPostLink");
        dojo.connect(newPostLinkWidget, 'onclick', function() {
            dijit.byId('post').show();
        });
        var newPostClearButtonWidget = dijit.byId("newPostClearButton");
        dojo.connect(newPostClearButtonWidget, 'onClick', clear);
        var newPostSaveButtonWidget = dijit.byId("newPostSaveButton");
        dojo.connect(newPostSaveButtonWidget, 'onClick', post);
        var searchAgainButtonWidget = dijit.byId("searchAgainButton");
        dojo.connect(searchAgainButtonWidget, 'onClick', function() {
            dijit.byId('noResultDiv').hide();
        });
        // dwr message
        dwr.util.useLoadingMessage();

        // tooltip experiment
        dojo.connect(widget, "onCellMouseOver", function(e) {
            var row = e.rowIndex
            var post = widget.store._arrayOfAllItems[row];
            var msg = post.comment;
            dijit.showTooltip(msg, e.cellNode);
        });
    }
    );
var widget;
var geoip_country_code;
var geoip_country_name;
var geoip_city;
var geoip_region;
var geoip_region_name;
var geoip_postal_code;
function search() {
    var keyword = document.getElementById('keyword').value;
    //document.getElementById('keyword').value='';
    RoomateAjaxService.search(keyword, displayResults);
}
function displayResults(posts) {
    if ( posts.length < 1 ) {
    //dijit.byId('noResultDiv').show();
    }
    else {
        var gridData = {
            identifier: "id",
            label: "ID",
            items: posts
        };
        var fileWriteStore = new dojo.data.ItemFileWriteStore({
            data: gridData
        });
        widget.setStore(fileWriteStore);
    }
}

function clear() {
    var post = {
        id:null,
        postedBy:null,
        posterContact:null,
        addressLine:null,
        city:null,
        zipcode:null,
        rent:null,
        comment:null
    };
    dwr.util.setValues(post);
}
function post() {
    var post = {
        id:null,
        postedBy:null,
        posterContact:null,
        addressLine:null,
        city:null,
        zipcode:null,
        sex:null,
        rent:null,
        comment:null
    };
    dwr.util.getValues(post);
    post.rent = post.rent.substring(1);
    RoomateAjaxService.post(post, reply);
}
function reply (data) {
    $.jGrowl('', {
        header: data,
        life : 10000,
        sticky : false
    });
    dijit.byId('post').hide();
    clear();
}

