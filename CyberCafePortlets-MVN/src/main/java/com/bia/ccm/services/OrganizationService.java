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

import com.bia.ccm.entity.Organization;

/**
 *
 * @author intesar
 */
public interface OrganizationService {

    /**
     *
     * @param organization
     */
    void saveOrganization(Organization organization, long organizationId, long userId, String ip);

    /**
     *
     * @param organizationId
     * @return
     */
    Organization getOrganization(long organizationId, String organizationName);

    /**
     *
     * @param organizationId
     * @param organizationName
     * @param email
     */
    void registerNewOrganization(long organizationId, String organizationName);
}
