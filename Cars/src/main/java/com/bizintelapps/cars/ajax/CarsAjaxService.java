package com.bizintelapps.cars.ajax;

import com.bizintelapps.cars.entity.Car;
import com.bizintelapps.cars.entity.ResultDto;
import com.bizintelapps.cars.service.CarService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author intesar
 */
public class CarsAjaxService {
    /**
     *
     * @param car
     */
    public void saveCar(Car car) {
        carService.saveCar(car);
    }

    /**
     *
     * @param carId
     * @return
     */
    public Car getCar(Long carId) {
        Car car = carService.getCar(carId);
        if ( log.isTraceEnabled() ) {
            log.trace( car.toString() );
        }
        return car;
    }

   
    /**
     * User can search cars by applying easy filters on home page
     * @param priceLimit
     * Filter Price
     *  $5000 or less
     *  $5000 - $10000
     *  $1000 or more
     * @param mileageLimit
     * Filter Mileage
     *  50,000 or less
     *  50,000 - 70,000
     *  70,000 or more
     * @param start -- begin with 0
     * @param max -- max elements to return
     *
     * @return
     */
    public ResultDto<Car> search(int priceLimit, int mileageLimit, int start, int max) {
        return carService.search(priceLimit, mileageLimit, start, max);
    }


    /** private methods **/
    protected static final Log log = LogFactory.getLog(CarsAjaxService.class);
    @Autowired
    protected CarService carService;
}
