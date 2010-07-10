/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.ajax;

import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.util.AcegiUtil;
import com.bia.ccm.util.ServiceFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class CustomerAjaxService {
    public List<SystemLease> getMySystemLease ( String startDateString, String endDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        String username = AcegiUtil.getUsername();
        try {
            startDate = sdf.parse(startDateString);
            endDate = sdf.parse(endDateString);
            return this.adminService.getMySystemLease(startDate, endDate, username);
        } catch (ParseException ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }
    protected static final Log logger = LogFactory.getLog(CustomerAjaxService.class);
    private AdminService adminService = (AdminService) ServiceFactory.getService("adminServiceImpl");
}
