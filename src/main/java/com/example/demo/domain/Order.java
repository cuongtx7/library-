package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order extends AbstractAuditingEntity {
    @Column(name = "customer_id", length = 255)
    private String customerId;

    @Column(name = "account_id", length = 50)
    private String accountId;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "product_total")
    private Float productTotal;

    @Column(name = "payment_amount")
    private Float paymentAmount;

    @Column(name = "note", length = 500)
    private String note;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Column(name = "paid_amount")
    private Float paidAmount;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(Float productTotal) {
        this.productTotal = productTotal;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Float getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Float paidAmount) {
        this.paidAmount = paidAmount;
    }
}
