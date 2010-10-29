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

import com.bia.ccm.entity.Memberships;
import com.bizintelapps.easydao.dao.GenericDao;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface MembershipsDao extends GenericDao<Memberships, Long> {
    public List<Memberships> findByOrganizationAndEmail (String org, String user);
    public List<Memberships> findByOrganization (String org);
    public Memberships findByOrganizationAndEmailAndActive(String org, String user);
}
