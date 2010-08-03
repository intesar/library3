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

import com.bia.ccm.entity.EmailTimePreference;
import com.bizintelapps.easydao.dao.GenericDao;
import java.util.List;

/**
 *
 * @author imran
 */
public interface EmailTimePreferenceDao extends GenericDao<EmailTimePreference, Long> {

    public List<EmailTimePreference> findByOrganization(long organization);

    public EmailTimePreference findByOrganizationAndReportTime(long organization, short reportTime);

    public List<EmailTimePreference> findByReportTime(int time);
}
