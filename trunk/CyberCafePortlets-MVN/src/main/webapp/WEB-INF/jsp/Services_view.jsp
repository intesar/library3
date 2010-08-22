<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<style type="text/css">
    body { font-size: 62.5%; }
    label, input { display:block; }
    input.text {  }
    input[type='text']{
        margin-bottom:12px; width:95%; padding: .4em;background-image: none;border-color:inherit;border-width: 0px;
    }
    input.text{background:url("images/ui-bg_loop_25_000000_21x21.png") repeat scroll 50% 50% #000000;
                      border:1px solid #555555;
                      color:#FFFFFF;}
    input[type='text']:hover,button:hover{background: none;border-color:inherit;color:inherit;}
    input[type='button'],button{text-shadow:0px 0px #FFFFFF;}
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#cc_s_view { margin: 20px 0; }
    div#cc_s_view table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#cc_s_view table td, div#cc_s_view table th { border: 0px; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
<div id="cc_s_view">
    <table  id="cc_s_table" class="ui-widget ui-widget-content">
        <thead>
            <tr class="ui-widget-header ">
                <th> Name </th>
                <th> Units </th>
                <th> Price </th>
                <th> Discount Units</th>
                <th> Discount Price</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <div>
        <button id="new-service" >Add Service</button>
    </div>
</div>
<div id="cc_s_edit"  title="Add/Edit Service">
    <p class="validateTips">Name, Units & Price are mandatory</p>
    <table>
        <tr>
            <td><label>Name</label></td>
            <td><input type="text" id="cc_s_name" class="text ui-widget-content ui-corner-all" /></td>
        </tr>
        <tr>
            <td><label>Units</label></td>
            <td><input type="text" id="cc_s_units" class="text ui-widget-content ui-corner-all" /></td>
        </tr>
        <tr>
            <td><label>Price</label></td>
            <td><input type="text" id="cc_s_price" class="text ui-widget-content ui-corner-all" /></td>
        </tr>
        <tr>
            <td><label>Units (Discount to apply)</label></td>
            <td><input type="text" id="cc_s_secondUnits" class="text ui-widget-content ui-corner-all" /></td>
        </tr>
        <tr>
            <td><label>Price (Discount price per unit)</label></td>
            <td><input type="text" id="cc_s_secondPrice" class="text ui-widget-content ui-corner-all" /></td>
        </tr>
        <!--
        <tr>
            <td><input type="button" value="Save" onclick="cc_s_saveService();"
                       <input type="button" value="Delete" onclick="cc_s_deleteService();" /></td>
            <td><input type="button" value="Cancel Changes" onclick="cc_s_cancel();"</td>
        </tr>
        -->
    </table>
</div>

<script type='text/javascript' src='/CyberCafePortlets/dwr/engine.js'></script>
<script type='text/javascript' src='/CyberCafePortlets/dwr/interface/AjaxAdminService.js'></script>
<script type="text/javascript" src="/CyberCafePortlets/js/services.js"></script>
