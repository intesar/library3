/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.jobs;

import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.WorkService;
import com.bia.ccm.util.ServiceFactory;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class ReportEngine {

    public void processReportAtContractStart() {
        logger.debug("inside processReport ***************** ");
        // find all SystemLease Records that was not notified
        //iterate and send emails
        try {
            workService.notifyCustomersAtContractStart();
        } catch (RuntimeException ex) {
            logger.warn(ex.getMessage(), ex);
        }

    }

    public void processReportAtContractEnd() {
        logger.debug("inside processReport ***************** ");
        // find all SystemLease Records that was not notified
        //iterate and send emails
        try {
            workService.notifyCustomersAtContractEnd();
        } catch (RuntimeException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    public void processReport() {
        logger.debug(" inside processing report ___________________________________ ");
        System.out.println(" inside processing report ___________________________________ ");
        Date dt = new Date();
        int time = dt.getHours();
        time = time * 100;

        List<EmailTimePreference> list = adminService.getEmailTimePreferences((short) time);
        for (EmailTimePreference etp : list) {
            try {
                String org = etp.getOrganization();
                List list1 = this.adminService.getReport(new Date(), dt, org);

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
//                    } else if (ep.getServiceProvider().startsWith("b")) {
//                        toAddress[count++] = ep.getEmailOrPhone() + emailService.bsnl;
//                    } else if (ep.getServiceProvider().startsWith("i")) {
//                        toAddress[count++] = ep.getEmailOrPhone() + emailService.idea;
//                    }
                }
                this.emailService.sendEmail(toAddress, list1.toString(), null);

            } catch (RuntimeException ex) {
                logger.warn(ex.getMessage(), ex);
            }
        }
    }

    public static void main(String[] args) {
        ReportEngine re = new ReportEngine();
        re.workService.notifyCustomersAtContractStart();
        
    }
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
