/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.MembershipDiscounts;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface MembershipDiscountsDao extends GenericDao<MembershipDiscounts, Integer> {

    List<MembershipDiscounts> findByMembershipTypesId(Integer id);
    MembershipDiscounts findByMembershipTypesIdAndService (Integer id, String service);
}
