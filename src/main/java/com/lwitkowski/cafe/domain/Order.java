package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Coffee> items = new ArrayList<>();

    public Order add(Coffee coffee) {
        this.items.add(coffee);
        return this;
    }
    
    public BigDecimal totalPrice() {
        return items
                .stream()
                .map(Coffee::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
