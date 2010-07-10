/*
 * 
 */

package com.bia.ccm.services;

/**
 * 
 * @author intesar
 */
public interface UserService {
    /**
     * 
     * @param username
     * @return
     */
    public String getUserRole(String username);
    
    /**
     * Step 1 (2) of password reseting process
     * @param email           (converts to lowercase)
     */
    public void forgotPassword(String email);
    /**
     * Step 2 (2) of password reseting process
     * @param email           (converts to lowercase)
     * @param activationCode
     * @param password
     * @param ip user IP address
     */
    public void resetPassword(String email, String activationCode, String password, String ip);
    /**
     * creates Organization, User, Services object in DB and also sends a welcome Email to user
     *
     * @param organizationName (converts to lowercase)
     * @param city             (converts to lowercase)
     * @param email            (converts to lowercase)
     * @param password
     * @param minutes
     * @param rate
     * @param maxSystems
     * @param ip user IP address
     */
    public void registerNewOrganization(String organizationName, String city,
            String email, String password, Integer minutes, Integer rate, Integer maxSystems, String ip);

}
