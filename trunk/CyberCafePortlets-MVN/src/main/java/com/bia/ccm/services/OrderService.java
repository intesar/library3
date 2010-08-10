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
import java.util.List;
import java.util.Set;

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
     * @param orderItemId not null
     * @param organization   not null
     * @return
     */
    OrderDetail deleteItem(Long orderDetailId, Long orderItemId, Long organization);

    /**
     *
     * @param orderDetailId not null
     * @param quantity >= 0
     * @param organization   not null
     * @return
     */
    OrderDetail updateOrderItemQuantity(Long orderDetailId, Long orderItemId, int quantity, Long organization);

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

}
