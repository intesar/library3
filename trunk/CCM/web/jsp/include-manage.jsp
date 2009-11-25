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
    }
    a {        
        text-decoration:none;
    }
    .white {
        color:#FFFFFF;
        text-decoration:none;
    }
    #welcomeDiv a {
        color:#FFFFFF;
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

<div id="mainDiv" align="center" style="font-size:20px;">
    <div id="adminDiv" align="center" style="text-decoration:none;">
        <a href="dashboard2.jsp" class="links"><< Home</a>
        &nbsp;&nbsp;
        <a href="systems.jsp" >PC</a>
        &nbsp;&nbsp;
        <a href="services.jsp" >Extras</a>
        &nbsp;&nbsp;
        <a href="users.jsp" >Manager</a>
        &nbsp;&nbsp;
        <a href="emails.jsp" >Notification</a>
        &nbsp;&nbsp;
        <a href="organization.jsp" >Profile</a>
    </div>
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