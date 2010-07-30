/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.Services;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface ServicesDao extends GenericDao<Services, Integer> {
    public List<Services> findByOrganization (String org);
}
