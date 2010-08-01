/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.portlet;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

/**
 *
 * @author intesar
 */
public class SessionUtil {

    public static void configure(PortletRequest portletRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        portletRequest.getPortletSession().setAttribute("THEME_DISPLAY", themeDisplay, PortletSession.APPLICATION_SCOPE);
        portletRequest.getPortletSession().setAttribute("CC_GROUP_ID", themeDisplay.getScopeGroupId(), PortletSession.APPLICATION_SCOPE);
    }

}
