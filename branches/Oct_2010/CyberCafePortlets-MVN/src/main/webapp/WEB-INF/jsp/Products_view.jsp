<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<style type="text/css">
    body { font-size: 62.5%; }
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#cc_s_view { margin: 20px 0; }
    div#cc_s_view table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#cc_s_view table td, div#cc_s_view table th { border: 0px; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
</style>
<div id="cc_s_view">
    <table  id="cc_s_table" class="ui-widget ui-widget-content">
        <thead>
            <tr class="ui-widget-header" >
                <th> Name </th>
                <th> Units </th>
                <th> Price </th>
                <th> Discount Units</th>
                <th> Discount Price</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' src='/CyberCafePortlets/dwr/engine.js'></script>
<script type='text/javascript' src='/CyberCafePortlets/dwr/interface/AjaxCafeService.js'></script>
<script type="text/javascript" src="/CyberCafePortlets/js/products.js"></script>
