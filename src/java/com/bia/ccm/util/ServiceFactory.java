/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author intesar
 */
public class ServiceFactory {
  public ServiceFactory() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{
                    "classpath:context/ApplicationContext-Dao.xml",
                    "classpath:context/ApplicationContext-Service.xml",
                    "classpath:context/ApplicationContext-Transactions.xml"
                });

// an ApplicationContext is also a BeanFactory (via inheritance)
        factory = (BeanFactory) context;
    }
    private BeanFactory factory = null;
    
    private static ServiceFactory serviceFactory = new ServiceFactory();

    /**
     * 
     * @param name userServiceImpl, contactServiceImpl, emailsServiceImpl
     * @return
     */
    public static Object getService(String name) {
        return serviceFactory.factory.getBean(name);
    }

    public static void main(String[] args) {
     

    }

    
}
