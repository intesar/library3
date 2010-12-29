var link = '/library?p_p_id=31&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_31_struts_action=/image_gallery/view&_31_folderId=';
var car_ = null;
jQuery(document).ready(function() {
    jQuery("#car-save").live("click",car_save);
    jQuery("#car-deactivate").live("click", car_deactivate);
    jQuery("#car-cancel").live("click",car_cancel);
    jQuery("#car-search").click(car_search);
    jQuery("#new-car").click(show_newCarDiv);
});
var car_cancel = function() {
    car_ = null;
    // find all input and clear them
    jQuery(':input').not(':button, :submit, :reset').each(function() {
        switch(this.type) {
            case 'password':
            case 'select-multiple':
            case 'select-one':
                this.selected = '';
            case 'text':
                this.value = '';
            case 'textarea':
                this.value = '';
            case 'checkbox':
                this.checked = false;
            case 'radio':
                this.checked = false;
        }
    }
    );
}
var show_newCarDiv = function() {
    car_ = null;
    car_showAddCar();
}
var car_showAddCar = function() {
    jQuery.facebox({
        div: '#car-newCar'
    },'my-groovy-style')
}
var car_save = function() {
    if ( car_ == null ) {
        car_ = getEmptyCar();
    }
    car_ = collectData(car_);
    AjaxCarService.saveCar(car_, function(car) {
        display_car(car);
    });
}
var car_deactivate = function() {
    if ( confirm("Are you sure your want to De Activate this car ? ")) {
        car_.active = false;
        AjaxCarService.saveCar(car_, function(car) {
            display_car(car);
        });
    }
}
function collectData(car) {
    for(var key in car){
        //var attrValue = car[key];
        var x = jQuery("." + key );
        if ( x.length > 0 ) {
            if ( x[1].type == "text") {
                car[key] =  x[1].value;
            } else if ( x[1].type == "checkbox") {
                car[key] =  x[1].checked;
            } else if ( x[1].type == "select-one") {
                car[key] =  x[1].value;
            } else if ( x[1].type == "radio") {
                car[key] =  x[1].value;
            }
        }
    }
    return car;
}
function display_car(car) {
    car_ = car;
    car_showAddCar();
    renderData(car);
    jQuery(jQuery('.photosUrl')[1]).attr('href', link+car_.photosFolderId);
}
function renderData(car) {
    for(var key in car){
        //var attrValue = car[key];
        var x = jQuery("." + key );
        if ( x.length > 0 ) {
            if ( x[1].type == "text") {
                x[1].value = car[key];
            } else if ( x[1].type == "checkbox") {
                x[1].checked = car[key];
            } else if ( x[1].type == "select-one") {
                x[1].value = car[key];
            } else if ( x[1].type == "radio") {
                x[1].value = car[key];
            }
        }
    }
    return car;
}
var car_search = function () {
    AjaxCarService.getCar(jQuery("#car-id").val(), function(car) {
        display_car(car);
    })
}
function getEmptyCar() {
    var car = {
        id:null,
        active:true,
        make:null,
        model:null,
        style:null,
        bodyStyle:null,
        year:null,
        askingPrice:null,
        mileage:null,
        exteriorColor:null,
        interiorColor:null,
        vin:null,
        fuleType:null,
        transmission:null,
        engineCylinder:null,
        driveTrain:null,
        doors:null,
        acFront:null,
        acRear:null,
        airbagDriver:null,
        airbagPassenger:null,
        airbagSide:null,
        alarm:null,
        alloyWheels:null,
        antilockBrakes:null,
        bucketSeats:null,
        cdChanger:null,
        casset:null,
        cdPlayer:null,
        cruiseControl:null,
        fogLights:null,
        leatherInterior:null,
        memorySeats:null,
        navigation:null,
        powerLocks:null,
        powerSeats:null,
        powerStearing:null,
        powerWindows:null,
        premiumSound:null,
        readWindowDefronster:null,
        rearWindowWiper:null,
        remoteKeyless:null,
        soundSystem:null,
        sunroof:null,
        thirdRowSeats:null,
        tintedGlass:null,
        towPackage:null,
        dvd:null,
        buyDate:null,
        ownerName:null,
        createDate:null,
        createdBy:null,
        comments:null,
        video:null,
        sold:null,
        soldDate:null,
        sellingPrice:null,
        sellerComments:null,
        active:null,
        photosFolderId:null
    };
    return car;
}