
package com.bizintelapps.cars.service;

import com.bizintelapps.cars.entity.Car;
import com.bizintelapps.cars.entity.ResultDto;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface CarService {
    /**
     *
     * @param car
     */
    Car saveCar(Car car);

    /**
     *
     * @param carId
     * @return
     */
    Car getCar(Long carId);

    /**
     *
     * @return
     */
    List<Car> getAllCars();

    /**
     * 
     * @param photosFolderId
     * @return
     */
    Car getCarByImageFolder(Long photosFolderId);

   

    /**
     * 
     * @param priceLimit
     * @param mileageLimit
     * @param start
     * @param max
     * @return
     */
    ResultDto<Car> search(int priceLimit, int mileageLimit, int sortBy, int start, int max);
}
