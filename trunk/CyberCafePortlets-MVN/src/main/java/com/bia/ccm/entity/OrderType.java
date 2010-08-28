/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */

package com.bia.ccm.entity;

/**
 *
 * @author intesar
 */
public enum OrderType {

    DURATION_BILLABLE("duration"),
    MIXED("mixed"),
    PRODUCT_BILLABLE("non-duration");

    private String type;

    private OrderType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
