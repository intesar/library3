package com.bizintelapps.cars.ajax;

import com.bizintelapps.cars.portlet.SessionHandler;
import javax.servlet.http.HttpSession;
import org.directwebremoting.WebContextFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class RoleCheckUtil {

    /**
     *  checks weather session has THEME_DISPLAY
     *  Lets do error handling in a more interesting way
     *  if session is empty create a new one
     *  if session doesn't have themedisplay invalidate session and try creating a new one to fix it
     * @return
     */
    public boolean isSessionAlive() {
        //Check if session has timedout/invalidated
        if (!checkSession()) {
            return checkSession();
        }
        return true;
    }

    /**
     * 
     * @return
     */
    private boolean checkSession() {
        HttpSession session = WebContextFactory.get().getSession(true);
        Object themeDisplay = session.getAttribute(SessionHandler.THEME_DISPLAY);
        if (themeDisplay == null) {
            session.invalidate();
            return false;
        }
        return true;
    }
    protected static final Log logger = LogFactory.getLog(RoleCheckUtil.class);
}
