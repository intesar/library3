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

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author intesar
 */
public class PreferenceDto {
    private Set<String> emails;
    private Set<Short> timings;

    public PreferenceDto() {
        emails = new HashSet<String>();
        timings = new HashSet<Short>();
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public Set<Short> getTimings() {
        return timings;
    }

    public void setTimings(Set<Short> timings) {
        this.timings = timings;
    }

    

}
