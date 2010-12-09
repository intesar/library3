package com.bizintelapps.cars.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author intesar
 */
@Component
public class SpringApplicationContextFactory implements ApplicationContextAware {

    protected static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static ApplicationContext getContext() {
        return ctx;
    }
}
