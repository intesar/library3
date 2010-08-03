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

import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.entity.Services;
import com.bizintelapps.easydao.dao.GenericDaoImpl;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class ServicesDaoImpl extends GenericDaoImpl<Services, Long> implements ServicesDao {

    public ServicesDaoImpl() {
        super(Services.class);
    }

    @Override
    public List<Services> findByOrganization(long org) {
        return this.executeNamedQueryReturnList("Services.findByOrganization", org);
    }

    
}
