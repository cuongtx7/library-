package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_variant")
public class ProductVariant extends AbstractAuditingEntity {
    @Column(name = "product_id", length = 60)
    private String productId;

    @Column(name = "product_code", length = 60)
    private String productCode;

    @Column(name = "size", length = 60)
    private String size;

    @Column(name = "color", length = 60)
    private String color;

    @Column(name = "gender", length = 60)
    private String gender;

    @Column(name = "unit_price")
    private Float unitPrice;

    @Column(name = "discount")
    private Float discount;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}
