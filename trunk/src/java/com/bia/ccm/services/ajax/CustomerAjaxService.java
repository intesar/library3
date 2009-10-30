/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.services.ajax;

import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Users;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.util.AcegiUtil;
import com.bia.ccm.util.ServiceFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Logger.getLogger(AdminAjaxService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    private AdminService adminService = (AdminService) ServiceFactory.getService("adminServiceImpl");
}
