package com.jpmorgan.models;

import java.math.BigDecimal;

/**
 * Created by lmontunato on 12/06/2018
 */
public class Report {
    private String productType;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private int numberOfSails;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumberOfSails() {
        return numberOfSails;
    }

    public void setNumberOfSails(int numberOfSails) {
        this.numberOfSails = numberOfSails;
    }
}
