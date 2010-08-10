/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bia.ccm.dao.impl;

import com.bia.ccm.dao.OrderItemDao;
import com.bia.ccm.entity.OrderItem;
import com.bizintelapps.easydao.dao.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class OrderItemDaoImpl extends GenericDaoImpl<OrderItem, Long> implements OrderItemDao {

    public OrderItemDaoImpl() {
        super(OrderItem.class);
    }
}
