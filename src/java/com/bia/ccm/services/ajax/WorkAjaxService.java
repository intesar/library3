/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.ajax;

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
public class WorkAjaxService {

    public List<Services> getAllServices() {
        return this.workService.getAllServices(AcegiUtil.getUsername());
    }

    public List<Systems> getActiveSystems() {
        String username = AcegiUtil.getUsername();
        return workService.getActiveSystems(username);
    }

    public Systems getSystemByNameAndOrganization(int systemNo) {
        String username = AcegiUtil.getUsername();
        return this.workService.getSystemByNameAndOrganization(systemNo, username);
    }

    public String leaseSystem(int systemId, String leaseHolder) {
        try {
            if (leaseHolder != null) {
                leaseHolder = SQLInjectionFilterManager.getInstance().filter(leaseHolder);
                leaseHolder = leaseHolder.toLowerCase();
            }
            this.workService.leaseSystem(systemId, leaseHolder, AcegiUtil.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return e.getMessage();
        }
        return "Assigned Successfully!";

    }

    public String addService(String service, Long units, String user, Double payableAmount,
            String comments, Double paidAmount) {
        String msg = "Service Added Successfully";
        String agent = AcegiUtil.getUsername();
        try {
            service = SQLInjectionFilterManager.getInstance().filter(service);
            user = SQLInjectionFilterManager.getInstance().filter(user);
            comments = SQLInjectionFilterManager.getInstance().filter(comments);
            if (units <= 0 || payableAmount <= 0 || paidAmount <= 0) {
                return "We are keeping an Eye on you! ";
            }
            this.workService.addService(service, units, user, payableAmount, comments, paidAmount, agent);
            return msg;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error(e);
            return e.getMessage();
        }

    }

    public UsageDetail getPayableAmount(int id) {
        return this.workService.getPayableAmount(id);
    }

    public String unleaseSystem(int systemId, double paidAmount) {
        try {
            if (systemId <= 0 || paidAmount <= 0) {
                return "We are keeping an Eye on you! ";
            }
            this.workService.unleaseSystem(systemId, paidAmount, AcegiUtil.getUsername());
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error(e);
            return e.getMessage();
        }
        return "Assigned Successfully!";
    }

    public String createCustomer(Users c) {

        String msg = "Customer Created Successfully!";
        try {
//            if (c.getImg() != null) {
//                c.setPic(this.bufferedImageToByteArray(c.getImg()));//this.scaleToSize(c.getImg())
//            }
            c.setCreateDate(new Date());
            Users u = this.workService.getCustomer(AcegiUtil.getUsername());
            c.setIsVerified(true);
            c.setVerifiedBy(u.getUsername());
            //c.setCreateUser(AcegiUtil.getUsername());
            caseConverter.toLowerCase(c, "password");
            this.workService.createCutomer(c, u);
            emailService.sendEmail(c.getEmail(), "Welcome to FaceGuard, username / password : " + c.getUsername() + " / " + c.getPassword());
        } catch (RuntimeException re) {
            logger.error(re);
            //re.printStackTrace();
            msg = re.getMessage();
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error(e);
            msg = e.getMessage();
        }
        return msg;
    }

    public Users getUserWithPic(String key) {
        Users c = null;
        key = SQLInjectionFilterManager.getInstance().filter(key);
        try {
            if (key != null) {
                key = key.toLowerCase();
            }
            c = this.workService.getCustomerPic(key);
        //c.setPassword("Encrypted");
        } catch (Exception e) {
            logger.error(e);
        }
        if (c == null) {
            c = new Users();
        }
//        if (c.getUserPic() != null && c.getUserPic().getPic() != null ) {
//            c.setImage(this.byteArrayToBufferedImage(c.getUserPic().getPic()));
//            c.setImg(null);
//        }

        return c;
    }

    public Users getCustomer(String key) {
        Users c = null;
        key = SQLInjectionFilterManager.getInstance().filter(key);
        try {
            if (key != null) {
                key = key.toLowerCase();
            }
            c = this.workService.getCustomer(key);
        //c.setPassword("Encrypted");
        } catch (Exception e) {
            logger.error(e);
        }
        if (c == null) {
            c = new Users();
            c.setComments("No record found for given email!");
        }


        return c;
    }

    public List<SystemLease> getSystemLease(int id) {
        return this.workService.getSystemLease(id);
    }

    //public String addSuggestion()
    public String chargePayment(int systemId) {
        String msg = "Payment Successful!";
        try {
            workService.chargePayment(systemId, AcegiUtil.getUsername());
        } catch (RuntimeException re) {
            logger.error(re.getMessage());
            return re.getMessage();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
        return msg;
    }

    public Organization getOrganization() {
        String username = AcegiUtil.getUsername();
        return this.adminService.getOrganization(username);
    }

    public List<MembershipTypes> getAllMembershipTypes() {
        String org = this.getOrganization().getName();
        List<MembershipTypes> list = this.membershipService.getMembershipTypesByOrganization(org);
        return list;
    }

    public String saveMembershipType(MembershipTypes membershipTypes) {
        try {
            if (membershipTypes == null || membershipTypes.getName() == null ||
                    membershipTypes.getName().trim().length() <= 0 ||
                    membershipTypes.getFee() <= 0.0 || membershipTypes.getDaysValidFor() <= 0) {
                return "Invalid Inputs!";
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
            return "Created Successfully!";
        } catch (Exception e) {
            logger.error(e);
            return "Error, Please check your inputs or Try later!";
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

    public String saveMembershipDiscount(int membershipDiscountId, double discountPercentage) {
        try {
            if (discountPercentage < 0.0 || discountPercentage > 100.0) {
                return "Discount Percentage cannot be Negative or Greater than 100!";
            }
            MembershipDiscounts discounts = this.membershipService.getMembershipDiscountsById(membershipDiscountId);
            discounts.setDiscountPercentage(discountPercentage);
            this.membershipService.saveMembershipDiscounts(discounts);
            return "Saved Successfully!";
        } catch (Exception e) {
            logger.error(e);
            return "Error, Please try again!";
        }
    }

    public String saveMembership(Memberships memberships) {
        try {
            this.membershipService.saveMembership(memberships, this.getOrganization(), AcegiUtil.getUsername());
            return "Saved Successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return "Error, Try again!";
        }
    }

    public List<Memberships> getMemberships(String key) {
        try {
            key = SQLInjectionFilterManager.getInstance().filter(key);
            if (key == null || key.trim().length() < 5) {
                return this.membershipService.getMembershipsByOrganization(this.getOrganization().getName());
            } else {
                List<Memberships> list = new ArrayList<Memberships>();
                Memberships m = this.membershipService.getMembershipsByOrganizationAndUsername(this.getOrganization().getName(), key);
                list.add(m);
                return list;
            }
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public void setCaseConverter(CaseConverter caseConverter) {
        this.caseConverter = caseConverter;
    }

    public void setEmailService(EMailService emailService) {
        this.emailService = emailService;
    }
    private EMailService emailService;
    private CaseConverter caseConverter;
    private AdminService adminService = (AdminService) ServiceFactory.getService("adminServiceImpl");
    private MembershipService membershipService = (MembershipService) ServiceFactory.getService("membershipServiceImpl");
    protected final Log logger = LogFactory.getLog(getClass());
    private WorkService workService = (WorkService) ServiceFactory.getService("workServiceImpl");

    public static void main(String[] args) {
        WorkAjaxService was = new WorkAjaxService();

//        Users c = new Users(null, "Intesar shannan Mohammed", "intesar.mohammed@bizintelapps.com",
//                "9-4-62/23 nizam colony, towli chowki", "hyderabad", "500008", "ap", "india", new Date(), "male");
    // System.out.println ( was.createCustomer());
//        System.out.println(was.getCustomer("intesar.mohammed@bizintelapps.com").getName());
    //System.out.println ( was.getPayableAmount(277));
    //System.out.println ( was.addService("B/W Print", 3, "2", 9.0, "", 9.0) );
    }
}
