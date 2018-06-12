package com.jpmorgan.models;

import java.math.BigDecimal;

public class SailList extends Sail {

    private int numberOfProducts;

    public SailList(String productType, BigDecimal price, int numberOfProducts) {
        super(productType, price);
        this.numberOfProducts = numberOfProducts;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }
}
