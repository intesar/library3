/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bia.ccm.services;

import com.bia.ccm.entity.OrderDetail;
import com.bia.ccm.entity.OrderStatus;
import com.bia.ccm.entity.ProductType;
import com.bizintelapps.easydao.dao.PagedResult;
import java.util.Date;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface OrderService {

    /**
     *
     * @param customerName
     * @param customerUsername
     * @param customerEmail
     * @param customerPhone
     * @param customerUserId
     * @param organization   not null
     * @return
     */
    OrderDetail createOrder(String customerName,
            String customerUsername, String customerEmail, String customerPhone, 
            Long customerUserId, Long organization);

    /**
     *  use this function for
     *   1. adding new item
     *   2. deleting existing item by providing quantity as zero
     *   3. update item quantity 
     *
     *
     * @param orderDetailId not null
     * @param productId not null
     * @param quantity >= 0
     * @param organization   not null
     * @return
     */
    OrderDetail adddItem(Long orderDetailId, Long productId, int quantity, Long organization, ProductType productType);

    
    /**
     *
     * @param orderDetailId not null
     * @param customerName
     * @param customerUsername
     * @param customerEmail
     * @param customerPhone
     * @param customerUserId
     * @param organization   not null
     * @return
     */
    OrderDetail updateOrderCustomerDetail(Long orderDetailId, String customerName,
            String customerUsername, String customerEmail, String customerPhone, 
            Long customerUserId, Long organization);

    /**
     *
     * @param orderDetailId not null
     * @param amountPaid >= 0
     * @param orderStatus not null
     * @param organization   not null
     * @return
     */
    OrderDetail changeOrderStatus(Long orderDetailId, Double amountPaid, 
            OrderStatus orderStatus, Long organization);

    /**
     * 
     * @param organization
     * @param orderStatus
     * @return
     */
    List<OrderDetail> getOrderByStatus(Long organization, OrderStatus orderStatus);

    /**
     *
     * @param userId
     * @param start
     * @param max
     * @return
     */
    PagedResult<OrderDetail> getOrderByUserId(Long userId, String username, String email, int start, int max);

    /**
     *
     * @param organization cannot be null
     * @param date null remove this from filter criteria
     * @param orderStatus  null remove this from filter criteria
     * @param customer could be customer --> userId, username, fullname, email
     * @param start cannot be less then zero
     * @param max  should be between 1 and 50
     * @return
     */
    PagedResult<OrderDetail> searchOrderByOrganization(Long organization, Date startDate, Date endDate,
            OrderStatus orderStatus, String customer, int start, int max);


}
