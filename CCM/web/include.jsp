<script type='text/javascript' src='/CCM/dwr/interface/AjaxUserService.js'></script>

<!--
<script type='text/javascript' src='/CCM/dwr/engine.js'></script>
<script type='text/javascript' src='/CCM/dwr/util.js'></script>
-->


<script type="text/javascript">
    var isMenuCreated = false;
    function createMenu() {
        if ( !isMenuCreated ) {
            AjaxUserService.getUserRole(function(isAdmin) {
                showDiv(isAdmin);
                isMenuCreated = true;
            });
            
        }
    }
    function showDiv(role) {
        //var role = dwr.util.getValue("role_hidden");
        var m = document.getElementById("mainDiv");
        var a = document.getElementById("adminDiv");
        var b = document.getElementById("employeeDiv");
        var c = document.getElementById("customerDiv");
        //alert ( role );
        if ( role  == "admin" ) {        
            a.style.visibility="visible";            
            m.removeChild(b);
            m.removeChild(c);
        }
        else if ( role == "employee" ) {
            b.style.visibility="visible";            
            m.removeChild(a);
            m.removeChild(c);
        } else {
            c.style.visibility="visible";            
            m.removeChild(a);
            m.removeChild(b);
        }   
      
    } 
            
    function refresh() {
        window.location.reload();
    }
    function forwardFunction(link) {
        location.href=link;
    }
</script>



<div id="mainDiv">
    <div id="adminDiv" align="center">
        <a href="javascript:void(0);" onclick="forwardFunction('dashboard2.jsp');">Home</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('users.jsp');">Users</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('systems.jsp');">Systems</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('services.jsp');">Services</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('emails.jsp');">Emails</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('emailtimings.jsp');">Timings</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('organization.jsp');">Company</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('systemlease.jsp');">History</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('report.jsp');">Billing</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('customer.jsp');">Customers</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('my_systemlease.jsp');">My History</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('j_acegi_logout');">Logout</a>
                
    </div>
    
    
    <div id="employeeDiv" style="visibility: hidden" align="center">
        <a href="javascript:void(0);" onclick="forwardFunction('dashboard2.jsp');">Home</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('customer.jsp');">Customers</a>
        
        <a href="javascript:void(0);" onclick="forwardFunction('my_systemlease.jsp');">My History</a>

         <a href="javascript:void(0);" onclick="forwardFunction('j_acegi_logout');">Logout</a>
    </div>
    
    <div id="customerDiv" style="visibility: hidden" align="center">
        
         <a href="javascript:void(0);" onclick="forwardFunction('my_systemlease.jsp');">History</a>

         <a href="javascript:void(0);" onclick="forwardFunction('j_acegi_logout');">Logout</a>

    </div>
    <br>
    <div align="center">   
        <span id="successReply" style="background-color:#99ff66;"></span>            
        <span id="failureReply" style="background-color:#ff3300; font-weight:bold;"></span>
    </div>
</div>

<script type="text/javascript">
    window.onload = createMenu();
    function writeMessage(type,  message) {
        dwr.util.setValue (type, message);
        // clear message after 5 seconds
        setTimeout("clearMessages();", 5000);
    }
    function clearMessages() {
        dwr.util.setValue ("failureReply", "" );
        dwr.util.setValue ("successReply", "" );
    }
</script>

