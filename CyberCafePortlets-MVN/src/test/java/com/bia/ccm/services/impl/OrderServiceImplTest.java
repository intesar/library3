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
import com.bia.ccm.entity.OrderItem;
import com.bia.ccm.entity.OrderStatus;
import com.bia.ccm.entity.ProductType;
import com.bia.ccm.entity.Services;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.services.OrderService;
import com.bia.ccm.services.ProductService;
import com.bizintelapps.easydao.dao.UserThreadLocal;
import com.bizintelapps.easydao.dao.UserThreadLocalDto;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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

        result = orderService.changeOrderStatus(result.getId(), 0.0, OrderStatus.Live, organization);
        assertNotNull(result);
        Double amt = 0.0;
        assertEquals(result.getAmountDue(), amt);
        assertEquals(result.getPaidAmount(), amt);
        assertEquals(result.getPayableAmount(), amt);
        assertEquals(result.getOrderStatus(), OrderStatus.Live);
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
        result = orderService.changeOrderStatus(result.getId(), paid, OrderStatus.Live, organization);
        assertNotNull(result);
        Double amt = 0.0;
        Double payable = -5.0;
        assertEquals(result.getAmountDue(), amt);
        assertEquals(result.getPaidAmount(), paid);
        assertEquals(result.getPayableAmount(), payable);
        assertEquals(result.getOrderStatus(), OrderStatus.Live);
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
            result = orderService.changeOrderStatus(result.getId(), paid, OrderStatus.Live, organization);
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
            result = orderService.changeOrderStatus(result.getId(), 0.0, OrderStatus.Live, organization + 1);
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
    @Test
    public void testGetOrderByStatus() {
        System.out.println("getOrderByStatus");
        getOrderByStatusNoResults();
        getOrderByStatusValid();
        getOrderByStatusNullStatus();
        getOrderByStatusLargeSet();
    }

    private void getOrderByStatusNoResults() {
        Long organization = 1L;
        List<OrderDetail> list = orderService.getOrderByStatus(organization, OrderStatus.Live);
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

        List<OrderDetail> list = orderService.getOrderByStatus(organization, OrderStatus.Live);
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
        List<OrderDetail> list = orderService.getOrderByStatus(organization, OrderStatus.Live);
        assertTrue(list.size() >= max);
    }
}
