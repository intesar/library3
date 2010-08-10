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

import com.bia.ccm.dao.OrderDetailDao;
import com.bia.ccm.entity.OrderDetail;
import com.bia.ccm.entity.OrderStatus;
import com.bizintelapps.easydao.dao.GenericDaoImpl;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class OrderDetailDaoImpl extends GenericDaoImpl<OrderDetail, Long> implements OrderDetailDao  {

    public OrderDetailDaoImpl() {
        super(OrderDetail.class);
    }

    @Override
    public List<OrderDetail> findByOrderStatus(Long organization, OrderStatus orderStatus) {
        return executeNamedQueryReturnList("OrderDetail.findByOrderStatus", organization, orderStatus);
    }
}
