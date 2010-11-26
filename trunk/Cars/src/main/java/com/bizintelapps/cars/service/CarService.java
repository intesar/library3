
package com.bizintelapps.cars.service;

import com.bizintelapps.cars.entity.Car;
import com.bizintelapps.cars.entity.ResultDto;

/**
 *
 * @author intesar
 */
public interface CarService {
    /**
     *
     * @param car
     */
    void saveCar(Car car);

    /**
     *
     * @param carId
     * @return
     */
    Car getCar(Long carId);

   

    /**
     * 
     * @param priceLimit
     * @param mileageLimit
     * @param start
     * @param max
     * @return
     */
    ResultDto<Car> search(int priceLimit, int mileageLimit, int start, int max);
}
