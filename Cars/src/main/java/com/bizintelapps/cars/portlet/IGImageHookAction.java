package com.bizintelapps.cars.portlet;

import com.bizintelapps.cars.entity.Car;
import com.bizintelapps.cars.entity.Image;
import com.bizintelapps.cars.service.CarService;
import com.bizintelapps.cars.service.impl.SpringApplicationContextFactory;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.ModelListener;
import com.liferay.portlet.imagegallery.model.IGImage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IGImageHookAction implements ModelListener {

    @Override
    public void onAfterCreate(Object model) throws ModelListenerException {
        if (logger.isTraceEnabled()) {
            logger.trace("trying to add image details to car object");
        }

        try {
            IGImage igImage = (IGImage) model;
            CarService carService = (CarService) SpringApplicationContextFactory.getContext().getBean("CarServiceImpl");
            Car car = carService.getCarByImageFolder(igImage.getFolderId());
            if (logger.isTraceEnabled()) {
                logger.trace(car.getId() +" " + car.getMake() + igImage.getImageId());
            }

            Image image = new Image(igImage.getUuid(), igImage.getGroupId(), false);
            image.setImageId(igImage.getImageId());
            image.setSmallImageId(igImage.getSmallImageId());
            image.setLargeImageId(igImage.getLargeImageId());
            car.getImages().add(image);
            carService.saveCar(car);
            if (logger.isTraceEnabled()) {
                logger.trace("Successfully added car object");
            }
        } catch (RuntimeException re) {
            logger.warn(" Error adding Image ", re);
            throw re;
        }
    }

    @Override
    public void onAfterAddAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
        //Add your implementation here
    }

    @Override
    public void onAfterRemove(Object model) throws ModelListenerException {
        if (logger.isTraceEnabled()) {
            logger.trace("trying to remove image details from car object");
        }
        try {
            IGImage igImage = (IGImage) model;
            CarService carService = (CarService) SpringApplicationContextFactory.getContext().getBean("CarServiceImpl");
            Car car = carService.getCarByImageFolder(igImage.getFolderId());
            Image image = new Image(igImage.getUuid(), igImage.getGroupId(), false);
            car.getImages().remove(image);
            carService.saveCar(car);
            if (logger.isTraceEnabled()) {
                logger.trace("Successfully deleted car object");
            }
        } catch (RuntimeException re) {
            logger.warn(" Error adding Image ", re);
            throw re;
        }
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
    protected static final Log logger = LogFactory.getLog(IGImageHookAction.class);
}
