package com.bia.ccm.portlet;
import javax.portlet.GenericPortlet;
import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import java.io.IOException;
import javax.portlet.PortletRequestDispatcher;

/**
 * Products Portlet Class
 */
public class Products extends GenericPortlet {

    @Override
    public void processAction(ActionRequest request, ActionResponse response) throws PortletException,IOException {

    }
    
    @Override
    public void doView(RenderRequest request,RenderResponse response) throws PortletException,IOException {
        response.setContentType("text/html");        
        PortletRequestDispatcher dispatcher =
        getPortletContext().getRequestDispatcher("/WEB-INF/jsp/Products_view.jsp");
        dispatcher.include(request, response);
    }
}