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
public enum ProductType {

    Computer("computer"),
    OTHER("other");

    private String rating;

    private ProductType(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return rating;
    }

}
