/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.services;

import com.bia.ccm.entity.MembershipDiscounts;
import com.bia.ccm.entity.MembershipTypes;
import com.bia.ccm.entity.Memberships;
import com.bia.ccm.entity.Organization;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface MembershipService {

    /**
     *
     * @param id
     * @return
     */
    List<MembershipDiscounts> getMembershipDiscountsByMembershipTypesId(Integer id);

    /**
     *
     * @param org
     * @return
     */
    List<MembershipTypes> getMembershipTypesByOrganization(String org);
    
    /**
     * 
     * @param org
     * @param name
     * @return
     */
    MembershipTypes getMembershipTypesByOrganizationAndName (String org, String name );

    /**
     *
     * @param id
     * @return
     */
    Memberships getMembershipsById(Integer id);

    /**
     *
     * @param org
     * @return
     */
    List<Memberships> getMembershipsByOrganization(String org);

    /**
     *
     * @param org
     * @param user
     * @return
     */
    Memberships getMembershipsByOrganizationAndUsername(String org, String user);

    /**
     * add/update Membership
     * if the id is null create a new one else updates the record
     * @param membership
     */
    void saveMembership(Memberships memberships, Organization org, String cashier);

    /**
     * add/update MembershipDiscounts
     * if the id is null create a new one else updates the record
     * @param membershipDiscounts
     */
    void saveMembershipDiscounts(MembershipDiscounts membershipDiscounts);

    /**
     * 
     * @param id
     * @return
     */
    MembershipDiscounts getMembershipDiscountsById(Integer id);
    
    /**
     * add/update  membership to organization
     * if id is null creates a new otherwise tries to save
     * @param membershipTypes
     */
    void saveMembershipType(MembershipTypes membershipTypes);

}
