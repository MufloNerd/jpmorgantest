package com.jpmorgan.models;

import java.math.BigDecimal;

public class SailOperation extends Sail {

    private Operation operation;

    public SailOperation(String productType, BigDecimal price, Operation operation) {
        super(productType, price);
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

}
