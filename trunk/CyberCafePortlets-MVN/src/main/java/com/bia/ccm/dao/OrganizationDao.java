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

import com.bia.ccm.entity.Organization;
import com.bizintelapps.easydao.dao.GenericDao;

/**
 *
 * @author intesar
 */
public interface OrganizationDao  extends GenericDao<Organization, Long> {

    public Organization findByOrganization(String organization);
}
