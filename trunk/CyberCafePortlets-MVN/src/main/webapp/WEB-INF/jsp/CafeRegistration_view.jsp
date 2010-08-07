<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%-- Uncomment below lines to add portlet taglibs to jsp --%>
<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />
<%PortletPreferences prefs = renderRequest.getPreferences();%> 


<form action="<portlet:actionURL/>" >
    <table>
        <tr>
            <td>Organization ID</td><td><input type="text" id="cc_organizationId" /></td>
        </tr>
        <tr>
            <td>Organization Name</td><td><input type="text" id="cc_organizationName" /></td>
        </tr>
        <tr>
            <td>User Email</td><td><input type="text" id="cc_email" /></td>
        </tr>
        <tr>
            <td></td><td><input type="submit" value="Create" /></td>
        </tr>
    </table>
</form>