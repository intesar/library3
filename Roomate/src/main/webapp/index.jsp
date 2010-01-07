<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Roomates</title>
        <link rel="stylesheet" href="css/jquery.jgrowl.css">
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dijit/themes/tundra/tundra.css" />
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dijit/themes/soria/soria.css" />
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dojo/resources/dojo.css"/>
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dojox/grid/resources/Grid.css"/>
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dojox/grid/resources/tundraGrid.css"/>
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dojox/grid/resources/soriaGrid.css"/>
        <style  media="screen" type="text/css">

            .dojoxGrid table { margin: 0; text-align:center }
            html, body { width: 100%; height: 100%; margin: 0; }
            .borderContainerTwo { width: 100%; height: 100%; }
            .centerdiv { text-align:center }
        </style>
        <script type='text/javascript' src="js/dojo-release-1.3.2/dojo/dojo.js" djConfig="parseOnLoad:true"></script>
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
        <script type='text/javascript' src='dwr/interface/RoomateAjaxService.js'></script>
        <script type="text/javascript" src='js/jquery.js' ></script>
        <script type="text/javascript" src='js/jgrowl_compressed.js' ></script>
        <script type="text/javascript" language="JavaScript" src="http://j.maxmind.com/app/geoip.js"></script>
        <script type="text/javascript">
            var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
            document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
        </script>
        <script type="text/javascript">
            try {
                var pageTracker = _gat._getTracker("UA-6815540-4");
                pageTracker._trackPageview();
            } catch(err) {}
        </script>
    </head>
    <body class="tundra">
        <br/><br/>
        <div align="center">
            <input type="text" id="keyword" class="defaultText" title="type any part of Address and press enter" dojoType="dijit.form.TextBox" style="width: 25em; height:2em" />
            <button id="searchButton" dojoType="dijit.form.Button" class="soria">Search</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="JavaScript:void(0)" id="newPostLink" > Post Your Ad for Free</a>
        </div>
        <div dojoType="dijit.Dialog" id="post" title="New Post">
            <table>
                <tr><td>Email / Phone &nbsp;<span style="color:red">*</span></td><td><input type="text" id="posterContact" dojoType="dijit.form.ValidationTextBox" trim="true" required="true"  invalidMessage="Invalid text." style="width: 15em; height:1.7em"/></td></tr>
                <tr><td>&nbsp;</td><td></td></tr>
                <tr><td>Address</td><td><input type="text" id="addressLine" dojoType="dijit.form.TextBox" trim="true" style="width: 15em; height:1.7em"/></td></tr>
                <tr><td>&nbsp;</td><td></td></tr>
                <tr><td>City / Zip &nbsp;<span style="color:red">*</span></td><td><input type="text" id="city" dojoType="dijit.form.ValidationTextBox" trim="true" required="true"  invalidMessage="Invalid text." style="width: 15em; height:1.7em"/></td></tr>
                <tr><td>&nbsp;</td><td></td></tr>
                <tr><td>Rent</td><td><input type="text" id="rent" dojoType="dijit.form.CurrencyTextBox"
                                            required="true" constraints="{fractional:true}" currency="USD" invalidMessage="Invalid amount." style="width: 7em; height:1.7em"/>&nbsp;&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td></td></tr>
                <tr><td>Looking for</td><td><select id="sex" dojoType="dijit.form.ComboBox"><option selected>Brother</option><option>Sister</option><option>Any</option></select></td></tr>
                <tr><td>&nbsp;</td><td></td></tr>
                <tr><td colspan="2"> <textarea id="comment" style="width:280px; height:50px">comment ...</textarea></td></tr>
                <tr><td>&nbsp;</td><td></td></tr>
                <tr><td></td><td><button dojoType="dijit.form.Button" id="newPostClearButton">Clear</button>&nbsp;&nbsp;&nbsp;&nbsp;<button dojoType="dijit.form.Button" class="soria" id="newPostSaveButton">Post Now</button></td></tr>

            </table>
        </div>
        <br/><br/>
        <div id="container" align="center"></div>
        <div  dojoType="dijit.Dialog" title="No Results" id="noResultDiv">
            <h2>Your query didn't returned any results,</h2>
            <p>Add more keywords (e.g san jose 950** cupertino) and try again!</p>
            <button id="searchAgainButton" dojoType="dijit.form.Button" class="soria">Search Again!</button>
        </div>

        <script type="text/javascript" src='js/roomate.js' ></script>
    </body>
</html>
