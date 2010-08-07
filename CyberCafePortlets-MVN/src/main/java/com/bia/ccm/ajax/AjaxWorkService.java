/*
 * 
 */
package com.bia.ccm.ajax;

import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.UsageDetail;
import com.bia.ccm.services.ProductService;
import com.bia.ccm.services.MembershipService;
import com.bia.ccm.services.SaleService;
import com.liferay.portal.theme.ThemeDisplay;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class AjaxWorkService {

    /**
     * 
     * @return
     */
    public List<Services> getAllServices(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organization = themeDisplay.getScopeGroupId();
        return this.workService.getAllServices(organization);
    }

    /**
     *
     * @return
     */
    public List<Systems> getActiveSystems(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organization = themeDisplay.getScopeGroupId();
        return workService.getActiveSystems(organization);
    }

    /**
     * not in use
     * @param systemNo
     * @return
     */
    public Systems getSystemByNameAndOrganization(int systemNo, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organization = themeDisplay.getScopeGroupId();
        return this.workService.getSystemByNameAndOrganization(systemNo, organization);
    }

    /**
     *
     * @param systemId
     * @param leaseHolder
     * @return
     */
    public void leaseSystem(int systemId, String leaseHolder, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String username = themeDisplay.getUserId() + "";
        this.workService.leaseSystem(systemId, leaseHolder, username);
    }

    /**
     * 
     * @param service
     * @param units
     * @param user
     * @param payableAmount
     * @param comments
     * @param paidAmount
     * @return
     */
    public void addService(String service, Long units, String user, Double payableAmount,
            String comments, Double paidAmount, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String username = themeDisplay.getUserId() + "";
        long organization = themeDisplay.getScopeGroupId();
        this.workService.addService(service, units, user, payableAmount, comments, paidAmount, username, organization);
    }

    /**
     *
     * @param id
     * @return
     */
    public UsageDetail getPayableAmount(int id) {
        return this.workService.getPayableAmount(id);
    }

    /**
     *
     * @param systemId
     * @param paidAmount
     * @return
     */
    public void unleaseSystem(int systemId, double paidAmount, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String username = themeDisplay.getUserId() + "";
        this.workService.unleaseSystem(systemId, paidAmount, username);
    }

    /**
     *
     * @param id
     * @return
     */
    public List<SystemLease> getSystemLease(int id) {
        return this.workService.getSystemLease(id);
    }

    /**
     *
     * @param systemId
     * @return
     */
    public void chargePayment(int systemId, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String username = themeDisplay.getUserId() + "";
        long organization = themeDisplay.getScopeGroupId();
        workService.chargePayment(systemId, username, organization);
    }

    // membership for next milestone
//    public List<MembershipTypes> getAllMembershipTypes() {
//        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
//            String username = themeDisplay.getUserId() + "";
//            String organization = themeDisplay.getScopeGroupId() + "";
//        List<MembershipTypes> list = this.membershipService.getMembershipTypesByOrganization(organization);
//        return list;
//    }
//
//    public int saveMembershipType(MembershipTypes membershipTypes) {
//        try {
//            if (membershipTypes == null || membershipTypes.getName() == null
//                    || membershipTypes.getName().trim().length() <= 0
//                    || membershipTypes.getFee() <= 0.0 || membershipTypes.getDaysValidFor() <= 0) {
//                //return "Invalid Inputs!";
//            }
//            Organization o = this.getOrganization();
//            if (membershipTypes.getId() == null) {
//                membershipTypes.setCreateDate(new Date());
//                membershipTypes.setCreateUser(AcegiUtil.getUsername());
//                membershipTypes.setOrganization(o);
//                membershipTypes.setName(membershipTypes.getName().toLowerCase());
//                this.membershipService.saveMembershipType(membershipTypes);
//
//                membershipTypes = this.membershipService.getMembershipTypesByOrganizationAndName(o.getName(), membershipTypes.getName());
//                List<Services> serviceses = this.getAllServices();
//                if (serviceses == null) {
//                    serviceses = new ArrayList<Services>();
//                }
//                serviceses.add(new Services(0, "computer", 0, ""));
//
//                for (Services s : serviceses) {
//                    MembershipDiscounts md = new MembershipDiscounts(null, s.getName(),
//                            0.0, new Date(), AcegiUtil.getUsername());
//                    md.setMembershipType(membershipTypes);
//                    this.membershipService.saveMembershipDiscounts(md);
//                }
//            } else {
//                this.membershipService.saveMembershipType(membershipTypes);
//            }
//            return 1;
//        } catch (InvalidInputException ex) {
//            logger.warn(ex.getMessage(), ex);
//            return -2;
//        } catch (NoRoleException ex) {
//            logger.warn(ex.getMessage(), ex);
//            return -3;
//        } catch (Exception ex) {
//            logger.warn(ex.getMessage(), ex);
//            return -1;
//        }
//    }
//
//    /**
//     *
//     * @param membershipType
//     * @return
//     */
//    public List<MembershipDiscounts> getMembershipDiscountses(Integer membershipType) {
//        return this.membershipService.getMembershipDiscountsByMembershipTypesId(membershipType);
//    }
//
//    public void createMembershipDiscount(String service) {
//        String org = this.getOrganization().getName();
//        List<MembershipTypes> list = this.membershipService.getMembershipTypesByOrganization(org);
//        for (MembershipTypes mt : list) {
//            MembershipDiscounts md = new MembershipDiscounts(null, service, 0.0, new Date(), AcegiUtil.getUsername());
//            md.setMembershipType(mt);
//            this.membershipService.saveMembershipDiscounts(md);
//        }
//    }
//
//    public int saveMembershipDiscount(int membershipDiscountId, double discountPercentage) {
//        try {
//            if (discountPercentage < 0.0 || discountPercentage > 100.0) {
//                //return "Discount Percentage cannot be Negative or Greater than 100!";
//            }
//            MembershipDiscounts discounts = this.membershipService.getMembershipDiscountsById(membershipDiscountId);
//            discounts.setDiscountPercentage(discountPercentage);
//            this.membershipService.saveMembershipDiscounts(discounts);
//            return 1;
//        } catch (InvalidInputException ex) {
//            logger.warn(ex.getMessage(), ex);
//            return -2;
//        } catch (NoRoleException ex) {
//            logger.warn(ex.getMessage(), ex);
//            return -3;
//        } catch (Exception ex) {
//            logger.warn(ex.getMessage(), ex);
//            return -1;
//        }
//    }
//
//    public int saveMembership(Memberships memberships) {
//        UsersLight ul = this.adminService.getUserByUsername(memberships.getEmail());
//        if (ul == null || ul.getId() == null) {
//            //return "No user found with this email";
//        }
//        try {
//            this.membershipService.validateUserMembership(memberships.getEmail(), this.getOrganization().getName());
//
//            this.membershipService.saveMembership(memberships, this.getOrganization(), AcegiUtil.getUsername());
//            return 1;
//        } catch (InvalidInputException ex) {
//            logger.warn(ex.getMessage(), ex);
//            return -2;
//        } catch (NoRoleException ex) {
//            logger.warn(ex.getMessage(), ex);
//            return -3;
//        } catch (Exception ex) {
//            logger.warn(ex.getMessage(), ex);
//            return -1;
//        }
//    }
//
//    public List<Memberships> getMembers() {
//        List<Memberships> list = new ArrayList<Memberships>();
//        try {
//            list = this.membershipService.getMembershipsByOrganization(this.getOrganization().getName());
//        } catch (RuntimeException re) {
//        }
//        return list;
//    }
    // getters and setters
    public void setAdminService(ProductService adminService) {
        this.adminService = adminService;
    }

    public void setMembershipService(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    public void setWorkService(SaleService workService) {
        this.workService = workService;
    }
    
    protected ProductService adminService;
    protected MembershipService membershipService;
    protected SaleService workService;
    protected static final Log logger = LogFactory.getLog(AjaxWorkService.class);
}
