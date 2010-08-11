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
import com.bizintelapps.easydao.dao.PagedResult;
import com.bizintelapps.easydao.dao.PagingParams;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class OrderDetailDaoImpl extends GenericDaoImpl<OrderDetail, Long> implements OrderDetailDao {

    public OrderDetailDaoImpl() {
        super(OrderDetail.class);
    }

    @Override
    public List<OrderDetail> findByOrderStatus(Long organization, OrderStatus orderStatus) {
        return executeNamedQueryReturnList("OrderDetail.findByOrderStatus", organization, orderStatus);
    }

    @Override
    public PagedResult<OrderDetail> findByCustomerInfo(Long userId, String username, String email, int start, int max) {
        PagingParams pagingParams = new PagingParams(start, max);
        PagedResult pagedResult = new PagedResult(0, null, pagingParams);
        return executeNamedQueryReturnList("OrderDetail.findByCustomerInfo", pagedResult, username, email, userId);
    }

    @Override
    public PagedResult<OrderDetail> findOrderDetails(Long organization, Date startDate, Date endDate,
            OrderStatus orderStatus, String customer, int start, int max) {
        PagingParams pagingParams = new PagingParams(start, max);
        PagedResult pagedResult = new PagedResult(0, null, pagingParams);
        String ql = "SELECT s FROM OrderDetail s WHERE s.organization = ?1 ";
        boolean isValidDate = false;
        if ( startDate != null && endDate != null) {
            ql += "AND s.createDate BETWEEN ?2 AND ?3 ";
            isValidDate = true;
        }
        boolean isValidStatus = false;
        if ( orderStatus != null ) {
            ql += "AND s.orderStatus LIKE ?4 ";
            isValidStatus = true;
        }
        boolean isValidCustomer = false;
        if ( customer != null && customer.trim().length() > 0 ) {
            ql += "AND (s.customerUserId LIKE ?5 OR s.customerUsername LIKE ?6 OR s.customerEmail LIKE ?7 ) ";
            isValidCustomer = true;
        }
        ql += "ORDER BY s.createDate";
        Query query = em.createQuery(ql).setFirstResult(start).setMaxResults(max);
        query.setParameter(1, organization);
        if ( isValidDate) {
            query.setParameter(2, startDate);
            query.setParameter(3, endDate);
        }
        if ( isValidStatus) {
            query.setParameter(2, orderStatus);
        }
        if ( isValidCustomer) {
            query.setParameter(5, customer);
            query.setParameter(6, customer);
            query.setParameter(7, customer);
        }
        List<OrderDetail> list = query.getResultList();
        pagedResult.setResults(list);
        return pagedResult;
    }
}
