package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;

public class OrderItem {
    protected final String name;
    protected final BigDecimal price;

    public OrderItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal price() {
        return this.price;
    }
}
