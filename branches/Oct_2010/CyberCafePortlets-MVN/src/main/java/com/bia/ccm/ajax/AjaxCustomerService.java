/*
 * 
 */
package com.bia.ccm.ajax;

import com.bia.ccm.services.ProductService;
import com.liferay.portal.theme.ThemeDisplay;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
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
//    public List getMySystemLease(String startDateString, String endDateString, HttpSession session) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate = null;
//        Date endDate = null;
//        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
//        String username = themeDisplay.getUserId() + "";
//        try {
//            startDate = sdf.parse(startDateString);
//            endDate = sdf.parse(endDateString);
//            return this.adminService.getMySystemLease(startDate, endDate, username);
//        } catch (ParseException ex) {
//            logger.warn(ex.getMessage(), ex);
//            return null;
//        }
//    }

    public void setAdminService(ProductService adminService) {
        this.adminService = adminService;
    }
    protected static final Log logger = LogFactory.getLog(AjaxCustomerService.class);
    protected ProductService adminService;
}
