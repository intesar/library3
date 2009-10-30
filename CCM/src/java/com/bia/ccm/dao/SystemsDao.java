/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.Systems;
import java.util.List;

/**
 *
 * @author imran
 */
public interface SystemsDao extends GenericDao<Systems, Integer> {

    public List<Systems> findByOrganization(String organization);

    public Systems findBySystemNameAndOrganization(int systemNo, String organization);

    Systems findByMacAddress(String macAddress);

    Long findNoOfActiveSystemsByOrganization(String org);
}
