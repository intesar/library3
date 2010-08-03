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

import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.entity.Organization;
import com.bizintelapps.easydao.dao.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class OrganizationDaoImpl extends GenericDaoImpl<Organization, Long> implements OrganizationDao {

    public OrganizationDaoImpl() {
        super(Organization.class);
    }

    @Override
    public Organization findByOrganization(String organization) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
