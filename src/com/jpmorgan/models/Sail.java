package com.jpmorgan.models;

import java.math.BigDecimal;

public class Sail {
    private String productType;
    private BigDecimal price;

    public Sail(String productType, BigDecimal price) {
        this.productType = productType;
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
