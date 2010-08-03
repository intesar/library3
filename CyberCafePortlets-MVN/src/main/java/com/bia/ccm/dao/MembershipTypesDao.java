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

import com.bia.ccm.entity.MembershipTypes;
import com.bizintelapps.easydao.dao.GenericDao;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface MembershipTypesDao extends GenericDao<MembershipTypes, Long> {
    List<MembershipTypes> findByOrganization (String org);
    MembershipTypes findByOrganizationAndName ( String org, String name);
}
