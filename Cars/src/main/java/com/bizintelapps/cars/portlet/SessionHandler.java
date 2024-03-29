package com.bizintelapps.cars.portlet;

import com.bizintelapps.cars.service.CarService;
import com.bizintelapps.cars.service.impl.SpringApplicationContextFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.imagegallery.model.IGFolder;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

/**
 *
 * @author intesar
 */
public class SessionHandler {

    /**
     * set cnams userId
     */
    public static void configure(PortletRequest portletRequest) {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
            if ( logger.isTraceEnabled()) {
                logger.trace(" setting themeDisplay into session " + themeDisplay);
            }
            portletRequest.getPortletSession().setAttribute(THEME_DISPLAY, themeDisplay, PortletSession.APPLICATION_SCOPE);
            ServiceContext sc = ServiceContextFactory.getInstance(IGFolder.class.getName(), portletRequest);
            portletRequest.getPortletSession().setAttribute(SERVICE_CONTEXT, sc, PortletSession.APPLICATION_SCOPE);

            if ( logger.isTraceEnabled()) {
                logger.trace(" added ServiceContext " + sc);
                logger.trace(" themeDisplay from portletSession -- " + portletRequest.getPortletSession().getAttribute(THEME_DISPLAY, PortletSession.APPLICATION_SCOPE));
            }
            // Remove later
            CarService service = (CarService) SpringApplicationContextFactory.getContext().getBean("CarServiceImpl");
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    protected static final Log logger = LogFactory.getLog(SessionHandler.class);
    public static final String THEME_DISPLAY = "THEME_DISPLAY";
    public static final String SERVICE_CONTEXT = "SERVICE_CONTEXT";
}
