/*
 * 
 */
package com.bia.ccm.ajax;

import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.exceptions.UnknownException;
import com.bia.ccm.exceptions.LoginRequiredException;
import java.lang.reflect.Method;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;
import org.directwebremoting.WebContextFactory;

/**
 *
 * @author intesar
 */
public class AjaxInterceptingFilter implements AjaxFilter {

    @Override
    public Object doFilter(Object obj, Method method, Object[] params, AjaxFilterChain chain) throws Exception {

        try {
            long startTime = Calendar.getInstance().getTimeInMillis();

            validateSession();

            Object object = chain.doFilter(obj, method, params);

            if (logger.isTraceEnabled()) {
                long endTime = Calendar.getInstance().getTimeInMillis();
                long totalTime = endTime - startTime;
                logger.trace(method.getName() + " : " + totalTime + " ms");
            }
            return object;
        } catch (LoginRequiredException ex) {
            logger.warn(ex.getMessage(), ex);
            throw ex;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            throw ex;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            throw new UnknownException();
        }
    }

    private void validateSession() throws LoginRequiredException {
        //Check if session has timedout/invalidated
        HttpSession session = WebContextFactory.get().getSession(false);
        if (session == null) {
            throw new LoginRequiredException();
        } else {
            Object themeDisplay = session.getAttribute("THEME_DISPLAY");
            if (themeDisplay == null) {
                session.invalidate();
                throw new LoginRequiredException();
            }
        }
    }
    protected static final Log logger = LogFactory.getLog(AjaxAdminService.class);
}
