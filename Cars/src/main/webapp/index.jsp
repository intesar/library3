<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>

        <style type="" >
            body { font-size: 62.5%; }
            label, input { display:block; }
            input.text { margin-bottom:12px; width:95%; padding: .4em; }
            fieldset { padding:0; border:0; margin-top:25px; }
            h1 { font-size: 1.2em; margin: .6em 0; }
            div#users-contain { width: 350px; margin: 20px 0; }
            div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
            div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
            .ui-dialog .ui-state-error { padding: .3em; }
            .validateTips { border: 1px solid transparent; padding: 0.3em; }
        </style>
        <%--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/dark-hive/jquery-ui.css" type="text/css" media="all" /> --%>
        <script type="text/javascript"  src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" ></script>
        <%--<script type="text/javascript"  src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/jquery-ui.min.js" ></script> --%>
        <script type='text/javascript' src='/Cars/dwr/interface/AjaxCarService.js'></script>
        <script type='text/javascript' src='/Cars/dwr/engine.js'></script>
        <link href="/Cars/css/facebox.css" media="screen" rel="stylesheet" type="text/css"/>
        <script src="/Cars/js/facebox.js" type="text/javascript"></script>
    </head>
    <body>
        <div>
            <label> Car Stock No # </label> &nbsp; <input type="text" id="car-id" /> &nbsp;
            <input type="button" id="car-search" value="Search" /> &nbsp;
            <input type="button" id="new-car" value="Add Car" />
        </div>
        <div id="car-newCar" title="Car Information" style="display:none">
            <label>Make</label>&nbsp;<input type="text" id="make" class="make"/> <br/>
            <label>Model</label>&nbsp;<input type="text" class="model" /><br/>
            <label>Style</label>&nbsp;<input type="text" class="style" /><br/>
            <label>Body Style</label>&nbsp;<input type="text" class="bodyStyle" /><br/>
            <label>Year</label>&nbsp;<input type="text" class="year" /><br/>
            <label>Price</label>&nbsp;<input type="text" class="askingPrice" /><br/>
            <label>Mileage</label>&nbsp;<input type="text" class="mileage" /><br/>
            <label>Exterior Color</label>&nbsp;<input type="text" class="exteriorColor" /><br/>
            <label>Interior Color</label>&nbsp;<input type="text" class="interiorColor" /><br/>
            <label>VIN</label>&nbsp;<input type="text" class="vin" /><br/>
            <label>Fule Type</label>&nbsp;<select class="fuleType" >
                <option>Gasoline</option>
                <option>Diesel</option>
            </select><br/>
            <label>Transmission</label>&nbsp;<select class="transmission" >
                <option>Auto</option>
                <option>Manual</option>
            </select><br/>
            <label>Engine Cylinder</label>&nbsp;<select class="engineCylinder" >
                <option>4</option>
                <option>6</option>
                <option>8</option>
            </select><br/>
            <label>Drivetrain</label>&nbsp;<select class="driveTrain" >
                <option>Auto</option>
                <option>Manual</option>
            </select><br/>
            <label>Doors</label>&nbsp;<select class="doors" >
                <option>2</option>
                <option>3</option>
                <option selected>4</option>
                <option>5</option>
            </select><br/>
            <label>AC Front</label>&nbsp;<input type="checkbox"  class="acFront" /><br/>
            <label>AC Rear</label>&nbsp;<input type="checkbox"  class="acRear" /><br/>
            <label>Airbag Driver</label>&nbsp;<input type="checkbox"  class="airbagDriver" /><br/>
            <label>Airbag Passenger</label>&nbsp;<input type="checkbox"  class="airbagPassenger" /><br/>
            <label>Airbag Sclasse</label>&nbsp;<input type="checkbox"  class="airbagSclasse" /><br/>
            <label>Alarm</label>&nbsp;<input type="checkbox"  class="alarm" /><br/>
            <label>Alloy Wheels</label>&nbsp;<input type="checkbox"  class="alloyWheels" /><br/>
            <label>ABS</label>&nbsp;<input type="checkbox"  class="antilockBrakes" /><br/>
            <label>Bucket Seats</label>&nbsp;<input type="checkbox"  class="bucketSeats" /><br/>
            <label>CD Changer</label>&nbsp;<input type="checkbox"  class="cdChanger" /><br/>
            <label>Casset</label>&nbsp;<input type="checkbox"  class="casset" /><br/>
            <label>CD Player</label>&nbsp;<input type="checkbox"  class="cdPlayer" /><br/>
            <label>Cruse Control</label>&nbsp;<input type="checkbox"  class="cruiseControl" /><br/>
            <label>Fog Lights</label>&nbsp;<input type="checkbox"  class="fogLights" /><br/>
            <label>Leather Interior</label>&nbsp;<input type="checkbox"  class="leatherInterior" /><br/>
            <label>Memory Seats</label>&nbsp;<input type="checkbox"  class="memorySeats" /><br/>
            <label>Navigation</label>&nbsp;<input type="checkbox"  class="navigation" /><br/>
            <label>Power Locks</label>&nbsp;<input type="checkbox"  class="powerLocks" /><br/>
            <label>Power Seats</label>&nbsp;<input type="checkbox"  class="powerSeats" /><br/>
            <label>Power Stearing</label>&nbsp;<input type="checkbox"  class="powerStearing" /><br/>
            <label>Power Windows</label>&nbsp;<input type="checkbox"  class="powerWindows" /><br/>
            <label>Premium Sound</label>&nbsp;<input type="checkbox"  class="premiumSound" /><br/>
            <label>Rear Window Defroster</label>&nbsp;<input type="checkbox"  class="readWindowDefronster" /><br/>
            <label>Rear Window Wiper</label>&nbsp;<input type="checkbox"  class="rearWindowWiper" /><br/>
            <label>Remote Keyless</label>&nbsp;<input type="checkbox"  class="remoteKeyless" /><br/>
            <label>Sound System</label>&nbsp;<input type="checkbox"  class="soundSystem" /><br/>
            <label>Sunroof</label>&nbsp;<input type="checkbox"  class="sunroof" /><br/>
            <label>3rd Row Seats</label>&nbsp;<input type="checkbox"  class="thirdRowSeats" /><br/>
            <label>Tinted Glass</label>&nbsp;<input type="checkbox"  class="tintedGlass" /><br/>
            <label>Tow Package</label>&nbsp;<input type="checkbox"  class="towPackage" /><br/>
            <input type="button" id="car-cancel" value="Cancel" />
            <input type="button" id="car-save" value="Save" />
        </div>
    </body>
    <script type="text/javascript">
        jQuery(document).ready(function() {
            jQuery("#car-save").live("click",car_save);
            jQuery("#car-cancel").live("click",car_cancel);
            jQuery("#car-search").click(car_search);
            jQuery("#new-car").click(car_showAddCar);
        });
        var car_cancel = function() {
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
        var car_showAddCar = function() {
            jQuery.facebox({ div: '#car-newCar' })
        }
        var car_save = function() {
            var car = getEmptyCar();
            car = collectData(car);
            AjaxCarService.saveCar(car, function() {
                alert("saved!")
            });
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
        var car_ ;
        var car_search = function () {
            AjaxCarService.getCar(jQuery("#car-id").val(), function(car) {
                car_ = car;
                car_showAddCar();
                renderData(car);
            })
        }
        function getEmptyCar() {
            var car = {
                id:null,
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
                sold:null,
                soldDate:null,
                sellingPrice:null,
                sellerComments:null,
                active:null
            };
            return car;
        }
    </script>

</html>
