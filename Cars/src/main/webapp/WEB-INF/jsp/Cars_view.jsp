<%--
    Document   : main
    Created on : Nov 24, 2010, 8:30:40 PM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=MacRoman">
        <title>Search Cars</title>
        <link href="/Cars/css/facebox.css" media="screen" rel="stylesheet" type="text/css"/>


    </head>
    <body>

        <div>
            <span> Sort By </span>
            <span><a href="#" class="sort-by-price">Price</a></span>
            <span><a href="#" class="sort-by-mileage">Mileage</a></span>
            <span><a href="#" class="sort-by-year">Year</a></span>
        </div>
        <br/>
        <div>
            <table width="300px" border="0">
                <tr valign="top">
                    <td>
                        <div id="cars">


                        </div>
                    </td>
                </tr>
            </table>
        </div>


        <div id="view-details" style="display: none">
            <!-- table class="clicked-details" -->

            <table class="clicked-details" width="800px">
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td id="image-area""><div class="images">Image here</div>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td>
                                    <table><!--div class="car-info"-->
                                        <h4>Car Glimpse</h4>
                                        <tr>
                                            <td id="car-info"><label>Body Style: </label></td>
                                            <td><span  class="bodyStyle"></span></td>
                                        </tr>
                                        <tr>
                                            <td id="car-info"><label>Exterior Color: </label></td>
                                            <td><span  class="exteriorColor"></span></td>
                                        </tr>
                                        <tr>
                                            <td id="car-info"><label>Interior Color: </label></td>
                                            <td><span  class="interiorColor"></span></td>
                                        </tr>
                                        <tr>
                                            <td id="car-info"><label>Doors: </label></td>
                                            <td><span class="doors"></span></td>
                                        </tr>
                                        <tr>
                                            <td id="car-info"><label>Transmission: </label></td>
                                            <td><span class="transmission"></span></td>
                                        </tr>
                                        <tr>
                                            <td id="car-info"><label>Engine: </label></td>
                                            <td><span  class="engine"></span></td>
                                        </tr>
                                        <tr>
                                            <td id="car-info"><label>Drivetrain: </label></td>
                                            <td><span  class="driveTrain"></span></td>
                                        </tr>
                                        <tr>
                                            <td id="car-info"><label>VIN#: </label></td>
                                            <td><span  class="vin"></span></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>
                <table width="800px" border="0" cellspacing="1" cellpadding="0" class="description" style="font-family:arial;font-size:12px">
                <tr><br/><br/>
                    <td width="133"><h5>Comfort</h5></td>
                    <td width="133"><h5>Safety</h5></td>
                    <td width="133"><h5>Seats</h5></td>
                    <td width="133"><h5>Sound System</h5></td>
                    <td width="133"><h5>Windows</h5></td>
                    <td width="133"><h5>Extras</h5></td>
                </tr>
            </table>
            <table width="800px">
                <tr>
                    <td>
                        <table><tr>
                            <td width="133" class="acRear"></td></tr><tr>
                            <td class="acFront"></td></tr><tr>
                            <td class="cruiseControl"></td></tr><tr>
                            <td class="navigation"></td></tr><tr>
                            <td class="powerLocks"></td></tr><tr>
                            <td class="powerStearing"></td></tr><tr>
                            <td class="remoteKeyless"></td></tr><tr>
                        </tr></table>
                    </td>
                    <td><table><tr>
                            <td width="133" class="airbagDriver"></td></tr><tr>
                            <td class="airbagPassenger"></td></tr><tr>
                            <td class="airbagSide"></td></tr><tr>
                            <td class="alarm"></td></tr><tr>
                            <td class="antilockBrakes"></td></tr><tr>
                        </tr></table>
                    </td>
                    <td><table><tr>
                            <td width="133" class="bucketSeats"></td></tr><tr>
                            <td class="leatherInterior"></td></tr><tr>
                            <td class="memorySeats"></td></tr><tr>
                            <td class="powerSeats"></td></tr><tr>
                        </tr></table>
                    </td>
                    <td><table><tr>
                            <td width="133"class="casset"></td></tr><tr>
                            <td class="cdChanger"></td></tr><tr>
                            <td class="cdPlayer"></td></tr><tr>
                            <td class="premiumSound"></td></tr><tr>
                        </tr></table>
                    </td>
                    <td><table><tr>
                        <td width="133"class="powerWindows"></td></tr><tr>
                        <td class="rearWindowWiper"></td></tr><tr>
                        <td class="readWindowDefronster"></td></tr><tr>
                        <td class="tintedGlass"></td></tr><tr>
                        </tr></table>
                    </td>
                    <td><table><tr>
                        <td width="133" class="alloyWheels"></td></tr><tr>
                        <td class="sunroof"></td></tr><tr>
                        <td class="thirdRowSeats"></td></tr><tr>
                        <td class="towPackage"></td></tr><tr>
                        </tr></table>
                    </td>
                </tr>

            </table>




                
                
        </div>


    </body>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" ></script>
    <script type='text/javascript' src='/Cars/dwr/interface/AjaxCarService.js'></script>
    <script type='text/javascript' src='/Cars/dwr/engine.js'></script>
    <script type="text/javascript" src="/Cars/js/facebox.js"></script>
    <script type="text/javascript" src="/Cars/js/cars.js"></script>
</html>

