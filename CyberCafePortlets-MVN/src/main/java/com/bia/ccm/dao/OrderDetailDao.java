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
import com.bizintelapps.easydao.dao.PagedResult;
import java.util.Date;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface OrderDetailDao  extends GenericDao<OrderDetail, Long> {

    List<OrderDetail> findByOrderStatus(Long organization, OrderStatus orderStatus);

    PagedResult<OrderDetail> findByCustomerInfo(Long userId, String username, String email, int start, int max);

    PagedResult<OrderDetail> findOrderDetails(Long organization, Date startDate, Date endDate,
            OrderStatus orderStatus, String customer, int start, int max);

    PagedResult<OrderDetail> search(String keyword, OrderStatus orderStatus, Date startDate, Date endDate, Long organization, int start, int max);
}
