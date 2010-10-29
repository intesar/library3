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

import com.bia.ccm.entity.EmailPreference;
import com.bizintelapps.easydao.dao.GenericDao;
import java.util.List;

/**
 *
 * @author imran
 */
public interface EmailPreferenceDao extends GenericDao<EmailPreference, Long> {

    public List<EmailPreference> findByOrganization(long organization);
}
