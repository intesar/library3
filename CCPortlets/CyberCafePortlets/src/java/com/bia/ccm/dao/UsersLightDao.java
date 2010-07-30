/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.UsersLight;
import java.util.List;

/**
 *
 * @author imran
 */
public interface UsersLightDao extends GenericDao<UsersLight, String> {

    public List<UsersLight> findByOrganization(String organization);

    public UsersLight findByUsername(String username);

    
}
