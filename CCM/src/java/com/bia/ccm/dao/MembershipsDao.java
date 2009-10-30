/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.Memberships;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface MembershipsDao extends GenericDao<Memberships, Integer> {
    public Memberships findByOrganizationAndEmail (String org, String user);
    public List<Memberships> findByOrganization (String org);

}
