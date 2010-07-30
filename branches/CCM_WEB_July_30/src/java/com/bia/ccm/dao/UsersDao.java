/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.Users;
import java.util.List;

/**
 *
 * @author imran
 */
public interface UsersDao extends GenericDao<Users, Integer> {

    public List<Users> findByOrganization(String organization);

    public Users findByUsername(String username);

    public Users findByKey(String key);
}
