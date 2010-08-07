/*
 * 
 */
package com.bia.ccm.jobs;

import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.services.AccountStatusNotificationService;
import com.bia.ccm.services.ProductService;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.SaleService;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

        List<EmailTimePreference> list = accountStatusNotificationService.getEmailTimePreferences((short) time);
        for (EmailTimePreference etp : list) {
            try {
                long org = etp.getOrganization();
                int count = 0;
                // get addresses for org
                List<EmailPreference> emailPreferences = this.accountStatusNotificationService.getAllOrganizationEmailPreference(org);
                String[] toAddress = new String[emailPreferences.size()];
                for (EmailPreference ep : emailPreferences) {
                    toAddress[count++] = ep.getEmail();
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

  

    @Autowired
    private EMailService emailService;
    @Autowired
    private SaleService workService;
    @Autowired
    private ProductService adminService;
    @Autowired
    protected AccountStatusNotificationService accountStatusNotificationService;
    protected final Log logger = LogFactory.getLog(getClass());
}
