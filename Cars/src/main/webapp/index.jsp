<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>



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
            <h3 style="font-family: verdana;"> Car Stock Number </h3> &nbsp; <input type="text" value="2385" id="car-id" /> &nbsp;
            <input type="button" id="car-search" value="Search" /> &nbsp;
            <input type="button" id="new-car" value="Add Car" />
        </div>
        <div id="car-newCar" title="Car Information" style="display:none">

            <table width="800px" border="0" cellspacing="1" cellpadding="0" class="description" style="font-family:arial;font-size:12px">
                <tr>
                    <td><h4>Vehicle Description</h4></td>
                    <td><h4>Vehicle Details</h4></td>
                </tr>
            </table>
            <table width="800px" border="0" cellspacing="1" cellpadding="0" class="description" style="font-family:arial;font-size:12px">
                <tr>
                    <td>

                        <table>
                            <tr>
                                <td width="150"><label >Make</label></td>
                                <td><input type="text" width="180" class="make" /><br/></td>
                            </tr>
                            <tr>
                                <td><label>Model</label><br/></td>
                                <td><input type="text" class="model" /><br/></td>
                            </tr>
                            <tr>
                                <td><label>Style</label></td>
                                <td><input type="text" class="style" /><br/></td>
                            </tr>
                            <tr>
                                <td><label>Body Style</label></td>
                                <td><input type="text" class="bodyStyle" /><br/></td>
                            </tr>
                            <tr>
                                <td><label>Year</label></td>
                                <td><input type="text" class="year" /><br/></td>
                            </tr>
                            <tr>
                                <td><label>Price</label></td>
                                <td><input type="text" class="askingPrice" /><br/></td>
                            </tr>
                            <tr>
                                <td><label>Mileage</label></td>
                                <td><input type="text" class="mileage" /><br/></td>
                            </tr>
                            <tr>
                                <td><label>VIN</label><br/></td>
                                <td><input type="text" class="vin" /><br/></td>
                            </tr>

                        </table>
                    </td>
                    <td><table><tr> <td></td> </tr> </table></td>
                    <td>

                        <table>
                            <tr>
                                <td width="150">Exterior Color</td>
                                <td>
                                    <select class="exteriorColor" >
                                        <option value="">Select</option>
                                        <option value="black">Black</option>
                                        <option value="gray">Gray</option>
                                        <option value="bue">Blue</option>
                                        <option value="beige">Beige</option>
                                        <option value="offwhite">Pearl White</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Interior Color</td>
                                <td>
                                    <select class="interiorColor" >
                                        <option value="">Select</option>
                                        <option value="black">Black</option>
                                        <option value="gray">Gray</option>
                                        <option value="bue">Blue</option>
                                        <option value="beige">Beige</option>
                                        <option value="offwhite">Pearl White</option>
                                    </select><br/>
                                </td>
                            </tr>
                            <tr>
                                <td>Fule Type</td>
                                <td>
                                    <select class="fuleType" >
                                        <option>Gasoline</option>
                                        <option>Diesel</option>
                                        <option>Electric</option>
                                        <option>Hybrid</option>
                                    </select><br/>
                                </td>
                            </tr>
                            <tr>
                                <td>Transmission</td>
                                <td>
                                    <select class="transmission" >
                                        <option>Auto</option>
                                        <option>Manual</option>
                                        <option>Auto-manual</option>
                                    </select><br/>
                                </td>
                            </tr>
                            <tr>
                                <td>Engine Cylinder</td>
                                <td>
                                    <select class="engineCylinder" >
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>8</option>
                                        <option>10</option>
                                        <option>12</option>
                                    </select><br/>
                                </td>
                            </tr>
                            <tr>
                                <td>Drivetrain</td>&nbsp;
                                <td>
                                    <select class="driveTrain" >
                                        <option>2WD</option>
                                        <option>4WD</option>
                                        <option>AWD</option>
                                        <option>FWD</option>
                                        <option>RWD</option>
                                    </select><br/>
                                </td>
                            </tr>
                            <tr>
                                <td>Doors</td>&nbsp;
                                <td>
                                    <select class="doors" >
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select><br/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <table style="font-family:arial;font-size:12px">

                <tr>
                    <td><table>
                            <tr>
                                <td><h4>Vehicle Features</h4></td>
                            </tr>
                        </table>
                    </td></tr>
            </table>
            <table style="font-family:arial;font-size:12px">
                <tr><td>

                        <table>
                            <tr>
                                <td align="center"><input type="checkbox"  class="acFront" ></td>
                                <td>AC Front</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="acRear" /></td>
                                <td>AC Rear</td>
                            </tr>
                            <tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="airbagDriver" /></td>
                                <td>Airbag Driver</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="airbagPassenger" /></td>
                                <td>Airbag Passenger</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="airbagSclasse" /></td>
                                <td>Airbag Sclasse</td>
                            </tr>

                        </table>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td align="center"><input type="checkbox"  class="alarm" /></td>
                                <td>Alarm</td>
                            </tr>

                            <tr>
                                <td align="center"><input type="checkbox"  class="alloyWheels" /></td>
                                <td>Alloy Wheels</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="antilockBrakes" /></td>
                                <td>ABS</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="bucketSeats" /></td>
                                <td>Bucket Seats</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="cdChanger" /></td>
                                <td>CD Changer</td>
                            </tr>

                        </table>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td align="center"><input type="checkbox"  class="casset" /></td>
                                <td>Casset</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="cdPlayer" /></td>
                                <td>CD Player</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="cruiseControl" /></td>
                                <td>Cruse Control</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="fogLights" /></td>
                                <td>Fog Lights</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="leatherInterior" /></td>
                                <td>Leather Interior</td>
                            </tr>

                        </table>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td align="center"><input type="checkbox"  class="memorySeats" /></td>
                                <td>Memory Seats</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="navigation" /></td>
                                <td>Navigation</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="powerLocks" /></td>
                                <td>Power Locks</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="powerSeats" /></td>
                                <td>Power Seats</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="powerStearing" /></td>
                                <td>Power Stearing</td>
                            </tr>

                        </table>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td align="center"><input type="checkbox"  class="powerWindows" /></td>
                                <td>Power Windows</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="premiumSound" /></td>
                                <td>Premium Sound</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="readWindowDefronster" /></td>
                                <td>Rear Window Defroster</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="rearWindowWiper" /></td>
                                <td>Rear Window Wiper</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="remoteKeyless" /></td>
                                <td>Remote Keyless</td>
                            </tr>



                        </table>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td align="center"><input type="checkbox"  class="soundSystem" /></td>
                                <td>Sound System</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="sunroof" /></td>
                                <td>Sunroof</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="thirdRowSeats" /></td>
                                <td>3rd Row Seats</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="tintedGlass" /></td>
                                <td>Tinted Glass</td>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"  class="towPackage" /></td>
                                <td>Tow Package</td>
                            </tr>
                        </table>
                    </td>
            </table>
            <table>

                <tr>
                    <td>
                        Comments <input size="100"  class="comments"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Video <input size="50" class="video"/>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><input type="button" id="car-cancel" value="Cancel" /></td>
                    <td><input type="button" id="car-save" value="Save" /></td>
                </tr>
            </table>
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
            var car;
            if ( car_ != null ) {
                car = car_;
            } else {
                var car = getEmptyCar();
            }
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
                video:null,
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
