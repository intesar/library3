/*
 * 
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

    public List<EmailPreference> findByOrganization(long organization);
}
