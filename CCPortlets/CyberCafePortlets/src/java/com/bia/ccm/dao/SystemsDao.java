/*
 * 
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.Systems;
import java.util.List;

/**
 *
 * @author imran
 */
public interface SystemsDao extends GenericDao<Systems, Integer> {

    public List<Systems> findByOrganization(long organization);

    public Systems findBySystemNameAndOrganization(int systemNo, long organization);

    Systems findByMacAddress(String macAddress);

    Long findNoOfActiveSystemsByOrganization(long organization);
}
