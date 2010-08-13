/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.entity.OrderDetail;
import com.bia.ccm.entity.OrderStatus;
import com.bia.ccm.entity.ProductType;
import com.bia.ccm.entity.Services;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.services.OrderService;
import com.bia.ccm.services.ProductService;
import com.bizintelapps.easydao.dao.PagedResult;
import com.bizintelapps.easydao.dao.UserThreadLocal;
import com.bizintelapps.easydao.dao.UserThreadLocalDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

/**
 *
 * @author intesar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-Annotations.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class OrderServiceImplTest {

    @Autowired
    protected OrderService orderService;
    @Autowired
    protected ProductService productService;

    public OrderServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        UserThreadLocal.set(new UserThreadLocalDto(1000L, "test IP"));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createOrder method, of class OrderServiceImpl.
     */
    //@Test
    public void testCreateOrder() {
        System.out.println("createOrder");
        orderWithEmptyCustomerDetails();
        orderWithNullCustomerDetails();
        orderWithValidCustomerDetails();
        orderWithEmptyAndValidCustomerDetails();
    }

    private void orderWithEmptyCustomerDetails() {
        String customerName = "";
        String customerUsername = "";
        String customerEmail = "";
        String customerPhone = "";
        Long customerUserId = null;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
    }

    private void orderWithNullCustomerDetails() {
        String customerName = null;
        String customerUsername = null;
        String customerEmail = null;
        String customerPhone = null;
        Long customerUserId = null;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
    }

    private void orderWithValidCustomerDetails() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
    }

    private void orderWithEmptyAndValidCustomerDetails() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
    }

    /**
     * Test of updateOrderCustomerDetail method, of class OrderServiceImpl.
     */
    //@Test
    public void testUpdateOrderCustomerDetail() {
        System.out.println("updateOrderCustomerDetail");
        updateOrderWithEmptyCustomerDetails();
        updateOrderWithNullCustomerDetails();
        updateOrderWithValidCustomerDetails();
        updateOrderWithEmptyAndValidCustomerDetails();

    }

    private void updateOrderWithEmptyCustomerDetails() {
        String customerName = "";
        String customerUsername = "";
        String customerEmail = "";
        String customerPhone = "";
        Long customerUserId = null;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
        customerName = "test";
        customerUsername = "test";
        customerEmail = "test@test.com";
        customerPhone = "test";
        result = orderService.updateOrderCustomerDetail(result.getId(), customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
    }

    private void updateOrderWithNullCustomerDetails() {
        String customerName = null;
        String customerUsername = null;
        String customerEmail = null;
        String customerPhone = null;
        Long customerUserId = null;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
        customerName = "test";
        customerUsername = null;
        customerEmail = "test@test.com";
        customerPhone = "test";
        result = orderService.updateOrderCustomerDetail(result.getId(), customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
    }

    private void updateOrderWithValidCustomerDetails() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
        customerName = "test1";
        customerUsername = "test1";
        customerEmail = "test1@test.com";
        customerPhone = "test1";
        result = orderService.updateOrderCustomerDetail(result.getId(), customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
    }

    private void updateOrderWithEmptyAndValidCustomerDetails() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
        customerName = "";
        customerUsername = "test1";
        customerEmail = "test1@test.com";
        customerPhone = "test1";
        result = orderService.updateOrderCustomerDetail(result.getId(), customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
    }

    /**
     * Test of adddItem method, of class OrderServiceImpl.
     */
    //@Test
    public void testAdddItem() {
        System.out.println("adddItem");
        Long organization = 1L;
        OrderDetail orderDetail = getOrder();
        Long orderDetailId = orderDetail.getId();
        Long productId = getProductId();
        int quantity = 1;
        ProductType productType = ProductType.OTHER;
        addValidItem(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addInValidProduct(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addInValidProduct1(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addInValidOrder(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addInValidOrder1(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addInValidQuantity(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addInValidOrganization(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addInValidOrganization1(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addInValidProductType(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addValidItem(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addItemQuantityAsZero(orderDetailId, productId, quantity, organization, productType, orderDetail);
        addItemQuantity1(orderDetailId, productId, quantity, organization, productType, orderDetail);
    }

    private void addValidItem(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {
        orderDetail = orderService.adddItem(orderDetailId, productId, quantity, organization, productType);
        assertEquals(1, orderDetail.getOrderItems().size());
    }

    private void addInValidProduct(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {
        try {
            orderDetail = orderService.adddItem(orderDetailId, productId + 1, quantity, organization, productType);
            fail(" invalid productId");
        } catch (NullPointerException ex) {
        }
    }

    private void addInValidProduct1(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {
        try {
            orderDetail = orderService.adddItem(orderDetailId, null, quantity, organization, productType);
            fail(" invalid productId");
        } catch (InvalidInputException ex) {
        }
    }

    private void addInValidOrder(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {
        try {
            orderDetail = orderService.adddItem(orderDetailId + 1, productId + 1, quantity, organization, productType);
            fail(" invalid orderId");
        } catch (NullPointerException ex) {
        }
    }

    private void addInValidOrder1(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {
        try {
            orderDetail = orderService.adddItem(null, productId + 1, quantity, organization, productType);
            fail(" invalid orderId");
        } catch (InvalidInputException ex) {
        }
    }

    private void addInValidQuantity(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {

        orderDetail = orderService.adddItem(orderDetailId, productId, -1, organization, productType);
        assertEquals(0, orderDetail.getOrderItems().size());
    }

    private void addInValidOrganization(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {
        try {
            orderDetail = orderService.adddItem(orderDetailId, productId, quantity, organization + 1, productType);
            fail(" invalid organization");
        } catch (InvalidInputException ex) {
        }
    }

    private void addInValidOrganization1(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {
        try {
            orderDetail = orderService.adddItem(orderDetailId + 1, productId + 1, quantity, null, productType);
            fail(" invalid organization");
        } catch (NullPointerException ex) {
        }
    }

    private void addInValidProductType(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {
        try {
            orderDetail = orderService.adddItem(orderDetailId + 1, productId + 1, quantity, organization, null);
            fail(" invalid product type");
        } catch (InvalidInputException ex) {
        }
    }

    private void addItemQuantityAsZero(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {

        orderDetail = orderService.adddItem(orderDetailId, productId, 0, organization, productType);
        assertEquals(0, orderDetail.getOrderItems().size());

    }

    private void addItemQuantity1(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType, OrderDetail orderDetail) {

        orderDetail = orderService.adddItem(orderDetailId, productId, 10, organization, productType);
        assertTrue(10 <= orderDetail.getOrderItems().get(0).getQuantity());

    }

    private OrderDetail getOrder() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        return result;
    }

    private Long getProductId() {
        Long organization = 1L;
        Services service = new Services(null, "Temp", 5.0, organization);
        productService.saveService(service, organization);
        List<Services> list = productService.getAllServices(organization);
        return list.get(0).getId();
    }

    /**
     * Test of deleteItem method, of class OrderServiceImpl.
     */
    /**
     * Test of changeOrderStatus method, of class OrderServiceImpl.
     */
    //@Test
    public void testChangeOrderStatus() {
        System.out.println("changeOrderStatus");
        changeOrderStatus1();
        changeOrderStatus2();
        changeOrderStatus3();
        changeOrderStatus4();
        changeOrderStatus5();
    }

    private void changeOrderStatus1() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);

        result = orderService.changeOrderStatus(result.getId(), 0.0, OrderStatus.LIVE, organization);
        assertNotNull(result);
        Double amt = 0.0;
        assertEquals(result.getAmountDue(), amt);
        assertEquals(result.getPaidAmount(), amt);
        assertEquals(result.getPayableAmount(), amt);
        assertEquals(result.getOrderStatus(), OrderStatus.LIVE);
    }

    private void changeOrderStatus2() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
        Double paid = 5.0;
        result = orderService.changeOrderStatus(result.getId(), paid, OrderStatus.LIVE, organization);
        assertNotNull(result);
        Double amt = 0.0;
        Double payable = -5.0;
        assertEquals(result.getAmountDue(), amt);
        assertEquals(result.getPaidAmount(), paid);
        assertEquals(result.getPayableAmount(), payable);
        assertEquals(result.getOrderStatus(), OrderStatus.LIVE);
    }

    private void changeOrderStatus3() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
        Double paid = -1.0;
        try {
            result = orderService.changeOrderStatus(result.getId(), paid, OrderStatus.LIVE, organization);
            fail(" negative payment");
        } catch (InvalidInputException ex) {
        }
    }

    private void changeOrderStatus4() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
        try {
            result = orderService.changeOrderStatus(result.getId(), 0.0, OrderStatus.LIVE, organization + 1);
            fail(" invalid org");
        } catch (InvalidInputException ex) {
        }
    }

    private void changeOrderStatus5() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        OrderDetail result = orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        assertNotNull(result);
        assertEquals(result.getCustomerName(), customerName);
        assertEquals(result.getCustomerUsername(), customerUsername);
        assertEquals(result.getCustomerEmail(), customerEmail);
        assertEquals(result.getCustomerPhone(), customerPhone);
        assertEquals(result.getCustomerUserId(), customerUserId);
        try {
            result = orderService.changeOrderStatus(result.getId(), 0.0, null, organization);
            fail(" invalid order status");
        } catch (InvalidInputException ex) {
        }
    }

    /**
     * Test of getOrderByStatus method, of class OrderServiceImpl.
     */
    //@Test
    public void testGetOrderByStatus() {
        System.out.println("getOrderByStatus");
        getOrderByStatusNoResults();
        getOrderByStatusValid();
        getOrderByStatusNullStatus();
        getOrderByStatusLargeSet();
    }

    private void getOrderByStatusNoResults() {
        Long organization = 1L;
        List<OrderDetail> list = orderService.getOrderByStatus(organization, OrderStatus.LIVE);
        assertEquals(list.size(), 0);
    }

    private void getOrderByStatusValid() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);

        List<OrderDetail> list = orderService.getOrderByStatus(organization, OrderStatus.LIVE);
        assertEquals(list.size(), 1);
    }

    private void getOrderByStatusNullStatus() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        try {
            List<OrderDetail> list = orderService.getOrderByStatus(organization, null);
            fail(" invalid order status");
        } catch (InvalidInputException ex) {
        }
    }

    private void getOrderByStatusLargeSet() {
        String customerName = "test";
        String customerUsername = "test";
        String customerEmail = "test@7cybercafe.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        int max = 100;
        for (int x = 0; x < max; x++) {
            orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        }
        List<OrderDetail> list = orderService.getOrderByStatus(organization, OrderStatus.LIVE);
        assertTrue(list.size() >= max);
    }

    /**
     * Test of getOrderByStatus method, of class OrderServiceImpl.
     */
    //@Test
    public void testGetOrderByUserInfo() {
        System.out.println("getOrderByUserInfo");
        testFindNoOrders();
        createOrders();
        testFindOrdersByUserId();
        testFindOrdersByUserId1();
        testFindOrdersByUsername();
        testFindOrdersByUsername1();
        testFindOrdersByEmail();
        testFindOrdersByEmail1();
    }

    private void createOrders() {
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        for (int x = 0; x < 100; x++) {
            orderService.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
        }
    }

    private void testFindNoOrders() {
        Long customerUserId = 1000L;
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.getOrderByUserInfo(customerUserId, customerUsername, customerEmail, start, max);
        assertTrue(result.getResults().size() == 0);
    }

    private void testFindOrdersByUserId() {
        Long customerUserId = 1000L;
        String customerUsername = "";
        String customerEmail = "";
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.getOrderByUserInfo(customerUserId, customerUsername, customerEmail, start, max);
        System.out.println("size : " + result.getResults().size());
        assertTrue(result.getResults().size() == 20);
    }

    private void testFindOrdersByUserId1() {
        Long customerUserId = 1001L;
        String customerUsername = "";
        String customerEmail = "";
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.getOrderByUserInfo(customerUserId, customerUsername, customerEmail, start, max);
        System.out.println("size : " + result.getResults().size());
        assertTrue(result.getResults().size() == 0);
    }

    private void testFindOrdersByUsername() {
        Long customerUserId = 1000L;
        String customerUsername = "inmohamm";
        String customerEmail = "";
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.getOrderByUserInfo(customerUserId, customerUsername, customerEmail, start, max);
        System.out.println("size : " + result.getResults().size());
        assertTrue(result.getResults().size() == 20);
    }

    private void testFindOrdersByUsername1() {
        Long customerUserId = 1001L;
        String customerUsername = "inmohamm1";
        String customerEmail = "";
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.getOrderByUserInfo(customerUserId, customerUsername, customerEmail, start, max);
        System.out.println("size : " + result.getResults().size());
        assertTrue(result.getResults().size() == 0);
    }

    private void testFindOrdersByEmail() {
        Long customerUserId = 0L;
        String customerUsername = "";
        String customerEmail = "mdshannan@gmail.com";
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.getOrderByUserInfo(customerUserId, customerUsername, customerEmail, start, max);
        System.out.println("size : " + result.getResults().size());
        assertTrue(result.getResults().size() == 20);
    }

    private void testFindOrdersByEmail1() {
        Long customerUserId = 1001L;
        String customerUsername = "inmohamm1";
        String customerEmail = "mdshannan@gmail.com1";
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.getOrderByUserInfo(customerUserId, customerUsername, customerEmail, start, max);
        System.out.println("size : " + result.getResults().size());
        assertTrue(result.getResults().size() == 0);
    }


    @Test
    public void testSearchOrderByOrganization() {
        System.out.println("SearchOrderByOrganization");
//        testFindNoOrdersSearch();
//        createOrders();
//        testFindOrdersByUserIdX();
//        testFindOrdersByUserId1X();
//        testFindOrdersByUsernameX();
//        testFindOrdersByUsername1X();
//        testFindOrdersByEmailX();
//        testFindOrdersByEmail1X();
          applicationContext();
    }

    private void applicationContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{
                    "classpath:applicationContext-Annotations.xml"
                });

        // an ApplicationContext is also a BeanFactory (via inheritance)
        BeanFactory factory = (BeanFactory) context;
        OrderService os = (OrderService) factory.getBean("orderServiceImpl");
        String customerName = "intesar shannan";
        String customerUsername = "inmohamm rock star";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long customerUserId = 1000L;
        Long organization = 1L;
        List<OrderDetail> ods = new ArrayList<OrderDetail>();
        int start = 0;
        int max = 20;
        for (int x = 0; x < max; x++) {
            OrderDetail od = os.createOrder(customerName, customerUsername, customerEmail, customerPhone, customerUserId, organization);
            ods.add(od);
        }
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os1 = OrderStatus.LIVE;
        String customer = customerUsername;
        
        PagedResult<OrderDetail> result = os.searchOrderByOrganization(organization, sd, ed, os1, customer, start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);

        result = os.searchOrderByOrganization(organization, sd, ed, os1, "intesar", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);


        result = os.searchOrderByOrganization(organization, sd, ed, os1, "Intesar", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);

        result = os.searchOrderByOrganization(organization, sd, ed, os1, "INTESAR", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);

        result = os.searchOrderByOrganization(organization, sd, ed, os1, "inmohamm", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);

        result = os.searchOrderByOrganization(organization, sd, ed, os1, "mdshannan@gmail.com", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);

        result = os.searchOrderByOrganization(organization, sd, ed, os1, "773-216-5478", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);

        result = os.searchOrderByOrganization(organization, sd, ed, os1, "rock inmohamm", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);

        result = os.searchOrderByOrganization(organization, sd, ed, os1, "rock", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);

        result = os.searchOrderByOrganization(organization, sd, ed, os1, "star", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() >= 20);

        result = os.searchOrderByOrganization(organization+1, sd, ed, os1, "star", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 0);

        result = os.searchOrderByOrganization(organization+1, sd, ed, null, "star", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 0);

        result = os.searchOrderByOrganization(organization, null, null, null, "stars", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 0);

        result = os.searchOrderByOrganization(organization, sd, ed, OrderStatus.COMPLETE, "star", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 0);

        result = os.searchOrderByOrganization(organization, sd, ed, null, "sta*", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 20);

        result = os.searchOrderByOrganization(organization, sd, ed, null, "shannan*", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 20);

        result = os.searchOrderByOrganization(organization, sd, ed, null, "shannan", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 20);

        result = os.getOrderByUserInfo(0L, "inmohamm", "", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 20);

        result = os.getOrderByUserInfo(0L, "", "mdshannan@gmail.com", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 20);

        result = os.getOrderByUserInfo(1000L, "", "", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 20);

        result = os.getOrderByUserInfo(0L, "", "", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 0);

        result = os.getOrderByUserInfo(0L, "inmohamm", "", start, max);
        System.out.println ( " search result size : " +result.getResults().size() );
        assertTrue(result.getResults().size() == 20);

        for ( OrderDetail o : result.getResults() ) {
            System.out.println(o.getId());
        }

        for ( OrderDetail o : ods ) {
            os.deleteOrder(o.getId(), organization);
        }
    }

    private void testFindNoOrdersSearch() {
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long organization = 1L;
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os = OrderStatus.LIVE;
        String customer = customerUsername;
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.searchOrderByOrganization(organization, sd, ed, os, customer, start, max);
        assertTrue(result.getResults().size() == 0);
    }

    private void testFindOrdersByUserIdX() {
        Long customerUserId = 1000L;
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long organization = 1L;
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os = OrderStatus.LIVE;
        String customer = customerName;
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.searchOrderByOrganization(organization, sd, ed, os, customer, start, max);
        System.out.println (" size : " + result.getResults().size());
        assertTrue(result.getResults().size() >= 20);
    }

    private void testFindOrdersByUserId1X() {
        Long customerUserId = 1000L;
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long organization = 1L;
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os = OrderStatus.LIVE;
        String customer = customerName;
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.searchOrderByOrganization(organization, sd, ed, os, customer, start, max);
        assertTrue(result.getResults().size() == 20);
    }

    private void testFindOrdersByUsernameX() {
        Long customerUserId = 1000L;
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long organization = 1L;
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os = OrderStatus.LIVE;
        String customer = customerUsername;
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.searchOrderByOrganization(organization, sd, ed, os, customer, start, max);
        assertTrue(result.getResults().size() == 20);
    }

    private void testFindOrdersByUsername1X() {
        Long customerUserId = 1000L;
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long organization = 1L;
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os = OrderStatus.LIVE;
        String customer = customerUsername;
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.searchOrderByOrganization(organization, sd, ed, os, customer, start, max);
        assertTrue(result.getResults().size() == 20);
    }

    private void testFindOrdersByEmailX() {
        Long customerUserId = 1000L;
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long organization = 1L;
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os = OrderStatus.LIVE;
        String customer = customerEmail;
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.searchOrderByOrganization(organization, sd, ed, os, customer, start, max);
        assertTrue(result.getResults().size() == 20);
    }

    private void testFindOrdersByEmail1X() {
        Long customerUserId = 1000L;
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long organization = 1L;
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os = OrderStatus.LIVE;
        String customer = customerEmail;
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.searchOrderByOrganization(organization, sd, ed, os, customer, start, max);
        assertTrue(result.getResults().size() == 20);
    }

    private void testFindOrdersByNameX() {
        Long customerUserId = 1000L;
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long organization = 1L;
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os = OrderStatus.LIVE;
        String customer = customerName;
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.searchOrderByOrganization(organization, sd, ed, os, customer, start, max);
        assertTrue(result.getResults().size() == 20);
    }

    private void testFindOrdersByPhoneX() {
        Long customerUserId = 1000L;
        String customerName = "intesar mohammed";
        String customerUsername = "inmohamm";
        String customerEmail = "mdshannan@gmail.com";
        String customerPhone = "773-216-5478";
        Long organization = 1L;
        Date sd = new Date();
        Date ed = new Date();
        OrderStatus os = OrderStatus.LIVE;
        String customer = customerPhone;
        int start = 0;
        int max = 20;
        PagedResult<OrderDetail> result = orderService.searchOrderByOrganization(organization, sd, ed, os, customer, start, max);
        assertTrue(result.getResults().size() == 20);
    }

}
