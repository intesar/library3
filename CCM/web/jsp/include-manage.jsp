<style>
    #adminDiv span {

    }
    #mainDiv {
        background-image:url('../images/topNavBG.png');
        background-repeat:no-repeat;
        width:760px;
        height:50px;
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

<div id="mainDiv" align="center" style="font-size:20px;">
    <div id="adminDiv" align="center" style="text-decoration:none;">
        <a href="dashboard2.jsp" class="links"><< Home</a>
        &nbsp;&nbsp;
        <a href="systems.jsp" >PC</a>
        &nbsp;&nbsp;
        <a href="services.jsp" >Service</a>
        &nbsp;&nbsp;
        <a href="users.jsp" >Manager</a>
        &nbsp;&nbsp;
        <a href="emails.jsp" >Email</a>
        &nbsp;&nbsp;
        <a href="emailtimings.jsp" >Time</a>
        &nbsp;&nbsp;
        <a href="organization.jsp" >Company</a>
        &nbsp;&nbsp;
        <a href="membership_types.jsp" >Memberships</a>
        &nbsp;&nbsp;
        <span>
            <a href="../j_acegi_logout" >Sign Out</a>
        </span>
    </div>
</div>