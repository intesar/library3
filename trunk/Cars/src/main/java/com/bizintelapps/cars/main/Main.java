
package com.bizintelapps.cars.main;

import com.bizintelapps.cars.entity.Car;
import com.bizintelapps.cars.service.CarService;
import com.bizintelapps.cars.service.impl.SpringApplicationContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author intesar
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
    	  new ClassPathXmlApplicationContext(new String[] {"applicationContext-Annotations.xml"});

        
        CarService carService = (CarService) SpringApplicationContextFactory.getContext().getBean("CarServiceImpl");
        Car car = new Car();
        car.setId(1L);
        carService.saveCar(car);
    }
}
