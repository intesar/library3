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
        <a onclick="forwardFunction('dashboard2.jsp');">
            <img src="menu_icons/dashboard.png" alt="Dashboard" title="Dashboard" />            
        </a>
        
        <a onclick="forwardFunction('users.jsp');"><img src="menu_icons/controlpanel.png" title="Control Panel" />
        </a>
        
        <a onclick="forwardFunction('systems.jsp');"><img src="menu_icons/systems.png" title="Computers" alt="3"/>
        </a>        
        
        <a onclick="forwardFunction('services.jsp');"><img src="menu_icons/services.png" title="Services"  alt="Extra Services"/>
        </a>
        
        <a onclick="forwardFunction('emails.jsp');"><img src="menu_icons/emails.png" title="Emails/SMS"  alt="4 emails"/>
        </a>
        
        <a onclick="forwardFunction('emailtimings.jsp');"><img src="menu_icons/report time.png" title="Report Timing"  alt="5 Time"/>
        </a>
        
        <a onclick="forwardFunction('organization.jsp');"><img src="menu_icons/profile.png" title="Profile" alt="7 profile"/>
        </a>
        
        <a onclick="forwardFunction('systemlease.jsp');"><img src="menu_icons/rental history.png" title="Rental History" alt="9 history"/>
        </a>
        
        <a onclick="forwardFunction('report.jsp');"><img src="menu_icons/billing.png" title="Billing Report" alt="8 report"/>
        </a>
        
        <a onclick="forwardFunction('customer.jsp');"><img src="menu_icons/customer profile_2.png" title="Customer Profiles" alt="6"/>
        </a>
        
        <a onclick="forwardFunction('my_systemlease.jsp');">
            <img src="menu_icons/myhistory234.png" alt="Dashboard" title="My History" />
        </a>
        
        <a onclick="forwardFunction('j_acegi_logout');"><img src="menu_icons/logout.png" title="LogOut" alt="logout"/>
        </a> 
        
        <a onclick="refresh();"><img src="menu_icons/re.png" title="Refresh" alt="11 logout"/>
        </a> 
        
    </div>
    
    
    <div id="employeeDiv" style="visibility: hidden" align="center">
        <a onclick="forwardFunction('dashboard2.jsp');">
            <img src="menu_icons/dashboard.png" alt="Dashboard" title="Dashboard" />
        </a>
        
        <a onclick="forwardFunction('customer.jsp');"><img src="menu_icons/customer profile_2.png" title="Customer Profiles" alt="6"/>
        </a>
        
        <a onclick="forwardFunction('my_systemlease.jsp');">
            <img src="menu_icons/myhistory234.png" alt="Dashboard" title="My History" />
        </a>
         <a onclick="forwardFunction('j_acegi_logout');"><img src="menu_icons/logout.png" title="LogOut" alt="logout"/>
        </a>

        <a onclick="refresh();"><img src="menu_icons/re.png" title="Refresh" alt="11 logout"/>
        </a> 
    </div>
    
    <div id="customerDiv" style="visibility: hidden" align="center">
        
         <a onclick="forwardFunction('my_systemlease.jsp');">
            <img src="menu_icons/myhistory234.png" alt="Dashboard" title="My History" />
        </a>
         <a onclick="forwardFunction('j_acegi_logout');"><img src="menu_icons/logout.png" title="LogOut" alt="logout"/>
        </a>

        <a onclick="refresh();"><img src="menu_icons/re.png" title="Refresh" alt="11 logout"/>
        </a> 
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

