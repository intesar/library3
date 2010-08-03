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

import com.bia.ccm.dao.SystemLeaseDao;
import com.bia.ccm.entity.SystemLease;
import com.bizintelapps.easydao.dao.GenericDaoImpl;
import com.bizintelapps.easydao.dao.PagingParams;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class SystemLeaseDaoImpl extends GenericDaoImpl<SystemLease, Long> implements SystemLeaseDao {

    public SystemLeaseDaoImpl() {
        super(SystemLease.class);
    }

    @Override
    public List<SystemLease> findByIsEndContractNotified(Boolean b, PagingParams pp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SystemLease> findByIsStartContractNotified(Boolean b, PagingParams pagingParamses) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SystemLease> findByOrganization(long organization) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SystemLease> findBySystemAndFinished(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SystemLease> findBySystemIdAndFinished(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SystemLease> findByStartAndEndDates(Date sd, Date ed, long org) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SystemLease> findByUsernameAndStartEndDates(String username, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List findReportBetweenDates(Date sd, Date ed, long org) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
