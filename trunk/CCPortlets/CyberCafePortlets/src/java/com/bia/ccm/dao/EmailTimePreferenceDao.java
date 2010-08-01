/*
 * 
 */
package com.bia.ccm.dao;

import com.abbhsoft.jpadaoframework.dao.GenericDao;
import com.bia.ccm.entity.EmailTimePreference;
import java.util.List;

/**
 *
 * @author imran
 */
public interface EmailTimePreferenceDao extends GenericDao<EmailTimePreference, Integer> {

    public List<EmailTimePreference> findByOrganization(long organization);

    public EmailTimePreference findByOrganizationAndReportTime(long organization, short reportTime);

    public List<EmailTimePreference> findByReportTime(int time);
}
