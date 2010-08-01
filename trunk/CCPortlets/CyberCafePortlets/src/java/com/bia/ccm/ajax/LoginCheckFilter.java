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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;

/**
 *
 * @author intesar
 */
public class LoginCheckFilter implements AjaxFilter {

    @Override
    public Object doFilter(Object obj, Method method, Object[] params, AjaxFilterChain chain) throws Exception {
        try {
            RoleCheckUtil roleCheckUtil = new RoleCheckUtil();
            if (roleCheckUtil.isSessionAlive()) {
                long startTime = Calendar.getInstance().getTimeInMillis();
                Object object = chain.doFilter(obj, method, params);
                if (logger.isTraceEnabled()) {
                    long endTime = Calendar.getInstance().getTimeInMillis();
                    long totalTime = endTime - startTime;
                    logger.trace(method.getName() + " : " + totalTime + " ms");
                }
                return object;
            } else {
                throw new LoginRequiredException();
            }
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
    protected static final Log logger = LogFactory.getLog(LoginCheckFilter.class);
}
