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

import com.bia.ccm.entity.SystemLease;
import com.bizintelapps.easydao.dao.GenericDao;
import com.bizintelapps.easydao.dao.PagingParams;
import java.util.Date;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface SystemLeaseDao extends GenericDao<SystemLease, Long> {

    public List<SystemLease> findByIsEndContractNotified(Boolean b, PagingParams pp);

    public List<SystemLease> findByIsStartContractNotified(Boolean b, PagingParams pagingParamses);

    public List<SystemLease> findByOrganization(long organization);

    public List<SystemLease> findBySystemAndFinished(long id);

    public List<SystemLease> findBySystemIdAndFinished(long id);

    public List<SystemLease> findByStartAndEndDates(Date sd, Date ed, long org);

    public List<SystemLease> findByUsernameAndStartEndDates(String username, Date startDate, Date endDate);

    public List findReportBetweenDates(Date sd, Date ed, long org);
}
