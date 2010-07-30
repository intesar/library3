/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.MembershipTypes;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface MembershipTypesDao extends GenericDao<MembershipTypes, Integer> {
    List<MembershipTypes> findByOrganization (String org);
    MembershipTypes findByOrganizationAndName ( String org, String name);
}
