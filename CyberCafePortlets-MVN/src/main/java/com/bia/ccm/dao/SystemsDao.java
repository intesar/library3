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

import com.bia.ccm.entity.Systems;
import com.bizintelapps.easydao.dao.GenericDao;
import java.util.List;

/**
 *
 * @author imran
 */
public interface SystemsDao extends GenericDao<Systems, Long> {

    public List<Systems> findByOrganization(long organization);

    public Systems findBySystemNameAndOrganization(int systemNo, long organization);

    Systems findByMacAddress(String macAddress);

    Long findNoOfActiveSystemsByOrganization(long organization);
}
