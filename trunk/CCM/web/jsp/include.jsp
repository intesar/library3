<script type='text/javascript' src='../dwr/interface/AjaxUserService.js'></script>

<!--
<script type='text/javascript' src='/CCM/dwr/engine.js'></script>
<script type='text/javascript' src='/CCM/dwr/util.js'></script>
-->


<script type="text/javascript">
    var isMenuCreated = false;
    dwr.util.useLoadingMessage("Please Wait");
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


<style>
    #adminDiv span {

    }
    #mainDiv {
        background-image:url('../images/topNavBG.png');
        background-repeat:no-repeat;
        width:760px;
    }
    #adminDiv {
        top:15px;
        vertical-align:bottom;
    }
    #adminDiv a{
        font-weight:normal;
        font-size:13px;
        color:white;
        cursor:pointer;
        text-decoration:none;
        font-family:Arial, Hevelectica, sans-serif;

    }
    #adminDiv a:hover {
        text-decoration:underline;
        cursor:pointer;
    }
</style>
<div id="mainDiv" align="center" style="font-size:12px;">
    <div id="adminDiv" align="center" style="text-decoration:none;">
        <a href="dashboard2.jsp" class="links">Home</a>
        &nbsp;&nbsp;
        <a href="systemlease.jsp" >History</a>
        &nbsp;&nbsp;
        <a href="report.jsp" >Billing</a>
        &nbsp;&nbsp;
        <a href="customer.jsp" >Customer</a>
        &nbsp;&nbsp;
        <a href="my_systemlease.jsp" >My-History</a>
        &nbsp;&nbsp;
        <a href="memberships.jsp" >Members</a>
        &nbsp;&nbsp;
        <a href="systems.jsp" >Manage</a>
        &nbsp;&nbsp;
        <span>
            <a href="../j_acegi_logout" >Sign Out</a>
        </span>
    </div>


    <div id="employeeDiv" style="visibility: hidden" align="center">
        <a href="dashboard2.jsp" >Home</a>

        <a href="customer.jsp" >Customers</a>

        <a href="my_systemlease.jsp" >My History</a>

        <a href="../j_acegi_logout" >Sign Out</a>
    </div>

    <div id="customerDiv" style="visibility: hidden" align="center">

        <a href="my_systemlease.jsp" >My History</a>

        <a href="../j_acegi_logout" >Sign Out</a>

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

