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
import com.bia.ccm.exceptions.InvalidInputException;
import com.bizintelapps.easydao.dao.GenericDaoImpl;
import com.bizintelapps.easydao.dao.PagedResult;
import com.bizintelapps.easydao.dao.PagingParams;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
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
    public PagedResult<OrderDetail> findByCustomerInfo(String user, int start, int max) {
        try {
            FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
            // create native Lucene query
            String[] fields = new String[]{"customerUsername", "customerEmail", "customerUserId"};

            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());

            org.apache.lucene.search.Query query = parser.parse(user);
            // wrap Lucene query in a javax.persistence.Query
            //javax.persistence.Query persistenceQuery
            FullTextQuery persistenceQuery = fullTextEntityManager.createFullTextQuery(query, OrderDetail.class);
            // execute search
            persistenceQuery.setFirstResult(start);
            persistenceQuery.setMaxResults(max);
            Sort sort = new Sort("id", true);
            persistenceQuery.setSort(sort);
            List list = persistenceQuery.getResultList();
            int maxResults = persistenceQuery.getMaxResults();

            PagingParams pagingParams = new PagingParams(start, max);
            PagedResult pagedResult = new PagedResult(maxResults, list, pagingParams);

            return pagedResult;
        } catch (ParseException ex) {
            log.warn(ex.getMessage(), ex);
            throw new InvalidInputException(ex.getMessage());
        }
    }

    @Override
    public PagedResult<OrderDetail> search(String keyword, OrderStatus orderStatus, Date startDate, Date endDate, Long organization, int start, int max) {
        try {
            FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
            // create native Lucene query
            String[] fields = new String[]{"customerName", "customerUsername", "customerEmail", "customerUserId",
                "customerPhone", "orderStatus", "orderItems.productName", "organization"};

            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
            String txt = "";
            if (orderStatus != null) {
                txt = " AND orderStatus:" + orderStatus;
            }
            org.apache.lucene.search.Query query = parser.parse(keyword + " AND organization:" + organization + txt);
            // wrap Lucene query in a javax.persistence.Query
            FullTextQuery persistenceQuery = fullTextEntityManager.createFullTextQuery(query, OrderDetail.class);
            // execute search
            persistenceQuery.setFirstResult(start);
            persistenceQuery.setMaxResults(max);
            Sort sort = new Sort("id", true);
            persistenceQuery.setSort(sort);
            List list = persistenceQuery.getResultList();
            int maxResults = persistenceQuery.getMaxResults();

            PagingParams pagingParams = new PagingParams(start, max);
            PagedResult pagedResult = new PagedResult(maxResults, list, pagingParams);

            return pagedResult;
        } catch (ParseException ex) {
            log.warn(ex.getMessage(), ex);
            throw new InvalidInputException(ex.getMessage());
        }

    }

    @Override
    public PagedResult<OrderDetail> findOrderDetails(Long organization, Date startDate, Date endDate,
            OrderStatus orderStatus, String customer, int start, int max) {
        PagingParams pagingParams = new PagingParams(start, max);
        PagedResult pagedResult = new PagedResult(0, null, pagingParams);
        String ql = "SELECT s FROM OrderDetail s WHERE s.organization = ?1 ";
        boolean isValidDate = false;
        if (startDate != null && endDate != null) {
            ql += "AND s.createDate BETWEEN ?2 AND ?3 ";
            isValidDate = true;
        }
        boolean isValidStatus = false;
        if (orderStatus != null) {
            ql += "AND s.orderStatus LIKE ?4 ";
            isValidStatus = true;
        }
        boolean isValidCustomer = false;
        if (customer != null && customer.trim().length() > 0) {
            ql += "AND (s.customerUserId = ?5 OR s.customerUsername LIKE ?6 OR s.customerEmail LIKE ?7 "
                    + "OR s.customerName LIKE ?8 OR s.customerPhone LIKE ?9) ";
            isValidCustomer = true;
        }
        ql += "ORDER BY s.createDate";
        Query query = em.createQuery(ql).setFirstResult(start).setMaxResults(max);
        query.setParameter(1, organization);
        if (isValidDate) {
            query.setParameter(2, startDate);
            query.setParameter(3, endDate);
        }
        if (isValidStatus) {
            query.setParameter(4, orderStatus);
        }
        if (isValidCustomer) {
            Long userId = 0L;
            try {
                userId = Long.parseLong(customer);
            } catch (Exception ex) {
            }
            query.setParameter(5, userId);
            query.setParameter(6, customer);
            query.setParameter(7, customer);
            query.setParameter(8, customer);
            query.setParameter(9, customer);
        }
        List<OrderDetail> list = query.getResultList();
        pagedResult.setResults(list);
        return pagedResult;
    }
    protected static final Log logger = LogFactory.getLog(OrderDetailDaoImpl.class);
}
