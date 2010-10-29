<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<style type="text/css">
    body { font-size: 62.5%; }
    label, input { display:block; }
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
    div#cc_o_view { margin: 20px 0; }
    div#cc_o_view table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#cc_o_view table td, div#cc_s_view table th { border: 0px; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
<div id="cc_o_view">
    <table  class="ui-widget ui-widget-content">
        <thead>
            <tr class="ui-widget-header ">
                <td colspan="2"> Always keep this information latest!</td>
            </tr>
        </thead>
        <tr>
            <td>
                <table>
                    <tr><td><label>Contact Person</label></td><td><input type="text" id="cc_o_contactPerson" class="text ui-widget-content ui-corner-all" /></td></tr>
                    <tr><td><label>Contact Email</label></td><td><input type="text" id="cc_o_contactEmail" class="text ui-widget-content ui-corner-all" /></td></tr>
                    <tr><td><label>Cafe Phone</label></td><td><input type="text" id="cc_o_cafePhone" class="text ui-widget-content ui-corner-all" /></td></tr>
                    <tr><td><label>Cafe Fax</label></td><td><input type="text" id="cc_o_cafeFax" class="text ui-widget-content ui-corner-all" /></td></tr>
                </table>
            </td>
            <td>
                <table>
                    <tr><td><label>Street</label></td><td><input type="text" id="cc_o_street" class="text ui-widget-content ui-corner-all" /></td></tr>
                    <tr><td><label>City</label></td><td><input type="text" id="cc_o_city" class="text ui-widget-content ui-corner-all" /></td></tr>
                    <tr><td><label>Zipcode</label></td><td><input type="text" id="cc_o_zipcode" class="text ui-widget-content ui-corner-all" /></td></tr>
                    <tr><td><label>Timings</label></td><td><input type="text" id="cc_o_timing" class="text ui-widget-content ui-corner-all" /></td></tr>
                </table>
            </td>
        </tr>
        <tr><td></td><td></td></tr>
        <tr><td></td><td>

            </td></tr>
    </table>
</div>
<span>
    <button id="cc_o_refreshBtn"> Refresh </button>
    <button id="cc_o_saveBtn" > Save </button>
</span>
<script type='text/javascript' src='/CyberCafePortlets/dwr/engine.js'></script>
<script type='text/javascript' src='/CyberCafePortlets/dwr/interface/AjaxAdminService.js'></script>
<script type="text/javascript" src="/CyberCafePortlets/js/cybercafeprofile.js"></script>