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

import com.bia.ccm.dao.EmailTimePreferenceDao;
import com.bia.ccm.entity.EmailTimePreference;
import com.bizintelapps.easydao.dao.GenericDaoImpl;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class EmailTimePreferenceDaoImpl extends GenericDaoImpl<EmailTimePreference, Long> implements EmailTimePreferenceDao {

    public EmailTimePreferenceDaoImpl() {
        super(EmailTimePreference.class);
    }

    @Override
    public List<EmailTimePreference> findByOrganization(long organization) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmailTimePreference findByOrganizationAndReportTime(long organization, short reportTime) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EmailTimePreference> findByReportTime(int time) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
