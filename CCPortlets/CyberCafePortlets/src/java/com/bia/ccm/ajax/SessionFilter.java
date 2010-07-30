/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.ajax;

import java.lang.reflect.Method;
import javax.servlet.http.HttpSession;
import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.LoginRequiredException;

/**
 *
 * @author intesar
 */
public class SessionFilter  implements AjaxFilter {

    @Override
    public Object doFilter(Object obj, Method method, Object[] params, AjaxFilterChain chain) throws Exception {

        System.out.println ("inside doFilter...");
        //Check if session has timedout/invalidated
        HttpSession session = WebContextFactory.get().getSession(false);
        if (session == null) {
            //Throw an exception
            throw new LoginRequiredException("This operation requires login.");
        } else {
            String msg = (String) session.getAttribute("msg");
            System.out.println ( "msg : " + msg );
            if ( msg != null && msg.equals("invalidate")){
                session.invalidate();
                System.out.println("session invalidated..");
                throw new LoginRequiredException("This operation requires login.");
            }
        }

        return chain.doFilter(obj, method, params);

    }
}
