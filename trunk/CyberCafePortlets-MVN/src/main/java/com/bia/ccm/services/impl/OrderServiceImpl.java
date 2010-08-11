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

import com.bia.ccm.dao.OrderDetailDao;
import com.bia.ccm.dao.OrderItemDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.entity.OrderDetail;
import com.bia.ccm.entity.OrderItem;
import com.bia.ccm.entity.OrderStatus;
import com.bia.ccm.entity.ProductType;
import com.bia.ccm.entity.Services;
import com.bia.ccm.services.OrderService;
import com.bia.ccm.exceptions.InvalidInputException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author intesar
 */
@Service(value = "orderServiceImpl")
public class OrderServiceImpl implements OrderService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDetail createOrder(String customerName, String customerUsername,
            String customerEmail, String customerPhone, Long customerUserId, Long organization) {
        OrderDetail orderDetail = new OrderDetail();
        updateCustomerDetails(orderDetail, customerName, customerUsername, customerUserId, customerEmail, customerPhone);
        orderDetail.setOrganization(organization);
        return this.orderDetailDao.persist(orderDetail);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDetail adddItem(Long orderDetailId, Long productId, int quantity,
            Long organization, ProductType productType) {
        /* not null orderDetailId, productId, productType */
        validateForNull(orderDetailId, "Order ID");
        validateForNull(productId, "Product ID");
        validateForNull(productType, "Product Type");
        OrderDetail orderDetail = this.orderDetailDao.find(orderDetailId);
        /* organization validation */
        validateOrganization(orderDetail.getOrganization(), organization);
        /* add Item or update if exists */
        Set<OrderItem> orderItems = orderDetail.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProductId().equals(productId)) {
                /* if quantity is zero delete this item */
                if ( quantity <= 0 ) {
                   orderItems.remove(orderItem);
                } else {
                    orderItem.setQuantity(orderItem.getQuantity() + quantity);
                }
                orderDetail = this.orderDetailDao.merge(orderDetail);
                return orderDetail;
            }
        }
        
        String productName = null;
        if (productType.equals(ProductType.OTHER) && quantity <= 0) {
            throw new InvalidInputException(" Quantity cannot be Zero");
        } else if (productType.equals(ProductType.OTHER)) {
            Services service = servicesDao.find(productId);
            productName = service.getName();
        } else {
            // TODO handle Computer lease
            productName = ProductType.Computer.toString();
        }

        Double amount = 0.0; //
        OrderItem orderItem = new OrderItem(orderDetail, productId, productName, productType, quantity, amount);
        orderDetail.getOrderItems().add(orderItem);
        /* calculate amount due */
        updateAmountDueAndPayable(orderDetail);
        orderDetail = this.orderDetailDao.merge(orderDetail);
        return orderDetail;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDetail deleteItem(Long orderDetailId, Long orderItemId, Long organization) {
        validateForNull(orderDetailId, "Order ID");
        validateForNull(orderItemId, "Product ID");
        OrderDetail orderDetail = this.orderDetailDao.find(orderDetailId);
        validateForNull(orderDetail, "Order ID");
        /* organization validation */
        validateOrganization(orderDetail.getOrganization(), organization);
        Set<OrderItem> orderItems = orderDetail.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId().equals(orderItemId)) {
                orderItems.remove(orderItem);
                /* calculate amount due */
                updateAmountDueAndPayable(orderDetail);
                orderDetail = this.orderDetailDao.merge(orderDetail);
                return orderDetail;
            }
        }
        throw new InvalidInputException(" invalid item to delete ");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDetail updateOrderItemQuantity(Long orderDetailId, Long orderItemId,
            int quantity, Long organization) {
        /* quantity validation */
        validateQuantity(quantity);
        OrderDetail orderDetail = this.orderDetailDao.find(orderDetailId);
        /* organization validation */
        validateOrganization(orderDetail.getOrganization(), organization);
        Set<OrderItem> orderItems = orderDetail.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId().equals(orderItemId)) {
                orderItem.setQuantity(quantity);
                /* calculate amount due */
                updateAmountDueAndPayable(orderDetail);
                orderDetail = this.orderDetailDao.merge(orderDetail);
                return orderDetail;
            }
        }
        throw new InvalidInputException(" invalid item to delete ");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDetail updateOrderCustomerDetail(Long orderDetailId, String customerName,
            String customerUsername, String customerEmail, String customerPhone,
            Long customerUserId, Long organization) {
        OrderDetail orderDetail = this.orderDetailDao.find(orderDetailId);
        /* organization validation */
        validateOrganization(orderDetail.getOrganization(), organization);
        updateCustomerDetails(orderDetail, customerName, customerUsername, customerUserId, customerEmail, customerPhone);
        orderDetail = this.orderDetailDao.merge(orderDetail);
        return orderDetail;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDetail changeOrderStatus(Long orderDetailId, Double amountPaid,
            OrderStatus orderStatus, Long organization) {
        /* validate orderStatus */
        validateOrderStatus(orderStatus);
        /* validate amount */
        validateAmount(amountPaid);
        OrderDetail orderDetail = this.orderDetailDao.find(orderDetailId);
        /* organization validation */
        validateOrganization(orderDetail.getOrganization(), organization);
        orderDetail.setPaidAmount(orderDetail.getPaidAmount() + amountPaid);
        orderDetail.setPayableAmount(orderDetail.getPayableAmount() - amountPaid);
        orderDetail.setOrderStatus(orderStatus);
        return orderDetail;
    }

    @Override
    public List<OrderDetail> getOrderByStatus(Long organization, OrderStatus orderStatus) {
        /* validate orderStatus */
        validateOrderStatus(orderStatus);
        return this.orderDetailDao.findByOrderStatus(organization, orderStatus);
    }

    /* private methods */
    private void updateAmountDueAndPayable(OrderDetail orderDetail) {
        Double amountDue = 0.0;
        Set<OrderItem> orderItems = orderDetail.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProductType().equals(ProductType.OTHER)) {
                Services service = servicesDao.find(orderItem.getProductId());
                Double unitPrice = 0.0;
                /* is saleTwo application ? */
                if (service.isSaleTwoEnabled() && service.getSaleTwoUnits() <= orderItem.getQuantity()) {
                    unitPrice = service.getSaleTwoPrice();
                } else {
                    unitPrice = service.getUnitPrice();
                }
                double amount = unitPrice * orderItem.getQuantity();
                amountDue += amount;
                orderItem.setAmount(amount);
            } else {
                /* also handle case of computer */
            }
        }
        orderDetail.setAmountDue(amountDue);
        orderDetail.setPayableAmount(amountDue - orderDetail.getPaidAmount());
    }

    private void updateCustomerDetails(OrderDetail orderDetail, String customerName, String customerUsername, Long customerUserId, String customerEmail, String customerPhone) {
        orderDetail.setCustomerName(customerName);
        orderDetail.setCustomerUsername(customerUsername);
        orderDetail.setCustomerUserId(customerUserId);
        orderDetail.setCustomerEmail(customerEmail);
        orderDetail.setCustomerPhone(customerPhone);
    }

    private void validateAmount(Double amountPaid) {
        if (amountPaid < 0) {
            throw new InvalidInputException("amountPaid cannot be less than zero");
        }
    }

    private void validateOrganization(Long id, Long organization) {
        if (!id.equals(organization)) {
            throw new InvalidInputException(" incorrect organization ");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new InvalidInputException("Quantity cannot be less than zero");
        }
    }

    private void validateOrderStatus(OrderStatus orderStatus) {
        if (orderStatus == null) {
            throw new InvalidInputException(" Order Status cannot be null");
        }
    }

    private void validateForNull(Object object, String msg) {
        if (object == null) {
            throw new InvalidInputException(msg + " cannot be null");
        }
    }
    /* daos */
    @Autowired
    protected ServicesDao servicesDao;
    @Autowired
    protected OrderItemDao orderItemDao;
    @Autowired
    protected OrderDetailDao orderDetailDao;
}