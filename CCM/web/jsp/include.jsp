
<%@page import="com.bia.ccm.util.AcegiUtil" %> 

<style type="">

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
    }
    #welcomeDiv a {
        color:#FFFFFF;
        text-decoration:none;
    }
    #welcomeDiv a:hover {
        border-bottom:1px dotted #FFFFFF;
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

<div id="welcomeDiv" >
    <span>
        Hi
    </span>
    <span><%= AcegiUtil.getUsername().split("@")[0]%></span>
    <% if (role.equals("admin")) {%>
    <span> | <a href="systems.jsp" >Admin</a> </span>
    <% }%>
    <span>
        | <a href="../j_acegi_logout" >Log out</a>
    </span>    
</div>

