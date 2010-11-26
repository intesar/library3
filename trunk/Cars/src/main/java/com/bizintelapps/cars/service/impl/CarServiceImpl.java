package com.bizintelapps.cars.service.impl;

import com.bizintelapps.cars.dao.CarDao;
import com.bizintelapps.cars.entity.Car;
import com.bizintelapps.cars.entity.ResultDto;
import com.bizintelapps.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author intesar
 */
@Service(value = "CarServiceImpl")
public class CarServiceImpl implements CarService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCar(Car car) {
        // validate
        // save
        if (car.getId() == null) {
            carDao.persist(car);
        } else {
            carDao.merge(car);
        }
    }

    @Override
    public Car getCar(Long carId) {
        return carDao.find(carId);
    }

    /**
     *
     *
     * @param priceLimit
     *         remove filter  --> 0
     *         less then $5000 --> 10
     *         $5,000 - $10,000 --> 20
     *         $10,000 or more --> 30
     *
     * @param mileageLimit
     *         remove filter  --> 0
     *         less then 30,000 --> 10
     *         30,000 - 50,000 --> 20
     *         50,000 or more --> 30
     * @param start
     * @param max
     * @return
     */
    @Override
    public ResultDto<Car> search(int priceLimit, int mileageLimit, int start, int max) {
        ResultDto<Car> dto = new ResultDto<Car>();
        dto.setList(carDao.search(priceLimit, mileageLimit, start, max));
        return dto;
    }
    /** private methods **/
    @Autowired
    protected CarDao carDao;
}
