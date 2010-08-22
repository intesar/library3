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
    div#cc_p_view { margin: 20px 0; }
    div#cc_p_view table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#cc_p_view table td, div#cc_s_view table th { border: 0px; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
<div id="cc_p_view">
    <table  class="ui-widget ui-widget-content">
        <thead><tr class="ui-widget-header "><th>Email</th><th>Time</th></tr></thead>
        <tbody>
            <tr>
                <td>
                    <table border="0">
                        <tr><td><input type="text" id="cc_p_email0" class="text ui-widget-content ui-corner-all" /> (example@gmail.com)</td></tr>
                        <tr><td><input type="text" id="cc_p_email1" class="text ui-widget-content ui-corner-all" /></td></tr>
                        <tr><td><input type="text" id="cc_p_email2" class="text ui-widget-content ui-corner-all" /></td></tr>
                        <tr><td><input type="text" id="cc_p_email3" class="text ui-widget-content ui-corner-all" /></td></tr>
                        <tr><td><input type="text" id="cc_p_email4" class="text ui-widget-content ui-corner-all" /></td></tr>
                    </table>
                </td>
                <td>
                    <table  border="0">
                        <tr><td><input type="text" id="cc_p_timing0" class="text ui-widget-content ui-corner-all" /> (0-2400 eg 900 is 9AM)</td></tr>
                        <tr><td><input type="text" id="cc_p_timing1" class="text ui-widget-content ui-corner-all" /></td></tr>
                        <tr><td><input type="text" id="cc_p_timing2" class="text ui-widget-content ui-corner-all" /></td></tr>
                        <tr><td><input type="text" id="cc_p_timing3" class="text ui-widget-content ui-corner-all" /></td></tr>
                        <tr><td><input type="text" id="cc_p_timing4" class="text ui-widget-content ui-corner-all" /></td></tr>
                    </table>
                </td>
            </tr>
        </tbody>
    </table>

    <div>
        <button id="cc_p_refreshBtn" > Refresh </button>
        <button id="cc_p_saveBtn" > Save </button>
    </div>
</div>
<script type='text/javascript' src='/CyberCafePortlets/dwr/engine.js'></script>
<script type='text/javascript' src='/CyberCafePortlets/dwr/interface/AjaxAdminService.js'></script>
<script type="text/javascript" src="/CyberCafePortlets/js/preferences.js"></script>
