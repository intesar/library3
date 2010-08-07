package com.bia.ccm.portlet;

import com.bia.ccm.services.OrganizationService;
import javax.portlet.GenericPortlet;
import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import java.io.IOException;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequestDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

/**
 * CafeRegistration Portlet Class
 */
public class CafeRegistration extends GenericPortlet {

    @Override
    public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
        if(logger.isTraceEnabled()) {
            logger.trace( " inside processAction ");
        }
        long organizationId = Long.parseLong(request.getParameter("cc_organizationId"));
        String organizationName = request.getParameter("cc_organizationName");
        String email = request.getParameter("cc_email");
        if(logger.isTraceEnabled()) {
            logger.trace( organizationId + " " + organizationName + email);
        }
        PortletContext portletContext = this.getPortletContext();
        ApplicationContext context = PortletApplicationContextUtils.getWebApplicationContext(portletContext);
        OrganizationService organizationService = (OrganizationService) context.getBean("organizationServiceImpl");
        organizationService.registerNewOrganization(organizationId, organizationName, email);
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        response.setContentType("text/html");
        PortletRequestDispatcher dispatcher =
                getPortletContext().getRequestDispatcher("/WEB-INF/jsp/CafeRegistration_view.jsp");
        dispatcher.include(request, response);
    }

    protected static final Log logger = LogFactory.getLog(CafeRegistration.class);

}
