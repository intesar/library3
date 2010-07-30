/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.UserPic;

/**
 *
 * @author intesar
 */
public interface UserPicDao extends GenericDao<UserPic, Integer>  {
    UserPic findByUsername (String username);
}
