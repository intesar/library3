/*
 * 
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.abbhsoft.jpadaoframework.dao.PagingParams;
import com.bia.ccm.entity.SystemLease;
import java.util.Date;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface SystemLeaseDao extends GenericDao<SystemLease, Integer> {

    public List<SystemLease> findByIsEndContractNotified(Boolean b, PagingParams... pp);

    public List<SystemLease> findByIsStartContractNotified(Boolean b, PagingParams... pagingParamses);

    public List<SystemLease> findByOrganization(long organization);

    public List<SystemLease> findBySystemAndFinished(int id);

    public List<SystemLease> findBySystemIdAndFinished(int id);

    public List<SystemLease> findByStartAndEndDates(Date sd, Date ed, long org);

    public List<SystemLease> findByUsernameAndStartEndDates(String username, Date startDate, Date endDate);

    public List findReportBetweenDates(Date sd, Date ed, long org);
}
