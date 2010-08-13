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

import com.bizintelapps.easydao.dao.BaseModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 *
 * @author intesar
 */
@Indexed
@AnalyzerDef(name = "customanalyzer",
  tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
  filters = {
    @TokenFilterDef(factory = LowerCaseFilterFactory.class),
    @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
      @Parameter(name = "language", value = "English")
    })
  })
@Entity
@Table(name = "order_detail")
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findByCustomerName", query = "select s from OrderDetail s where s.customerName like ?1 "),
    @NamedQuery(name = "OrderDetail.findByOrderStatus", query = "select s from OrderDetail s where s.organization = ?1 AND s.orderStatus like ?2"),
    @NamedQuery(name = "OrderDetail.findByCustomerInfo", query = "select s from OrderDetail s "
    + "WHERE s.customerUsername LIKE ?1 OR s.customerEmail LIKE ?2 OR s.customerUserId = ?3 ORDER BY s.createDate DESC ")
})
public class OrderDetail extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    /*  Customer Details */
    @Analyzer(definition = "customanalyzer")
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "customer_name")
    private String customerName;
    @Analyzer(definition = "customanalyzer")
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "customer_username")
    private String customerUsername;
    @Analyzer(definition = "customanalyzer")
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "customer_email")
    private String customerEmail;
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "customer_userId")
    private Long customerUserId;
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "customer_phone")
    private String customerPhone;
    /* payment info */
    @Column(name = "amount_due", nullable = false)
    private Double amountDue;
    @Column(name = "paid_amount", nullable = false)
    private Double paidAmount;
    @Column(name = "payable_amount", nullable = false)
    private Double payableAmount;
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Enumerated
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;
    @IndexedEmbedded
    @OneToMany(targetEntity = com.bia.ccm.entity.OrderItem.class, cascade = CascadeType.ALL, mappedBy = "orderDetail")
    private List<OrderItem> orderItems;
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "organization", nullable = false)
    private Long organization;
    @DateBridge(resolution = Resolution.MINUTE)
    @Field(index = Index.UN_TOKENIZED, store = Store.YES)
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date orderDate;

    public OrderDetail() {
        this.amountDue = 0.0;
        this.paidAmount = 0.0;
        this.payableAmount = 0.0;
        this.orderStatus = OrderStatus.LIVE;
        this.orderItems = new ArrayList<OrderItem>();
        this.orderDate = new Date();
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(Long customerUserId) {
        this.customerUserId = customerUserId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public Long getOrganization() {
        return organization;
    }

    public void setOrganization(Long organization) {
        this.organization = organization;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bia.ccm.entity.Order[id=" + id + "]";
    }
}
