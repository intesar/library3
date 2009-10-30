/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.Organization;

/**
 *
 * @author intesar
 */
public interface OrganizationDao  extends GenericDao<Organization, String> {

    public Organization findByOrganization(String organization);
}
