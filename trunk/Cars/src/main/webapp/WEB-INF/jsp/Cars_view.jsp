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
        <table width="300px" border="0">

            <tr>
                <td>
                    <h2>Our Collection</h2>
                </td>
            </tr>

            <tr valign="top">


                <td style="background-color: white; height: 200px; width: 300px; text-align: top;">
                    <div id="cars">


                    </div>

                </td>
            </tr>
        </table>


    </body>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" ></script>
    <script type='text/javascript' src='/Cars/dwr/interface/AjaxCarService.js'></script>
    <script type='text/javascript' src='/Cars/dwr/engine.js'></script>
    <script type="text/javascript" src="/Cars/js/facebox.js"></script>
    <script type="text/javascript" src="/Cars/js/cars.js"></script>
</html>
