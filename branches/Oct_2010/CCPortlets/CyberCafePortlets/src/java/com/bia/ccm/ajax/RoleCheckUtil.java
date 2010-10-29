/*
 * 
 */
package com.bia.ccm.ajax;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import javax.servlet.http.HttpSession;
import org.directwebremoting.WebContextFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class RoleCheckUtil {

    public boolean isSessionAlive() {
        //Check if session has timedout/invalidated
        HttpSession session = WebContextFactory.get().getSession(false);
        if (session == null) {
            return false;
        } else {
            ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
            long CC_GROUP_ID = (Long) session.getAttribute("CC_GROUP_ID");
            if (themeDisplay == null || themeDisplay.getScopeGroupId() != CC_GROUP_ID) {
                session.invalidate();
                return false;
            }
        }
        return true;
    }

    public boolean isUserGroupAdmin() {
        try {
            HttpSession session = WebContextFactory.get().getSession(false);
            ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
            long userId = themeDisplay.getUserId();
            long companyId = themeDisplay.getCompanyId();
            Role roleAdmin = RoleLocalServiceUtil.getRole(companyId, RoleConstants.COMMUNITY_ADMINISTRATOR);
            return RoleLocalServiceUtil.hasUserRole(userId, roleAdmin.getRoleId());
        } catch (PortalException ex) {
            logger.warn(ex.getMessage(), ex);
        } catch (SystemException ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return false;
    }

    public boolean isUserGroupOwner() {
        try {
            HttpSession session = WebContextFactory.get().getSession(false);
            ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
            long userId = themeDisplay.getUserId();
            long companyId = themeDisplay.getCompanyId();
            Role roleAdmin = RoleLocalServiceUtil.getRole(companyId, RoleConstants.COMMUNITY_OWNER);
            return RoleLocalServiceUtil.hasUserRole(userId, roleAdmin.getRoleId());
        } catch (PortalException ex) {
            logger.warn(ex.getMessage(), ex);
        } catch (SystemException ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return false;
    }

    public boolean isUserGroupMember() {
        try {
            HttpSession session = WebContextFactory.get().getSession(false);
            ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
            long userId = themeDisplay.getUserId();
            long companyId = themeDisplay.getCompanyId();
            Role roleMember = RoleLocalServiceUtil.getRole(companyId, RoleConstants.COMMUNITY_MEMBER);
            return RoleLocalServiceUtil.hasUserRole(userId, roleMember.getRoleId());
        } catch (PortalException ex) {
            logger.warn(ex.getMessage(), ex);
        } catch (SystemException ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return false;
    }
    protected static final Log logger = LogFactory.getLog(RoleCheckUtil.class);
}
