var priceLimit = 0;
var mileage = 0;
var start = 0;
var max = 20;
var cars_ = new Array();
jQuery(document).ready(function() {
    AjaxCarService.search(priceLimit, mileage, start, max, displayCars);
    jQuery(".car-link").live("click", view_details);
});

var displayCars = function(cars) {
    var html = "";
    for ( var i=0; i < cars.list.length; i++) {
        var car = cars.list[i];
        cars_[car.id] = car; //cars_[i]=car;

        html +=
        "<div class='car-link' id='car-"+car.id +"'>"
                        +"<a href='JavaScript:void(0)' class='car-record' >"
                            +"<table width='600px'>" + car.year +
        " " + car.make + " " + car.model + " " + car.mileage +
        "mi $" + car.askingPrice  
                           +" </table>"
                        +"</a>"
                    + "</div>" +
          " <div><table width='100%'> "
                
                +"<tr><td id='comments' width='5%'></td><td width='80%'>"
                    + car.comments +
                "</td>"
                +"<td width='15%' align='center'>"
                    +"<img src="+car.photosFolderId+" >"
                +"</td></tr></table></div> "

                 
        
        +"<br/>"
        +"<!--a href='http://localhost:8080/web/cars/library?p_p_id=31&p_p_lifecycle=0&p_p_state=pop_up&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_31_struts_action=%2Fimage_gallery%2Fview_slide_show&_31_folderId=" + cars.photosFolderId + ">Images</a -->"
        + "<!-- a href='#'> Video <a-->\n\
        <br/><br/>"

    ;
    }
    jQuery("#cars").html(html);
    
}

var view_details = function() {
    var id = jQuery(this).attr("id").substr(4); // car-10 car-33
    var car = cars_[id];
    display_car(car);
    jQuery.facebox({
        div: '#view-details'
    });
}

function display_car(car) {
    jQuery(".make").text(car.make);
    jQuery(".model").text(car.model);
    jQuery(".style").text(car.style);
    jQuery(".year").text(car.year);
    jQuery(".bodyStyle").text(car.bodyStyle);
    jQuery(".exteriorColor").text(car.exteriorColor);
    jQuery(".interiorColor").text(car.interiorColor);
    jQuery(".transmission").text(car.transmission);
    jQuery(".vin").text(car.vin);
    jQuery(".engine").text(car.engineCylinder);
    jQuery(".driveTrain").text(car.driveTrain);
    jQuery(".doors").text(car.doors);
    jQuery(".fuleType").text(car.doors);
    jQuery(".engineCylinder").text(car.engineCylinder);

    // checkboxes
    if(car.acFront == true)
        jQuery(".acFront").html("AC Front");
    if(car.acRear == true)
        jQuery(".acRear").html("AC Rear");
    if(car.airbagDriver == true)
        jQuery(".airbagDriver").html("Airbag Driver");
    if(car.airbagPassenger == true)
        jQuery(".airbagPassenger").html("Airbag Passenger");
    if(car.airbagSide == true)
        jQuery(".airbagSide").html("Airbag Side");
    if(car.alarm == true)
        jQuery(".alarm").html("Alarm");
    if(car.alloyWheels == true)
        jQuery(".alloyWheels").html("Alloy Wheels");
    if(car.antilockBrakes == true)
        jQuery(".antilockBrakes").html("ABS System");
    if(car.bucketSeats == true)
        jQuery(".bucketSeats").html("Bucket Seats");
    if(car.cdChanger == true)
        jQuery(".cdChanger").html("CD Changer");
    if(car.cdPlayer == true)
        jQuery(".cdPlayer").html("CD Player");
    if(car.cruiseControl == true)
        jQuery(".cruiseControl").html("Cruise Control");
    if(car.leatherInterior == true)
        jQuery(".leatherInterior").html("Leather Interior");
    if(car.memorySeats == true)
        jQuery(".memorySeats").html("Memory Seats");
    if(car.navigation == true)
        jQuery(".navigation").html("Navigation");
    if(car.powerLocks == true)
        jQuery(".powerLocks").html("Power Locks");
    if(car.powerSeats == true)
        jQuery(".powerSeats").html("Power Seats");
    if(car.powerStearing == true)
        jQuery(".powerStearing").html("Power Stearing");
    if(car.powerWindows == true)
        jQuery(".powerWindows").html("Power Windows");
    if(car.premiumSound == true)
        jQuery(".premiumSound").html("Premium Sound");
    if(car.readWindowDefronster == true)
        jQuery(".readWindowDefronster").html("Rear Defronster");
    if(car.rearWindowWiper == true)
        jQuery(".rearWindowWiper").html("Rear Wiper");
    if(car.remoteKeyless == true)
        jQuery(".remoteKeyless").html("Remote Keyless");
    if(car.sunroof == true)
        jQuery(".sunroof").html("Sunroof");
    if(car.tintedGlass == true)
        jQuery(".tintedGlass").html("Tinted Glass");
    if(car.thirdRowSeats == true)
        jQuery(".thirdRowSeats").html("Third Row Seats");
    if(car.towPackage == true)
        jQuery(".towPackage").html("Tow Package");

}
