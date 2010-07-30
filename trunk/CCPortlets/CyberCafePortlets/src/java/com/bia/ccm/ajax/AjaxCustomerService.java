/*
 * 
 */

package com.bia.ccm.ajax;

import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.util.AcegiUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @see  src/context/ApplicationContext-AjaxService.xml
 *
 *  This class is mapped to AjaxCustomerService bean in the above file
 *
 * @author intesar
 */
public class AjaxCustomerService {

    /**
     * 
     * @param startDateString
     * @param endDateString
     * @return
     */
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

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    protected static final Log logger = LogFactory.getLog(AjaxCustomerService.class);

    protected AdminService adminService;
}
