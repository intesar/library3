package com.bizintelapps.cars.service.impl;

import com.bizintelapps.cars.entity.ResultDto;
import com.bizintelapps.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import com.bizintelapps.cars.entity.Car;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author intesar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-Annotations.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CarServiceImplTest {

    @Autowired
    CarService carService;

    public CarServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        for (int i = 1; i < 100; i++) {
            Car car = new Car();
            car.setAcFront(true);
            car.setMileage(1000L * i);
            car.setAskingPrice(1000.0 * i);
            carService.saveCar(car);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of saveCar method, of class CarServiceImpl.
     */
    @Test
    public void testAddCar() {
        System.out.println("addCar");
        Car car = new Car();
        car.setAcFront(true);
        carService.saveCar(car);
    }

    /**
     * Test of saveCar method, of class CarServiceImpl.
     */
    @Test
    public void testSearch() {
        ResultDto<Car> dto = carService.search(0, 0, 0, 10);
        assertNotNull(dto.getList());
        assertTrue(dto.getList().size() == 10 );
    }


    @Test
    public void testSearch1() {
        ResultDto<Car> dto = carService.search(10, 0, 0, 10);
        assertNotNull(dto.getList());
        System.out.println (dto.getList().size() );
        assertTrue(dto.getList().size() == 5 );
    }

    @Test
    public void testSearch2() {
        ResultDto<Car> dto = carService.search(0, 10, 0, 10);
        assertNotNull(dto.getList());
        System.out.println (dto.getList().size() );
        assertTrue(dto.getList().size() == 10 );
    }
}
