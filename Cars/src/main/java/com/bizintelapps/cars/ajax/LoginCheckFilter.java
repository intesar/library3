package com.bizintelapps.cars.ajax;

import com.bizintelapps.cars.exceptions.LoginRequiredException;
import com.bizintelapps.cars.exceptions.UnknownException;
import java.lang.reflect.Method;
import java.util.Calendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;

/**
 *
 * @author intesar
 *
 * Filter for checking user has session
 */
public class LoginCheckFilter implements AjaxFilter {

    @Override
    public Object doFilter(Object obj, Method method, Object[] params, AjaxFilterChain chain) throws Exception {
        try {
            if (roleCheckUtil.isSessionAlive()) {
                long startTime = Calendar.getInstance().getTimeInMillis();
                Object object = chain.doFilter(obj, method, params);
                long totalTime = Calendar.getInstance().getTimeInMillis()- startTime;
                if (logger.isTraceEnabled() && totalTime < 1000) {
                    logger.trace(" Execution of : " + method.getName() + " took : " + totalTime + " ms");
                } else {
                    logger.warn(" Execution of : " + method.getName() + " took : " + totalTime + " ms to execute");
                }
                return object;
            } else {
                throw new LoginRequiredException();
            }
        } catch (LoginRequiredException ex) {
            logger.warn(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            throw new UnknownException("Error please try again, or try after sometime");
        }
    }
    protected RoleCheckUtil roleCheckUtil = new RoleCheckUtil();
    protected static final Log logger = LogFactory.getLog(LoginCheckFilter.class);
}
