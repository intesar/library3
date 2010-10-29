/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.util;

import com.bia.ccm.services.AdminService;
import java.util.List;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author intesar
 */
public class ServiceFactory {

    public ServiceFactory() {
//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                new String[]{
//                    "classpath:context/ApplicationContext-Dao.xml",
//                    "classpath:context/ApplicationContext-Service.xml",
//                    "classpath:context/ApplicationContext-Transactions.xml"
//                });

         ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{
                    "classpath:context/ApplicationContext-AjaxService.xml",
                    "classpath:context/ApplicationContext.xml",
                    "classpath:context/remoting-servlet.xml",
                    "classpath:context/ApplicationContext-Dao.xml",
                    "classpath:context/ApplicationContext-Service.xml",
                    "classpath:context/ApplicationContext-Transactions.xml",
                    "classpath:context/ApplicationContext-Schedule.xml"
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
        ApplicationContext c = new ClassPathXmlApplicationContext(
                new String[]{
                    "classpath:context/ApplicationContext-AjaxService.xml",
                    "classpath:context/ApplicationContext.xml",
                    "classpath:context/remoting-servlet.xml",
                    "classpath:context/ApplicationContext-Dao.xml",
                    "classpath:context/ApplicationContext-Service.xml",
                    "classpath:context/ApplicationContext-Transactions.xml",
                    "classpath:context/ApplicationContext-Schedule.xml"
                });

        BeanFactory f = (BeanFactory) c;
        AdminService as = (AdminService) serviceFactory.factory.getBean("adminServiceImpl");
        
    }
}
