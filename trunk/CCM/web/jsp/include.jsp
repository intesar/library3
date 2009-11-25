
<%@page import="com.bia.ccm.util.AcegiUtil" %> 

<style>
    
    #mainDiv {
        background-image:url('../images/topNavBG.png');
        background-repeat:no-repeat;
        width:760px;
        height:50px;
        clear:both;
        
    }
    #adminDiv {
        top:15px;
        vertical-align:bottom;        
    }
    #welcomeDiv {
        top:15px;
        position:absolute;
        right:30px;
        height:50px;        
        float:right;
        color:#FFFFFF;
        font-size:0.75em;
    }
    a {
        color:#FFFFFF;
        text-decoration:none;
    }
    .white {
        color:#FFFFFF;
        text-decoration:none;
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
<%
    String role = AcegiUtil.getUserRole();
%>
<div id="mainDiv" align="center" style="font-size:20px;">
    <% if ( role.equals("admin")) { %>
    <div id="adminDiv" align="center" style="text-decoration:none;">
        <a href="dashboard2.jsp" class="links">Home</a>        
        &nbsp;&nbsp;
        <a href="systemlease.jsp" >Report</a>
        &nbsp;&nbsp;
        <a href="systems.jsp" >Manage</a>                
    </div>
    <% } else if ( role.equals("employee")) { %>
    <div id="employeeDiv" style="visibility: hidden" align="center">
        <a href="dashboard2.jsp" >Home</a>
        &nbsp;&nbsp;
        <a href="my_systemlease.jsp" >My History</a>
    </div>
    <% } else { %>
    <div id="customerDiv" style="visibility: hidden" align="center">
        <a href="my_systemlease.jsp" >My History</a>
    </div>
    <% } %>
</div>

<div id="welcomeDiv" >
    <span>
        Hi
    </span>
    <span><%= AcegiUtil.getUsername().split("@")[0] %></span>
    <span>
        | <a href="../j_acegi_logout" >Log out</a>
    </span>    
</div>

