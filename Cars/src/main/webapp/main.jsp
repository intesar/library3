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
          <%--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/dark-hive/jquery-ui.css" type="text/css" media="all" /> --%>
        <script type="text/javascript"  src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" ></script>
        <%--<script type="text/javascript"  src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/jquery-ui.min.js" ></script> --%>
        <script type='text/javascript' src='/Cars/dwr/interface/AjaxCarService.js'></script>
        <script type='text/javascript' src='/Cars/dwr/engine.js'></script>
        <link href="/Cars/css/facebox.css" media="screen" rel="stylesheet" type="text/css"/>
        <script src="/Cars/js/facebox.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>Our Collection</h1>
        <div id="cars">
            
        </div>
    </body>
    <script type="text/javascript">
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
                html += "<div><a href='JavaScript:void(0)' class='car-record car'"+cars.list[i].id +">" + cars.list[i].year +
                    " " + cars.list[i].make + " " + cars.list[i].model + " " + cars.list[i].mileage +
                    " " + cars.list[i].askingPrice + " </a> </div>";
            }
            jQuery("#cars").html(html);
        }
    </script>
</html>
