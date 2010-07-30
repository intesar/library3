/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

    public List<EmailTimePreference> findByOrganization(String organization);

    public EmailTimePreference findByOrganizationAndReportTime(String organization, short reportTime);

    public List<EmailTimePreference> findByReportTime(int time);
}
