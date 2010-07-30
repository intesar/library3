<%-- 
    Document   : systems
    Created on : Jul 7, 2008, 5:43:08 AM
    Author     : intesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            a:link    {color:black; text-decoration:none; font-size:11pt}
            a:hover   {color:black; text-decoration:none; 
                font-size:11pt}
            a:active  {color:black; text-decoration:none; font-size:11pt}
            a:visited {color:black; text-decoration:none; font-size:11pt}
        </style>
        <style>
            a:link    {color:black; text-decoration:none; font-size:11pt}
            a:hover   {color:black; text-decoration:none; 
                font-size:11pt}
            a:active  {color:black; text-decoration:none; font-size:11pt}
            a:visited {color:black; text-decoration:none; font-size:11pt}
        </style>
        <script type='text/javascript' src='/CCM/dwr/interface/AjaxAdminService.js'></script>
        <script type='text/javascript' src='/CCM/dwr/engine.js'></script>        
        <script type='text/javascript' src='/CCM/dwr/util.js'></script>
        
        <script type="text/javascript">
            function init() {
                fillTable();
            }
        
          
        
           
        
            
        
            function writePerson() {
                //var person = { id:null, name:null, address:null, salary:null };
                dwr.util.getValues(person);
        
                //dwr.engine.beginBatch();
                //People.setPerson(person);
                //AjaxAdminService.saveSystems(person);
                
                //dwr.engine.endBatch();
            }
        
            function clearPerson() {
                viewed = null;
                //dwr.util.setValues({ id:null, name:null, description:null, minuteRate:null, enabled:true, macAddress:null });
            }
        </script>
       <jsp:include page="table_style.jsp" ></jsp:include>
    </head>
    <body>
        
        <jsp:include page="include.jsp" />
        
        
      
        
        <table align="center">
            
            <thead>
                <tr>
                    <th>
                        Feedback
                    </th>
                    <th></th>
                </tr>
            
            </thead>
            <tr>
                <td>Type:</td>
                <td><select name="type">
                        <option>Enhancement</option>
                        <option>Report Bug</option>
                        <option>Compliment</option>
                        <option>Suggestion</option>                        
                        <option>Remark</option>
                        <option>Other</option>
                </select></td>
            </tr>
            <tr>
                <td>Comment:</td>
                <td><textarea name="comment" rows="4" cols="40">
                </textarea></td>
            </tr>
            
            <tr>
                <td></td>
                <td align="right">                    
                    <input type="button" value="Save" onclick="writePerson()"/>
                    <input type="button" value="Clear" onclick="clearPerson()"/>
                </td>
            </tr>
        </table>
        
    </body>
    
    
    <p align="center">
        <font size="2"> &copy; Copyrights BizIntelApps 2008 All Rights Reserved. <a href="http://www.bizintelapps.com/"><font color="blue">BizIntelApps</font></a> </font>
    </p>
</html>
