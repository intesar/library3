/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.dao.EmailPreferenceDao;
import com.bia.ccm.dao.EmailTimePreferenceDao;
import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.services.AccountStatusNotificationService;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author intesar
 */
@Service(value = "accountStatusNotificationServiceImpl")
public class AccountStatusNotificationServiceImpl implements AccountStatusNotificationService {

    @Override
    public List<EmailTimePreference> getEmailTimePreferences(short time) {
        return this.emailTimePreferenceDao.findByReportTime(time);
    }

    @Override
    public List<EmailPreference> getAllOrganizationEmailPreference(long organization) {
        return this.emailPreferenceDao.findByOrganization(organization);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void savePreferences(PreferenceDto preferenceDto, long organization) {
        // add new and delete old --> Emails
        List<EmailPreference> emailPreferences = this.emailPreferenceDao.findByOrganization(organization);
        Set<String> emails = preferenceDto.getEmails();
        for (EmailPreference emailPreference : emailPreferences) {
            if (!emails.contains(emailPreference.getEmail())) {
                this.emailPreferenceDao.delete(emailPreference);
            } else {
                emails.remove(emailPreference.getEmail());
            }
        }
        for (String email : emails) {
            EmailPreference emailPreference = new EmailPreference(email, organization);
            this.emailPreferenceDao.persist(emailPreference);
        }

        // Timings
        List<EmailTimePreference> emailTimePreferences = this.emailTimePreferenceDao.findByOrganization(organization);
        Set<Short> timings = preferenceDto.getTimings();
        for (EmailTimePreference emailTimePreference : emailTimePreferences) {
            if (!timings.contains(emailTimePreference.getReportTime())) {
                this.emailTimePreferenceDao.delete(emailTimePreference);
            } else {
                timings.remove(emailTimePreference.getReportTime());
            }
        }
        for (Short timing : preferenceDto.getTimings()) {
            EmailTimePreference emailTimePreference = new EmailTimePreference(timing, organization);
            this.emailTimePreferenceDao.persist(emailTimePreference);
        }
    }

    @Override
    public PreferenceDto getPreferences(long organization) {
        PreferenceDto dto = new PreferenceDto();
        List<EmailPreference> emailPreferences = this.emailPreferenceDao.findByOrganization(organization);
        for (EmailPreference emailPreference : emailPreferences) {
            dto.getEmails().add(emailPreference.getEmail());
        }
        List<EmailTimePreference> emailTimePreferences = this.emailTimePreferenceDao.findByOrganization(organization);
        for (EmailTimePreference emailTimePreference : emailTimePreferences) {
            dto.getTimings().add(emailTimePreference.getReportTime());
        }
        return dto;
    }
    @Autowired
    protected EmailPreferenceDao emailPreferenceDao;
    @Autowired
    protected EmailTimePreferenceDao emailTimePreferenceDao;
    protected static final Log logger = LogFactory.getLog(AccountStatusNotificationServiceImpl.class);
}
