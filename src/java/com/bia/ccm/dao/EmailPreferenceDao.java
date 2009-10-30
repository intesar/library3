/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.EmailPreference;
import java.util.List;

/**
 *
 * @author imran
 */
public interface EmailPreferenceDao extends GenericDao<EmailPreference, Integer> {

    public List<EmailPreference> findByOrganization(String organization);
}
