package com.bizintelapps.cars.portlet;

import com.bizintelapps.cars.entity.Car;
import com.bizintelapps.cars.service.CarService;
import com.bizintelapps.cars.service.impl.SpringApplicationContextFactory;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.ModelListener;
import com.liferay.portlet.imagegallery.model.IGFolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IGFolderHookAction implements ModelListener {

    @Override
    public void onAfterCreate(Object model) throws ModelListenerException {
    }

    @Override
    public void onAfterAddAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
        //Add your implementation here
    }

    @Override
    public void onAfterRemove(Object model) throws ModelListenerException {
        IGFolder igFolder = (IGFolder) model;
        if (logger.isTraceEnabled()) {
            logger.trace("removing image folder from car object");
        }
        CarService carService = (CarService) SpringApplicationContextFactory.getContext().getBean("CarServiceImpl");
        Car car = carService.getCarByImageFolder(igFolder.getFolderId());
        car.setPhotosFolderId(null);
        carService.saveCar(car);
    }

    @Override
    public void onAfterRemoveAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
        //Add your implementation here
    }

    @Override
    public void onAfterUpdate(Object model) throws ModelListenerException {
        //Add your implementation here
    }

    @Override
    public void onBeforeAddAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
        //Add your implementation here
    }

    @Override
    public void onBeforeCreate(Object model) throws ModelListenerException {
        //Add your implementation here
    }

    @Override
    public void onBeforeRemove(Object model) throws ModelListenerException {
        //Add your implementation here
    }

    @Override
    public void onBeforeRemoveAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
        //Add your implementation here
    }

    @Override
    public void onBeforeUpdate(Object model) throws ModelListenerException {
        //Add your implementation here
    }

    protected static final Log logger = LogFactory.getLog(IGFolderHookAction.class);
}
