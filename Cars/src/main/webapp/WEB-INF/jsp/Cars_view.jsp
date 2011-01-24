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
        <link href="/Cars/css/cars.css" media="screen" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="main-box">
            <div id="filter-list">
                <select id="sort-range">
                    <option>Mileage Range</option>
                    <option  class="mileageLimit" value="0"> None </option>
                    <option  class="mileageLimit" value="10"> Less than 30K </option>
                    <option  class="mileageLimit" value="20"> 30K to 50K </option>
                    <option  class="mileageLimit" value="30"> 50K or more </option>
                </select>
            </div>
            <div id="filter-list">
                <label>Narrow your search: </label>
                <select id="sort-range">
                    <option>Price Range</option>
                    <option  class="priceLimit" value="0" > None </option>
                    <option  class="priceLimit" value="10" > Less than $5000 </option>
                    <option  class="priceLimit" value="20" > $5000 to $10,000 </option>
                    <option  class="priceLimit" value="30" > $10,000 or more</option>
                </select>
            </div>            
            <div id="sort-list">
                <span> Sort By </span>
                <span><a href="#" id="sort-list" class="sort-by-new">New</a></span>
                <span><a href="#" id="sort-list" class="sort-by-price">Price</a></span>
                <span><a href="#" id="sort-list" class="sort-by-mileage">Mileage</a></span>
                <span><a href="#" id="sort-list" class="sort-by-year">Year</a></span>
            </div>

        </div>
        <br/>
        <div id="cars">

        </div>
        <div id="view-details" class="clicked-details"  style="display: none;">


            <div class="images"></div>

            <div class="car-info">

                <div class="carHeading">
                    <label class="make"></label>
                    <label class="model"></label>
                    <label class="year"></label>
                </div>

                <div id="car-glimpse">
                    <h4 id="cg-head">Car Glimpse</h4>
                    <ul>
                        <label>Body Style: </label>
                        <span  class="bodyStyle"></span><br/>
                        <label>Exterior Color: </label>
                        <span  class="exteriorColor"></span><br/>
                        <label>Interior Color: </label>
                        <span  class="interiorColor"></span><br/>
                        <label>Doors: </label>
                        <span class="doors"></span><br/>
                        <label>Transmission: </label>
                        <span class="transmission"></span><br/>
                        <label>Engine: </label>
                        <span  class="engine"></span><br/>
                        <label>Drivetrain: </label>
                        <span  class="driveTrain"></span><br/>
                        <label>VIN#: </label>
                        <span  class="vin"></span><br/>
                        <label>Stock#: </label>
                        <span class="stock"></span>
                    </ul>
                </div>


                <div id="comfort-list" class="left-padding">
                    <h4>Comfort</h4>
                    <ul>
                        <li><div class="acFront"></div></li>
                        <li><div class="acRear"></div></li>
                        <li><div class="cruiseControl"></div></li>
                        <li><div class="navigation"></div></li>
                        <li><div class="powerLocks"></div></li>
                        <li><div class="powerStearing"></div></li>
                        <li><div class="remoteKeyless"></div></li>
                    </ul>
                </div>

                <div id="safety-list">
                    <h4>Safety</h4>
                    <ul>
                        <li><div class="airbagDriver"></div></li>
                        <li><div class="airbagPassenger"></div></li>
                        <li><div class="airbagSide"></div></li>
                        <li><div class="alarm"></div></li>
                        <li><div class="antilockBrakes"></div></li>
                    </ul>
                </div>

                <div id="seats-list" class="left-padding">
                    <h4>Seats</h4>
                    <ul>
                        <li><div class="bucketSeats"></div></li>
                        <li><div class="leatherInterior"></div></li>
                        <li><div class="memorySeats"></div></li>
                        <li><div class="powerSeats"></div></li>
                    </ul>
                </div>

                <div id="soundSystem-list">
                    <h4>Sound System</h4>
                    <ul>
                        <li><div class="casset"></div></li>
                        <li><div class="cdChanger"></div></li>
                        <li><div class="cdPlayer"></div></li>
                        <li><div class="premiumSound"></div></li>
                    </ul>
                </div>

                <div id="windows-list" class="left-padding">
                    <h4>Windows</h4>
                    <ul>
                        <li><div class="powerWindows"></div></li>
                        <li><div class="rearWindowWiper"></div></li>
                        <li><div class="readWindowDefronster"></div></li>
                        <li><div class="tintedGlass"></div></li>
                    </ul>
                </div>

                <div id="extras-list">
                    <h4>Extras</h4>
                    <ul>
                        <li><div class="alloyWheels"></div></li>
                        <li><div class="sunroof"></div></li>
                        <li><div class="thirdRowSeats"></div></li>
                        <li><div class="towPackage"></div></li>
                    </ul>
                </div>

            </div>



        </div>
        <div style="display:none;" id="email-div">
            <div id="toAdd"><label>To</label><input type="text" class="toAddress" id="to-label1" value=""/> </div>
            <div id="sub"><label>Subject</label><input type="text" class="subjectEmail" id="subject-label" value=""/> </div>
            <div id="com"><label>Comment</label><input type="text" class="commentEmail" id="comment-label" value=""/> </div>
            <div>
                <input type="button" value="Send" class="send-btn"/>
                <input type="button" value="Clear" class="clear-btn"/>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" ></script>
    <script type='text/javascript' src='/Cars/dwr/interface/AjaxCarService.js'></script>
    <script type='text/javascript' src='/Cars/dwr/engine.js'></script>
    <script type="text/javascript" src="/Cars/js/facebox.js"></script>
    <script type="text/javascript" src="/Cars/js/cars.js"></script>
</html>
