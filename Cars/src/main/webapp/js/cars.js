var priceLimit = 0;
var mileage = 0;
var start = 0;
var max = 20;
jQuery(document).ready(function() {
    AjaxCarService.search(priceLimit, mileage, start, max, displayCars);
});

var displayCars = function(cars) {
    var html = "";

    for ( var i=0; i < cars.list.length; i++) {
        html +=
        "<div id='car-link'>\n\
                        <a href='JavaScript:void(0)' class='car-record car'"+cars.list[i].id +">\n\
                            <table width='600px'>" + cars.list[i].year +
        " " + cars.list[i].make + " " + cars.list[i].model + " " + cars.list[i].mileage +
        " $" + cars.list[i].askingPrice + " \n\
                            </table>\n\
                        </a> \n\
                    </div>" +
          " <div><table width='100%'>\n\
                \n\
                <tr><td width='5%'></td><td width='80%'>"
                    + cars.list[i].comments +
                "</td>\n\
\n\
\n\<td width='15%' align='center'>\n\
                    <img src='' >\n\
                </td></tr></table></div>      "
        
        +"<br/>"
        +"<!--a href='http://localhost:8080/web/cars/library?p_p_id=31&p_p_lifecycle=0&p_p_state=pop_up&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_31_struts_action=%2Fimage_gallery%2Fview_slide_show&_31_folderId=" + cars.photosFolderId + ">Images</a -->"
        + "<!-- a href='#'> Video <a-->\n\
        <br/><br/>"

    ;
    }
    jQuery("#cars").html(html);
    
}