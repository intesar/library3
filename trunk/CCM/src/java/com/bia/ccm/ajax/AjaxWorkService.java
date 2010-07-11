/*
 * 
 */
package com.bia.ccm.ajax;

import com.abbhsoft.sqlInjectionFilter.SQLInjectionFilterManager;
import com.bia.ccm.entity.MembershipDiscounts;
import com.bia.ccm.entity.MembershipTypes;
import com.bia.ccm.entity.Memberships;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.UsageDetail;
import com.bia.ccm.entity.Users;
import com.bia.ccm.entity.UsersLight;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.MembershipService;
import com.bia.ccm.services.WorkService;
import com.bia.ccm.util.AcegiUtil;
import com.bia.ccm.util.ServiceFactory;
import com.bia.converter.CaseConverter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public List<Services> getAllServices() {
        try {
            return this.workService.getAllServices(AcegiUtil.getUsername());
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    public List<Systems> getActiveSystems() {
        try {
            String username = AcegiUtil.getUsername();
            return workService.getActiveSystems(username);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * not in use
     * @param systemNo
     * @return
     */
    public Systems getSystemByNameAndOrganization(int systemNo) {
        String username = AcegiUtil.getUsername();
        return this.workService.getSystemByNameAndOrganization(systemNo, username);
    }

    /**
     *
     * @param systemId
     * @param leaseHolder
     * @return
     */
    public int leaseSystem(int systemId, String leaseHolder) {
        try {
            this.workService.leaseSystem(systemId, leaseHolder, AcegiUtil.getUsername());
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }

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
    public int addService(String service, Long units, String user, Double payableAmount,
            String comments, Double paidAmount) {

        try {
            String agent = AcegiUtil.getUsername();
            this.workService.addService(service, units, user, payableAmount, comments, paidAmount, agent);
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }

    }

    /**
     *
     * @param id
     * @return
     */
    public UsageDetail getPayableAmount(int id) {
        try {
            return this.workService.getPayableAmount(id);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param systemId
     * @param paidAmount
     * @return
     */
    public int unleaseSystem(int systemId, double paidAmount) {
        try {
            this.workService.unleaseSystem(systemId, paidAmount, AcegiUtil.getUsername());
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     *
     * @param c
     * @return
     */
    public int createCustomer(Users c) {
        try {
            c.setCreateDate(new Date());
            Users u = this.workService.getCustomer(AcegiUtil.getUsername());
            c.setIsVerified(true);
            c.setVerifiedBy(u.getUsername());
            //c.setCreateUser(AcegiUtil.getUsername());
            this.workService.createCutomer(c, u);
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public Users getUserWithPic(String key) {
        try {
            Users c = null;
            key = SQLInjectionFilterManager.getInstance().filter(key);
            try {
                if (key != null) {
                    key = key.toLowerCase();
                }
                c = this.workService.getCustomerPic(key);
                //c.setPassword("Encrypted");
            } catch (Exception ex) {
                logger.warn(ex.getMessage(), ex);
            }
            if (c == null) {
                c = new Users();
            }
            return c;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public Users getCustomer(String key) {
        try {
            Users c = null;
            key = SQLInjectionFilterManager.getInstance().filter(key);
            try {
                if (key != null) {
                    key = key.toLowerCase();
                }
                c = this.workService.getCustomer(key);
                //c.setPassword("Encrypted");
            } catch (Exception ex) {
                logger.warn(ex.getMessage(), ex);
            }
            if (c == null) {
                c = new Users();
                c.setComments("No record found for given email!");
            }
            return c;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public List<SystemLease> getSystemLease(int id) {
        try {
            return this.workService.getSystemLease(id);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param systemId
     * @return
     */
    public int chargePayment(int systemId) {
        try {
            workService.chargePayment(systemId, AcegiUtil.getUsername());
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     *
     * @return
     */
    public Organization getOrganization() {
        String username = AcegiUtil.getUsername();
        return this.adminService.getOrganization(username);
    }
    // membership for next milestone
    public List<MembershipTypes> getAllMembershipTypes() {
        String org = this.getOrganization().getName();
        List<MembershipTypes> list = this.membershipService.getMembershipTypesByOrganization(org);
        return list;
    }

    public int saveMembershipType(MembershipTypes membershipTypes) {
        try {
            if (membershipTypes == null || membershipTypes.getName() == null
                    || membershipTypes.getName().trim().length() <= 0
                    || membershipTypes.getFee() <= 0.0 || membershipTypes.getDaysValidFor() <= 0) {
                //return "Invalid Inputs!";
            }
            Organization o = this.getOrganization();
            if (membershipTypes.getId() == null) {
                membershipTypes.setCreateDate(new Date());
                membershipTypes.setCreateUser(AcegiUtil.getUsername());
                membershipTypes.setOrganization(o);
                membershipTypes.setName(membershipTypes.getName().toLowerCase());
                this.membershipService.saveMembershipType(membershipTypes);

                membershipTypes = this.membershipService.getMembershipTypesByOrganizationAndName(o.getName(), membershipTypes.getName());
                List<Services> serviceses = this.getAllServices();
                if (serviceses == null) {
                    serviceses = new ArrayList<Services>();
                }
                serviceses.add(new Services(0, "computer", 0, ""));

                for (Services s : serviceses) {
                    MembershipDiscounts md = new MembershipDiscounts(null, s.getName(),
                            0.0, new Date(), AcegiUtil.getUsername());
                    md.setMembershipType(membershipTypes);
                    this.membershipService.saveMembershipDiscounts(md);
                }
            } else {
                this.membershipService.saveMembershipType(membershipTypes);
            }
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     * 
     * @param membershipType
     * @return
     */
    public List<MembershipDiscounts> getMembershipDiscountses(Integer membershipType) {
        return this.membershipService.getMembershipDiscountsByMembershipTypesId(membershipType);
    }

    public void createMembershipDiscount(String service) {
        String org = this.getOrganization().getName();
        List<MembershipTypes> list = this.membershipService.getMembershipTypesByOrganization(org);
        for (MembershipTypes mt : list) {
            MembershipDiscounts md = new MembershipDiscounts(null, service, 0.0, new Date(), AcegiUtil.getUsername());
            md.setMembershipType(mt);
            this.membershipService.saveMembershipDiscounts(md);
        }
    }

    public int saveMembershipDiscount(int membershipDiscountId, double discountPercentage) {
        try {
            if (discountPercentage < 0.0 || discountPercentage > 100.0) {
                //return "Discount Percentage cannot be Negative or Greater than 100!";
            }
            MembershipDiscounts discounts = this.membershipService.getMembershipDiscountsById(membershipDiscountId);
            discounts.setDiscountPercentage(discountPercentage);
            this.membershipService.saveMembershipDiscounts(discounts);
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    public int saveMembership(Memberships memberships) {
        UsersLight ul = this.adminService.getUserByUsername(memberships.getEmail());
        if (ul == null || ul.getId() == null) {
            //return "No user found with this email";
        }
        try {
            this.membershipService.validateUserMembership(memberships.getEmail(), this.getOrganization().getName());

            this.membershipService.saveMembership(memberships, this.getOrganization(), AcegiUtil.getUsername());
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    public List<Memberships> getMembers() {
        List<Memberships> list = new ArrayList<Memberships>();
        try {
            list = this.membershipService.getMembershipsByOrganization(this.getOrganization().getName());
        } catch (RuntimeException re) {
        }
        return list;
    }
    // end of memberships
     private AdminService adminService = (AdminService) ServiceFactory.getService("adminServiceImpl");
    private MembershipService membershipService = (MembershipService) ServiceFactory.getService("membershipServiceImpl");
    protected final Log logger = LogFactory.getLog(getClass());
    private WorkService workService = (WorkService) ServiceFactory.getService("workServiceImpl");
}
