package com.bizintelapps.cars.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author intesar
 */
public class ResultDto<T> implements Serializable {

    private int start;
    private int max;
    private List<T> list;
    private int total;

    public ResultDto(int start, int max, List<T> list, int total) {
        this.start = start;
        this.max = max;
        this.list = list;
        this.total = total;
    }

    public ResultDto() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
