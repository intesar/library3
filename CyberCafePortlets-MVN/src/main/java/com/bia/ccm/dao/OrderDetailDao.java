/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */

package com.bia.ccm.dao;

import com.bia.ccm.entity.OrderDetail;
import com.bia.ccm.entity.OrderStatus;
import com.bizintelapps.easydao.dao.GenericDao;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface OrderDetailDao  extends GenericDao<OrderDetail, Long> {

    public List<OrderDetail> findByOrderStatus(Long organization, OrderStatus orderStatus);

}
