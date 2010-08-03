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

import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.entity.Systems;
import com.bizintelapps.easydao.dao.GenericDaoImpl;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class SystemsDaoImpl extends GenericDaoImpl<Systems, Long> implements SystemsDao {

    public SystemsDaoImpl() {
        super(Systems.class);
    }

    @Override
    public List<Systems> findByOrganization(long organization) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Systems findBySystemNameAndOrganization(int systemNo, long organization) {
        return this.executeNamedQueryReturnObject("Systems.findBySystemNameAndOrganization", systemNo, organization);
    }

    @Override
    public Systems findByMacAddress(String macAddress) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long findNoOfActiveSystemsByOrganization(long organization) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
