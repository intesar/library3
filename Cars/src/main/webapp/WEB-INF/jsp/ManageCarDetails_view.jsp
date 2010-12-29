<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link href="/Cars/css/facebox.css" media="screen" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div>
            <h3 style="font-family: verdana;"> Car Stock Number </h3> &nbsp; <input type="text" value="" id="car-id" /> &nbsp;
            <input type="button" id="car-search" value="Edit" /><br/><br/> &nbsp;
            <input type="button" id="new-car" value="Add A New Car" />
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
                                <td>Stock ID  # </td>
                                <td><input type="text" width="180" class="id" /></td>
                            </tr>
                            <tr>
                                <td>Active</td>
                                <td><input type="text" width="180" make="active" /></td>
                            </tr>
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
                                        <option value="Beige">Beige</option>
                                        <option value="Black">Black</option>
                                        <option value="Blue">Blue</option>
                                        <option value="Brown">Brown</option>
                                        <option value="Burgundy">Burgundy</option>
                                        <option value="Champagne">Champagne</option>
                                        <option value="Charcoal">Charcoal</option>
                                        <option value="Cream">Cream</option>
                                        <option value="Gold">Gold</option>
                                        <option value="Gray">Gray</option>
                                        <option value="Green">Green</option>
                                        <option value="Maroon">Maroon</option>
                                        <option value="OffWhite">Off White</option>
                                        <option value="Orange">Orange</option>
                                        <option value="Pewter">Pewter</option>
                                        <option value="PearlWhite">Pearl White</option>
                                        <option value="Purple">Purple</option>
                                        <option value="Red">Red</option>
                                        <option value="Silver">Silver</option>
                                        <option value="Tan">Tan</option>
                                        <option value="Teal">Teal</option>
                                        <option value="Titanium">Titanium</option>
                                        <option value="Turquoise">Turquoise</option>
                                        <option value="White">White</option>
                                        <option value="Yellow">Yellow</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Interior Color</td>
                                <td>
                                    <select class="interiorColor" >
                                        <option value="">Select</option>
                                        <option value="Beige">Beige</option>
                                        <option value="Black">Black</option>
                                        <option value="Blue">Blue</option>
                                        <option value="Brown">Brown</option>
                                        <option value="Burgundy">Burgundy</option>
                                        <option value="Champagne">Champagne</option>
                                        <option value="Charcoal">Charcoal</option>
                                        <option value="Cream">Cream</option>
                                        <option value="Gold">Gold</option>
                                        <option value="Gray">Gray</option>
                                        <option value="Green">Green</option>
                                        <option value="Maroon">Maroon</option>
                                        <option value="OffWhite">Off White</option>
                                        <option value="Orange">Orange</option>
                                        <option value="Pewter">Pewter</option>
                                        <option value="PearlWhite">Pearl White</option>
                                        <option value="Purple">Purple</option>
                                        <option value="Red">Red</option>
                                        <option value="Silver">Silver</option>
                                        <option value="Tan">Tan</option>
                                        <option value="Teal">Teal</option>
                                        <option value="Titanium">Titanium</option>
                                        <option value="Turquoise">Turquoise</option>
                                        <option value="White">White</option>
                                        <option value="Yellow">Yellow</option>
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
                            <tr>
                                <td>Photos</td>
                                <td><a href="" target="_blank" class="photosUrl"> Click here </a></td>
                            </tr>
                            <tr>
                                <td>Video</td>
                                <td><input size="15" class="video"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <br/><br/>
            <table style="font-family:arial;font-size:12px">

                <tr>
                    <td><table>
                            <tr>
                                <td><h4>Vehicle Features</h4></td>
                            </tr>
                        </table>
                    </td></tr>
            </table>
            <table style="font-family:arial;font-size:12px;width: 100%">
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
            <br/><br/>
            <table>
                <tr>
                    <td>
                        Comments <input size="100"  class="comments"/>
                    </td>
                </tr>                
            </table>
            <br/>
            <table>
                <tr>
                    <td><input type="button" id="car-cancel" value="Cancel" /></td>
                    <td><input type="button" id="car-save" value="Save" /></td>
                    <td><input type="button" id="car-deactivate" value="Deactivate" /></td>
                </tr>
            </table>
        </div>
    </body>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" ></script>
    <script type='text/javascript' src='/Cars/dwr/interface/AjaxCarService.js'></script>
    <script type='text/javascript' src='/Cars/dwr/engine.js'></script>
    <script type="text/javascript" src="/Cars/js/facebox.js" ></script>
    <script type="text/javascript" src="/Cars/js/car_details.js"></script>
</html>
