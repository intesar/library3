/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.UsersPass;

/**
 *
 * @author imran
 */
public interface UsersPassDao extends GenericDao<UsersPass, Integer> {

    public UsersPass findByUsernameAndEnabled(String username, boolean enabled);
}
