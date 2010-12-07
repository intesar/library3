package com.bizintelapps.cars.portlet;
import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import java.io.IOException;
import javax.portlet.PortletRequestDispatcher;

/**
 * ManageCarDetails Portlet Class
 * @author Intesar
 */
public class ManageCarDetails extends GenericPortlet {

    @Override
    public void doView(RenderRequest request,RenderResponse response) throws PortletException,IOException {
        response.setContentType("text/html");
        SessionHandler.configure(request);
        PortletRequestDispatcher dispatcher =
        getPortletContext().getRequestDispatcher("/WEB-INF/jsp/ManageCarDetails_view.jsp");
        dispatcher.include(request, response);
    }
}