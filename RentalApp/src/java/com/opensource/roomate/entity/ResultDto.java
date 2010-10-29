/*
 *
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 *
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */

package com.opensource.roomate.entity;

import com.opensource.roomate.entity.Post;
import java.util.List;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/**
 *
 * @author inmohamm
 */
@DataTransferObject
public class ResultDto {

    @RemoteProperty
    private List<Post> list;
    @RemoteProperty
    private int start;
    @RemoteProperty
    private int max;
    @RemoteProperty
    private long total;

    public ResultDto(List<Post> list, int start, int max, long total) {
        this.list = list;
        this.start = start;
        this.max = max;
        this.total = total;
    }

    public ResultDto() {
    }

    public List<Post> getList() {
        return list;
    }

    public void setList(List<Post> list) {
        this.list = list;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
