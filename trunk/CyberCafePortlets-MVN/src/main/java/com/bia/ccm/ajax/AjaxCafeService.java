/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */

package com.bia.ccm.ajax;

import com.bia.ccm.entity.Services;
import com.bia.ccm.services.ProductService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author intesar
 */
public class AjaxCafeService {

    /**
     *
     * @return
     */
    public List<Services> getAllServices(Long groupId, HttpSession session) {
        List<Services> list = this.productService.getAllServicesWithSystem(groupId);
        return list;
    }


    //  getters and setters
    @Autowired
    protected ProductService productService;
    protected static final Log logger = LogFactory.getLog(AjaxCafeService.class);
}
