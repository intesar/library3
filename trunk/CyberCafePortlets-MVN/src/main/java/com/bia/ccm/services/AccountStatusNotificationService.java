/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bia.ccm.services;

import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.PreferenceDto;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface AccountStatusNotificationService {

    /**
     *
     * @param emails
     * @param timings
     * @param organization
     * @param userId
     * @param ip
     */
    void savePreferences(PreferenceDto preferenceDto, long organization,
            String userId, String ip);

    /**
     *
     * @param organization
     * @return
     */
    PreferenceDto getPreferences(long organization);

    /**
     *
     * @param time
     * @return
     */
    List<EmailTimePreference> getEmailTimePreferences(short time);

    /**
     *
     * @param organization
     * @return
     */
    List<EmailPreference> getAllOrganizationEmailPreference(long organization);
}
