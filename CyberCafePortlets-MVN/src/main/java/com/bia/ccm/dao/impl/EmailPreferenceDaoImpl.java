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

import com.bia.ccm.dao.EmailPreferenceDao;
import com.bia.ccm.entity.EmailPreference;
import com.bizintelapps.easydao.dao.GenericDaoImpl;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class EmailPreferenceDaoImpl extends GenericDaoImpl<EmailPreference, Long> implements EmailPreferenceDao{

    public EmailPreferenceDaoImpl() {
        super(EmailPreference.class);
    }
    @Override
    public List<EmailPreference> findByOrganization(long organization) {
        return executeNamedQueryReturnList("EmailPreference.findByOrganization", organization);
    }

}
