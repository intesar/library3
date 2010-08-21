<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<div id="cc_s_view" style="display:none">
    <div>
        <a href="javascript:void(0);" onclick="cc_s_new();"> Add Service </a>
    </div>
    <table  id="cc_s_table" cellspacing="1" >
        <thead>
            <tr>
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
</div>
<div id="cc_s_edit" style="display:none">
    <table>
        <tr>
            <td><label>Name</label></td>
            <td><input type="text" id="cc_s_name" /></td>
        </tr>
        <tr>
            <td><label>Units</label></td>
            <td><input type="text" id="cc_s_units" /></td>
        </tr>
        <tr>
            <td><label>Price</label></td>
            <td><input type="text" id="cc_s_price" /></td>
        </tr>
        <tr>
            <td><label>Units (Discount to apply)</label></td>
            <td><input type="text" id="cc_s_secondUnits" /></td>
        </tr>
        <tr>
            <td><label>Price (Discount price per unit)</label></td>
            <td><input type="text" id="cc_s_secondPrice" /></td>
        </tr>
        <tr>
            <td><input type="button" value="Save" onclick="cc_s_saveService();"
                       <input type="button" value="Delete" onclick="cc_s_deleteService();" /></td>
            <td><input type="button" value="Cancel Changes" onclick="cc_s_cancel();"</td>
        </tr>
    </table>
</div>

<script type='text/javascript' src='/CyberCafePortlets/dwr/engine.js'></script>
<script type='text/javascript' src='/CyberCafePortlets/dwr/interface/AjaxAdminService.js'></script>
<script type="text/javascript" src="/CyberCafePortlets/js/services.js"></script>
