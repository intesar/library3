/*
 * 
 */

package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.Organization;

/**
 *
 * @author intesar
 */
public interface OrganizationDao  extends GenericDao<Organization, Long> {

    public Organization findByOrganization(String organization);
}
