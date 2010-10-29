/*
 * 
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
    public List<Services> findByOrganization (long org);
}
