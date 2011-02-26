package com.bizintelapps.cars.service.impl;

import java.util.List;
import com.bizintelapps.cars.entity.Image;
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
    public void testAddCar1() {
        System.out.println("addCar");
        Car car = new Car();
        car.setAcFront(true);
        Image image = new Image("abc", 11L, false, 1L, 1L, 1L);
        car.getImages().add(image);
        carService.saveCar(car);
    }

    /**
     * Test of saveCar method, of class CarServiceImpl.
     */
    @Test
    public void testRemoveImage() {
        ResultDto<Car> dto = carService.search(0, 0, 0, 0, 1);
        assertNotNull(dto.getList());
        assertTrue(dto.getList().size() == 1);
        Car car = dto.getList().get(0);
        Image image = new Image("abc", 11L, false, 1L, 1L, 1L);
        car.getImages().add(image);
        car = carService.saveCar(car);
        car.getImages().remove(image);
        car = carService.saveCar(car);
        //assertTrue(car.getImages().size() == 0);
    }

    /**
     * Test of getAllCars method, of class CarServiceImpl.
     */
    @Test
    public void testGetAllCars() {
        System.out.println("getAllCars");
        List<Car> cars = carService.getAllCars();
        assertNotNull(cars);
        assertTrue(!cars.isEmpty());
        System.out.println(" Total cars getAllCars() -- " + cars.size());
    }

    /**
     * Test of saveCar method, of class CarServiceImpl.
     */
    @Test
    public void testSearch() {
        ResultDto<Car> dto = carService.search(0, 0, 0, 0, 10);
        assertNotNull(dto.getList());
        assertTrue(dto.getList().size() == 10);
    }

    /**
     * Test of saveCar method, of class CarServiceImpl.
     */
    @Test
    public void testSearchWithCacheOn() {
        System.out.println ( " --------------------------------------------- ");
        ResultDto<Car> dto = carService.search(0, 0, 0, 0, 10);
        assertNotNull(dto.getList());
        assertTrue(dto.getList().size() == 10);
        System.out.println ( " New Query ************** ");
        dto = carService.search(0, 0, 0, 0, 10);
        assertNotNull(dto.getList());
        assertTrue(dto.getList().size() == 10);
        System.out.println ( " --------------------------------------------- ");
    }

    @Test
    public void testSearch1() {
        ResultDto<Car> dto = carService.search(10, 0, 0, 0, 10);
        assertNotNull(dto.getList());
        System.out.println(dto.getList().size());
        //assertTrue(dto.getList().size() == 5);
    }

    @Test
    public void testSearch2() {
        ResultDto<Car> dto = carService.search(0, 10, 0, 0, 10);
        assertNotNull(dto.getList());
        System.out.println(dto.getList().size());
        assertTrue(dto.getList().size() == 10);
    }
}
