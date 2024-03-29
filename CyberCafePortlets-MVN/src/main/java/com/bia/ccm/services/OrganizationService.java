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
    Organization saveOrganization(Organization organization, long organizationId);

    /**
     *
     * @param organizationId
     * @return
     */
    Organization getOrganization(long organizationId);

    /**
     *
     * @param organizationId
     * @param organizationName
     * @param email
     */
    void registerNewOrganization(long organizationId, String organizationName, String email);
}
