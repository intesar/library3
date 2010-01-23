<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Roomates</title>

        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dijit/themes/tundra/tundra.css" />
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dijit/themes/soria/soria.css" />
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dojo/resources/dojo.css"/>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dojox/grid/resources/Grid.css"/>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dojox/grid/resources/tundraGrid.css"/>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dojox/grid/resources/soriaGrid.css"/>
        <%--
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dijit/themes/tundra/tundra.css" />
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dijit/themes/soria/soria.css" />
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dojo/resources/dojo.css"/>
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dojox/grid/resources/Grid.css"/>
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dojox/grid/resources/tundraGrid.css"/>
        <link rel="stylesheet" href="js/dojo-release-1.3.2/dojox/grid/resources/soriaGrid.css"/>
        --%>
        <style  media="screen" type="text/css">
            .dojoxGrid table { margin: 0; text-align:center }
            html, body { width: 100%; height: 100%; margin: 0; }
            .borderContainerTwo { width: 100%; height: 100%; }
            .centerdiv { text-align:center }
            label {font-weight:bold}
            #mytable {margin-left: 40px;}
            .dojoxGrid table { margin: 0; margin-left: 0px}
            #loadingDiv {margin-left: 45%; margin-top: 20%}
            .smallLabel {
                color:#666666;
                font-size:100%;
            }
            #searchDiv {
                margin-top: 0px;
            }
            .tableStyle, tr {
                height:30px;
            }
        </style>

    </head>
    <body class="tundra">
        <div id="loadingDiv">
            <p>Loading . . .</p>
        </div>
        <div id="bodyDiv" style="display:none">

            <div align="right" style="margin-top:10px; margin-right:30px">
                <a href="JavaScript:void(0)" id="newPostLink" > Post Your Ad for Free</a>&nbsp;&nbsp;
            </div>

            <br/>

            <div id="searchDiv" align="center">
                <input type="text" id="keyword" class="defaultText" title="type any part of Address and press enter" dojoType="dijit.form.TextBox" style="width: 30em; height:2em" /> &nbsp;
                <button id="searchButton" dojoType="dijit.form.Button" class="">Search</button>&nbsp;&nbsp;
                <a href="javascript:void(0)" id="searchAdvLink">Advanced Search</a>
            </div>

            <div dojoType="dijit.Dialog" id="advancedSearchDiv" title="Advanced Search" class="soria">
                <table class="tableStyle" cellspacing="10px">
                    <tr><td><span class="smallLabel">City</span></td><td><input type="text" id="cityAdv" dojoType="dijit.form.ValidationTextBox" trim="true" required="true"  invalidMessage="Invalid city." style="width: 20em; height:1.7em"/></td></tr>
                    <tr><td><span class="smallLabel">Max Rent</span></td><td><input type="text" id="rentAdv" dojoType="dijit.form.NumberTextBox" required="false" constraints="{min:0,max:200000,places:0, fractional:true}" style="width: 20em; height:1.7em"/></td></tr>
                    <tr><td><span class="smallLabel">Type</span></td><td><select dojoType="dijit.form.ComboBox" id="rentalTypeAdv" style="width: 200px;">
                                <option selected>
                                    All
                                </option>
                                <option>
                                    Shared
                                </option>
                                <option>
                                    Shared - Seperate room
                                </option>
                                <option>
                                    New Rental
                                </option>
                                <option>
                                    Commercial
                                </option>
                            </select></td></tr>
                    <tr><td></td><td><button dojoType="dijit.form.Button" class="soria" id="searchAdvButton" style="width:100px">Search</button></td></tr>
                </table>
            </div>

            <div dojoType="dijit.Dialog" id="post" title="New Post" class="soria">
                <table class="tableStyle" >
                    <tr><td colspan="2" align="center">Contact information</td></tr>
                    <tr><td><span class="smallLabel" >Name</span></td><td><input type="text" id="postedBy" dojoType="dijit.form.TextBox" trim="true" style="width: 20em; height:1.7em"/></td></tr>
                    <tr><td><span class="smallLabel" >Email</span>&nbsp;&nbsp;<span style="color:red">*</span></td><td><input type="text" id="emailTransient" dojoType="dijit.form.ValidationTextBox" trim="true" required="true"  invalidMessage="Invalid Email" style="width: 20em; height:1.7em"/>&nbsp;&nbsp;<a class="smallLabel" id="whyEmail" href="javascript:void(0);">?</a></td></tr>
                    <tr><td><span class="smallLabel" >Phone</span></td><td><input type="text" id="phone" dojoType="dijit.form.TextBox" trim="true" required="true"  style="width: 20em; height:1.7em"/>&nbsp;&nbsp;<a class="smallLabel" id="whyPhone" href="javascript:void(0);">?</a></td></tr>
                    <tr><td colspan="2" align="center">Rental Address</td></tr>
                    <tr><td><span class="smallLabel">Street</span></td><td><input type="text" id="addressLine" dojoType="dijit.form.TextBox" trim="true" style="width: 20em; height:1.7em"/></td></tr>
                    <tr><td><span class="smallLabel" >City</span>&nbsp;<span style="color:red">*</span>&nbsp; / &nbsp; <span class="smallLabel" >Zip</span>&nbsp;<span style="color:red">*</span>&nbsp;</td>
                        <td><input type="text" id="city" dojoType="dijit.form.ValidationTextBox" trim="true" required="true"  invalidMessage="Invalid city." style="width: 10em; height:1.7em"/>
                            &nbsp;&nbsp;<input type="text" id="zipcode" dojoType="dijit.form.ValidationTextBox" trim="true" required="true"  invalidMessage="Invalid zipcode." style="width: 9em; height:1.7em"/></td></tr>
                    <tr><td></td><td></td></tr>
                    <tr><td><span class="smallLabel" >Rent</span>&nbsp;<span style="color:red">*</span>&nbsp;</td>
                        <td><input type="text" id="rent" dojoType="dijit.form.NumberTextBox"
                                   required="true" constraints="{min:0,max:200000,places:0, fractional:true}" invalidMessage="Invalid amount." style="width: 7em; height:1.7em"/>&nbsp;&nbsp;
                            <select dojoType="dijit.form.ComboBox" id="currency" style="width: 80px;display: none">
                                <option selected>
                                    USD
                                </option>
                                <option>
                                    INR
                                </option>
                                <option>
                                    Local
                                </option>
                            </select>
                            &nbsp;<a class="smallLabel" id="whyRent" href="javascript:void(0);">?</a>
                        </td></tr>
                    <tr><td><span class="smallLabel" >Rental Type</span>&nbsp;</td>
                        <td> <select dojoType="dijit.form.ComboBox" id="rentalType" style="width: 200px;">
                                <option selected>
                                    All
                                </option>
                                <option>
                                    Shared
                                </option>
                                <option>
                                    Shared - Seperate room
                                </option>
                                <option>
                                    New Rental
                                </option>
                                <option>
                                    Commercial
                                </option>
                            </select>
                        </td></tr>
                    <tr><td><span class="smallLabel">Beds</span></td><td>
                            <select dojoType="dijit.form.ComboBox" id="beds" style="width: 100px;">
                                <option selected value="0">
                                    Studio+
                                </option>
                                <option value="1">
                                    1+
                                </option>
                                <option value="2">
                                    2+
                                </option>
                                <option value="3">
                                    3+
                                </option>
                            </select></td></tr>
                    <tr><td><span class="smallLabel">Area</span></td><td>
                            <input type="text" id="area" dojoType="dijit.form.ValidationTextBox" trim="true" required="false"  invalidMessage="Invalid" style="width: 9em; height:1.7em"/> &nbsp; <span class="smallLabel"> sq ft</span>
                        </td></tr>
                    <tr><td colspan="2"> <textarea id="comment" dojoType="dijit.form.SimpleTextarea" rows="2" cols="50" style="width:300px;">Comments...</textarea></td></tr>
                    <tr><td></td><td><button dojoType="dijit.form.Button" id="newPostClearButton" class="tundra">Clear</button>&nbsp;&nbsp;&nbsp;&nbsp;<button dojoType="dijit.form.Button" class="soria" id="newPostSaveButton" style="width:100px">Save</button></td></tr>
                </table>
            </div>

            <br/><br/>

            <div id="container" ></div>

            <div  dojoType="dijit.Dialog" title="No matching records found!" id="noResultDiv">
                <input type="text" id="keywordNoResult" class="" title="" dojoType="dijit.form.TextBox" style="width: 25em; height:2em" />
                <button id="searchAgainButton" dojoType="dijit.form.Button" class="soria">Search Again!</button><br/>
                <span class="smallLabel" >(Add more keywords e.g san jose cupertino 9505* 9504*) </span>
                <br/><br/><hr>
                <h3>Setup email alerts</h3> <br/>
                <input type="text" id="subscribeKeyword" class="" title="" dojoType="dijit.form.TextBox" style="width: 25em; height:2em" /><br/>
                <span class="smallLabel" >Search Criteria (Enter all possible keywords)</span><br/><br/>
                <input type="text" id="subscribeEmail" class="" title="" dojoType="dijit.form.TextBox" style="width: 25em; height:2em" /><br/>
                <span class="smallLabel" >Email</span><br/><br/>
                <button id="subscribeButton" dojoType="dijit.form.Button" >Save this settings</button>

            </div>

            <div  dojoType="dijit.Dialog" title="Post successfull" id="postSuccessMsgDiv">
                <h2>Your Ad posted successfully!</h2>
                <div align="center">
                    <button id="postSuccessMsgButton" dojoType="dijit.form.Button" class="soria">Close</button>
                </div>
            </div>

            <div dojoType="dijit.Dialog" id="postDetails" title="Post Details" class="soria">
                <table>
                    <tr><td><span class="smallLabel">Posted By </span></td><td><span id="postedByDetail"></span>&nbsp; &nbsp;(<span id="dateDetail"></span>)</td></tr>
                    <tr><td><span class="smallLabel">Phone </span></td><td><span id="phoneDetail"></span> </td></tr>
                    <tr><td><span class="smallLabel">Type </span></td><td><span id="rentalTypeDetail"></span> &nbsp; &nbsp; <span id="typeDetailMore"></span></td></tr>
                    <tr><td><span class="smallLabel">Rent </span></td><td><span id="rentDetail" ></span>  &nbsp; <span id="currencyDetail"></span> </td></tr>
                    <tr><td><span class="smallLabel">Address </span></td><td><span id="addressLineDetail" ></span> &nbsp; &nbsp; <span id="cityDetail" ></span> &nbsp; &nbsp; <span id="zipcodeDetail" ></span></td></tr>
                    <tr><td colspan="2"><span class="smallLabel">Comments </span></td></tr>
                    <tr><td colspan="2"><span id="commentDetail" ></span></td></tr>
                </table>
                <br/><br/><hr><br/>
                <div id="deleteButtonDiv" style="display:none">
                    <button dojoType="dijit.form.Button" class="soria" id="deleteButton">Please remove this listing!</button>
                </div>
                <div id="commentDiv">
                    <span class="smallLabel">Please include your email or phone, so user can contact you back</span><br/>
                    <textarea id="reply" dojoType="dijit.form.SimpleTextarea" rows="2" cols="50" style="width:400px;"></textarea><br/>
                </div>
                <div align="right" id="SendMessageDiv" >
                    <button dojoType="dijit.form.Button" class="soria" id="postReplyButton">Send Message</button>
                </div>
                <br/>
                <div align="center" id="prevNextDiv">
                    <a href="javascript:void(0);" id="prev">Prev</a> &nbsp; &nbsp;
                    <a href="javascript:void(0);" id="next">Next</a> &nbsp; &nbsp; &nbsp;
                </div>
                <div  align="right" >
                    <span style="font-size: smaller">
                        <a href="javascript:void(0);" id="reportAbuseLink">Report Abuse</a>
                    </span>
                </div>
                <br/>
            </div>

            <div  dojoType="dijit.Dialog" title="Report Abuse" id="reportAbuseDiv" style="width:200px" class="soria">
                <input id="abuseType1" dojoType="dijit.form.CheckBox" value="In appropriate content" /> <span>In appropriate content</span><br/><br/>
                <input id="abuseType2" dojoType="dijit.form.CheckBox" value="Spam" /> <span>Spam</span><br/><br/>
                <div align="center">
                    <button id="reportAbuseButton" dojoType="dijit.form.Button" class="">Send</button>
                </div>
            </div>
        </div>
    </body>
    <%--
    <script type='text/javascript' src="js/dojo-release-1.3.2/dojo/dojo.js" djConfig="parseOnLoad:true"></script>
    --%>
    <script type='text/javascript' src="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dojo/dojo.xd.js" djConfig="parseOnLoad:true"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type='text/javascript' src='dwr/interface/RoomateAjaxService.js'></script>
    <%--
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
    --%>
    <script type="text/javascript" src='js/roomate.js' ></script>
</html>
