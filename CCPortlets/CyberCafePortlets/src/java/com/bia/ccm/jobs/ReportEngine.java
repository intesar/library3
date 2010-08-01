/*
 * 
 */
package com.bia.ccm.jobs;

import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.WorkService;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class ReportEngine {

    /**
     *
     */
    public void processReportAtContractStart() {
        if (logger.isTraceEnabled()) {
            logger.trace(" Started workService.notifyCustomersAtContractStart() ");
        }
        try {
            workService.notifyCustomersAtContractStart();
        } catch (RuntimeException ex) {
            logger.warn(ex.getMessage(), ex);
        }

    }

    /**
     *
     */
    public void processReportAtContractEnd() {
        if (logger.isTraceEnabled()) {
            logger.trace("started workService.notifyCustomersAtContractEnd()");
        }
        try {
            workService.notifyCustomersAtContractEnd();
        } catch (RuntimeException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    /**
     *
     */
    public void processReport() {
        if (logger.isTraceEnabled()) {
            logger.trace(" inside processReport() ");
        }
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY) * 100;

        List<EmailTimePreference> list = adminService.getEmailTimePreferences((short) time);
        for (EmailTimePreference etp : list) {
            try {
                long org = etp.getOrganization();
                int count = 0;
                // get addresses for org
                List<EmailPreference> emailPreferences = this.adminService.getAllOrganizationEmailPreference(org);
                String[] toAddress = new String[emailPreferences.size()];
                for (EmailPreference ep : emailPreferences) {
                    if (ep.getServiceProvider().equals("email")) {
                        toAddress[count++] = ep.getEmailOrPhone();
                    } else {
                        toAddress[count++] = ep.getEmailOrPhone() + ep.getServiceProvider();
                    }
                }
                if (toAddress.length > 0) {
                    List list1 = this.adminService.getReport(calendar.getTime(), calendar.getTime(), org);
                    this.emailService.sendEmail(toAddress, list1.toString(), null);
                }
            } catch (RuntimeException ex) {
                logger.warn(ex.getMessage(), ex);
            }
        }
    }

    // getters and setters
    public void setEmailService(EMailService emailService) {
        this.emailService = emailService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }
    private EMailService emailService;
    private WorkService workService;
    private AdminService adminService;
    protected final Log logger = LogFactory.getLog(getClass());
}
